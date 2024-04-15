package br.com.videomentor.api.config;

import br.com.videomentor.api.auth.jwt.JwtAuthenticationFilter;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

  @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  private static final String[] AUTH_WHITELIST = { "/api/v1/auth/**", "/v3/api-docs/**", "/v3/api-docs.yaml",
      "/swagger-ui/**", "/swagger-ui.html", };

  private static final String[] ALL_USERS = { "ADMIN", "USER", "TEACHER", "STUDENT", };
  private static final String[] SUPER_USERS = { "ADMIN", "TEACHER" };

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> {
      csrf.disable();
    });
    http.cors(cors -> {
      cors.configurationSource(corsConfigurationSource());
    });
    http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    http.authorizeHttpRequests(auth -> {
      auth.requestMatchers(AUTH_WHITELIST).permitAll();
      auth.requestMatchers("/").permitAll();
      auth.requestMatchers(HttpMethod.GET, "/users").permitAll();
      auth.requestMatchers("/auth/**").permitAll();
      auth.requestMatchers("/auth/login").permitAll();
      auth.requestMatchers("/auth/login/teacher").permitAll();
      auth.requestMatchers("/auth/login/student").permitAll();
      auth.requestMatchers("/auth/authenticate").permitAll();

      // ROLES
      auth.requestMatchers(HttpMethod.GET, "/roles/**").hasAnyAuthority(ALL_USERS);

      // REDEFINE PASSWORD
      auth.requestMatchers(HttpMethod.GET, "/auth/redefine-password/**").hasAnyAuthority(ALL_USERS);

      // NOTIFICATIONS
      auth.requestMatchers(HttpMethod.GET, "/auth/notifications/**").hasAnyAuthority(ALL_USERS);
      auth.requestMatchers(HttpMethod.POST, "/auth/notifications/**").hasAuthority("ADMIN");
      auth.requestMatchers(HttpMethod.PUT, "/auth/notifications/**").hasAuthority("ADMIN");
      auth.requestMatchers(HttpMethod.PATCH, "/auth/notifications/**").hasAuthority("ADMIN");
      auth.requestMatchers(HttpMethod.DELETE, "/auth/notifications/**").hasAuthority("ADMIN");

      // COMMENTS
      auth.requestMatchers(HttpMethod.GET, "/comments/**").hasAnyAuthority(ALL_USERS);
      auth.requestMatchers(HttpMethod.POST, "/comments/**").hasAnyAuthority(ALL_USERS);
      auth.requestMatchers(HttpMethod.PUT, "/comments/**").hasAnyAuthority(ALL_USERS);
      auth.requestMatchers(HttpMethod.PATCH, "/comments/**").hasAnyAuthority(ALL_USERS);
      auth.requestMatchers(HttpMethod.DELETE, "/comments/**").hasAnyAuthority(ALL_USERS);

      // USERS
      auth.requestMatchers(HttpMethod.GET, "/users/**").hasAnyAuthority(ALL_USERS);
      auth.requestMatchers(HttpMethod.POST, "/users/**").permitAll();
      auth.requestMatchers(HttpMethod.PUT, "/users/**").hasAnyAuthority(ALL_USERS);
      auth.requestMatchers(HttpMethod.PATCH, "/users/**").hasAnyAuthority(ALL_USERS);
      auth.requestMatchers(HttpMethod.DELETE, "/users/**").hasAuthority("ADMIN");

      // TEACHERS
      auth.requestMatchers(HttpMethod.GET, "/teachers/**").hasAnyAuthority(ALL_USERS);

      // AREA-OF-KNOWLEDGE
      auth.requestMatchers(HttpMethod.GET, "/area-of-knowledge/**").hasAnyAuthority(ALL_USERS);

      // SUBJECTS
      auth.requestMatchers(HttpMethod.GET, "/subjects/**").hasAnyAuthority(ALL_USERS);
      auth.requestMatchers(HttpMethod.POST, "/subjects/**").hasAnyAuthority(SUPER_USERS);
      auth.requestMatchers(HttpMethod.PUT, "/subjects/**").hasAnyAuthority(SUPER_USERS);
      auth.requestMatchers(HttpMethod.PATCH, "/subjects/**").hasAnyAuthority(SUPER_USERS);
      auth.requestMatchers(HttpMethod.DELETE, "/subjects/**").hasAnyAuthority(SUPER_USERS);

      // MODULES
      auth.requestMatchers(HttpMethod.GET, "/subjects/**").hasAnyAuthority(ALL_USERS);
      auth.requestMatchers(HttpMethod.POST, "/subjects/**").hasAnyAuthority(SUPER_USERS);
      auth.requestMatchers(HttpMethod.PUT, "/subjects/**").hasAnyAuthority(SUPER_USERS);
      auth.requestMatchers(HttpMethod.PATCH, "/subjects/**").hasAnyAuthority(SUPER_USERS);
      auth.requestMatchers(HttpMethod.DELETE, "/subjects/**").hasAnyAuthority(SUPER_USERS);

      // VIDEOAULAS
      auth.requestMatchers(HttpMethod.GET, "/videoaulas/**").hasAnyAuthority(ALL_USERS);
      auth.requestMatchers(HttpMethod.POST, "/videoaulas/**").hasAuthority("TEACHER");
      auth.requestMatchers(HttpMethod.PUT, "/videoaulas/**").hasAnyAuthority(ALL_USERS);
      auth.requestMatchers(HttpMethod.PATCH, "/videoaulas/**").hasAuthority("TEACHER");
      auth.requestMatchers(HttpMethod.DELETE, "/videoaulas/**").hasAuthority("TEACHER");

      // SERIE
      auth.requestMatchers(HttpMethod.GET, "/series/**").hasAnyAuthority(ALL_USERS);

      // ANYREQUESTS
      auth.anyRequest().authenticated();
    });
    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("https://videomentor.onrender.com//"));
    configuration.addAllowedMethod("*");
    configuration.addAllowedHeader("*");
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public CorsFilter corsFilter() {
    CorsFilter corsFilter = new CorsFilter(corsConfigurationSource());
    return corsFilter;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
