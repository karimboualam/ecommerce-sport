// ✅ 3. JwtTokenUtil.java
package com.ecommerce.ajc.security;

import com.ecommerce.ajc.model.Utilisateur;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    //private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);


    @Value("${jwt.expiration}")
    private long expirationTime;

    public String generateToken(Utilisateur utilisateur) {
        return Jwts.builder()
                .setSubject(utilisateur.getUsername())
                .claim("role", utilisateur.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
             //   .signWith(SignatureAlgorithm.HS512, secretKey)
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS512)
                .compact();
    }

   /* public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody().getSubject();
    } */
   public String extractUsername(String token) {
       return Jwts.parser()
               .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)))  // ✅ clé bien décodée
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

   /* private Date extractExpiration(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody().getExpiration();
    }*/
   private Date extractExpiration(String token) {
       return Jwts.parser()
               .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)))
               .parseClaimsJws(token)
               .getBody()
               .getExpiration();
   }

}
