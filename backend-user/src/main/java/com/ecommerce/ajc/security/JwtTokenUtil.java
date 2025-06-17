package com.ecommerce.ajc.security;

import model.Utilisateur;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expirationTime;

    // ✅ Cette méthode centralise la génération correcte de la clé
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    /*private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        if (keyBytes.length < 64) { // HS512 nécessite 512 bits (64 bytes)
            throw new IllegalArgumentException("La clé doit faire 64 bytes (512 bits)");
        }
        return Keys.hmacShaKeyFor(keyBytes);
    }*/

    public String generateToken(Utilisateur utilisateur) {
        return Jwts.builder()
               // .setSubject(utilisateur.getUsername())
                .setSubject(utilisateur.getEmail())
                .claim("role", utilisateur.getRole().name())// Important: le nom du claim doit correspondre à ce que Spring Security attend
              //  .claim("role", "ROLE_" + utilisateur.getRole().name()) // Ajoutez ROLE_ explicitement
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)  // ✅ clé uniforme
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())  // ✅ même clé utilisée
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, Utilisateur utilisateur) {
        String username = extractUsername(token);
        return username.equals(utilisateur.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())  // ✅ même clé utilisée
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }
  /*  public String extractRole(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("roles", String.class);
    }*/

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
