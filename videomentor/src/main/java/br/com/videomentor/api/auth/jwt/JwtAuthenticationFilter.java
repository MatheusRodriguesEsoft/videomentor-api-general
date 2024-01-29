package br.com.videomentor.api.auth.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.videomentor.api.user.model.User;
import br.com.videomentor.api.user.repository.UserRepository;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtUtilities jwtUtilities;

	@Autowired
	private UserRepository userRepository;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
			@NonNull FilterChain filterChain) throws ServletException, IOException {

		var tokenJWT = jwtUtilities.getToken(request);

		if (tokenJWT != null) {
			String subject = jwtUtilities.getSubject(tokenJWT);
			User user = userRepository.findByUsername(subject);

			var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

	public JwtAuthenticationFilter(JwtUtilities jwtUtilities, UserRepository userRepository) {
		this.jwtUtilities = jwtUtilities;
		this.userRepository = userRepository;
	}
}
