package com.example.apirestbooks.services.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
	  private static final String JWT_SECRET_KEY = "cursospringboot3";
	  // ensure key length is at least 256 bits for HS256 by padding/truncating
	  private static final byte[] SECRET_BYTES = Arrays.copyOf(JWT_SECRET_KEY.getBytes(StandardCharsets.UTF_8), 32);
	  private static final Key SIGNING_KEY = Keys.hmacShaKeyFor(SECRET_BYTES);
	  public static final long JWT_TOKEN_VALIDITY = (long) 1000 * 60 * 60; // 1 hora

	  public String extractUsername(String token) {
	    return extractClaim(token, Claims::getSubject);
	  }

	  public Date extractExpiration(String token) {
	    return extractClaim(token, Claims::getExpiration);
	  }

	  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	    return claimsResolver.apply(extractAllClaims(token));
	  }

	  private Claims extractAllClaims(String token) {
	    // use parserBuilder().setSigningKey(key).build() to get a JwtParser, then parse
	    return Jwts.parser().setSigningKey(SIGNING_KEY).build().parseClaimsJws(token).getBody();
	  }

	  private Boolean isTokenExpired(String token) {
	    return extractExpiration(token).before(new Date());
	  }

	  public String generateToken(UserDetails userDetails) {
	    Map<String, Object> claims = new HashMap<>();
	    // get first authority as a string (e.g. "ROLE_USER")
	    String rol = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse("");
	    claims.put("rol", rol);
	    return createToken(claims, userDetails.getUsername());
	  }

	  private String createToken(Map<String, Object> claims, String subject) {
	    return Jwts
	        .builder()
	        .setClaims(claims)
	        .setSubject(subject)
	        .setIssuedAt(new Date(System.currentTimeMillis()))
	        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
	        .signWith(SIGNING_KEY, SignatureAlgorithm.HS256)
	        .compact();
	  }

	  public boolean validateToken(String token, UserDetails userDetails) {
	    final String username = extractUsername(token);
	    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	  }
}
