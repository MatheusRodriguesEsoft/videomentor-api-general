package br.com.videomentor.api.auth.jwt;

import br.com.videomentor.api.exceptions.InvalidTokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtilities {

  @Value("${api.security.token.secret}")
  private String secret;

  @Value("${api.security.token.expiration}")
  private Long jwtExpiration;

  public String generateToken(String username) {
    try {
      var algoritmo = Algorithm.HMAC256(secret);
      return JWT
        .create()
        .withIssuer("API Videomentor")
        .withSubject(username)
        .withExpiresAt(expiresDate())
        .sign(algoritmo);
    } catch (InvalidTokenException exception) {
      throw new RuntimeException("Error generate JWT token ", exception);
    }
  }

  public String getSubject(String tokenJWT) {
    try {
      var algoritmo = Algorithm.HMAC256(secret);
      return JWT
        .require(algoritmo)
        .withIssuer("API Videomentor")
        .build()
        .verify(tokenJWT)
        .getSubject();
    } catch (JWTVerificationException e) {
      throw new RuntimeException("Invalid JWT token!" + e);
    }
  }

  private Instant expiresDate() {
    return LocalDateTime.now().plusDays(30).toInstant(ZoneOffset.of("-03:00"));
  }

  public String getToken(HttpServletRequest httpServletRequest) {
    String bearerToken = httpServletRequest.getHeader("Authorization");
    if (bearerToken != null) {
      return bearerToken.replace("Bearer ", "");
    }
    return null;
  }
}
