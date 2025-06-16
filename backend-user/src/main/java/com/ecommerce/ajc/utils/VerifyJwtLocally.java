package com.ecommerce.ajc.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class VerifyJwtLocally {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBZG1pbjYiLCJyb2xlIjoiUk9MRV9BRE1JTiIsImlhdCI6MTc1MDA3NTYyNSwiZXhwIjoxNzUwMTYyMDI1fQ.bB64VhHKySorpef-JBX-Qcu9thN7R_neG-fea97fjMKGdIvZB4kMOVadpx4sqdoe6rsn7RgFZReaPr-d8NiFNg"; // 🔁 Mets ici ton token complet
        String base64Key = "6JoST51hCabkeAKV+LaWIe0dagbSyT/3HeyKIhANC6ITfwU/Ugx4q+mV/D/WCeIK6Bsz7sIJ4mjYB6u2nOqhag==";

        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(base64Key));

            Jws<Claims> parsedToken = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            System.out.println("✅ Signature VALIDE !");
            System.out.println("Subject (username) : " + parsedToken.getBody().getSubject());
            System.out.println("Role : " + parsedToken.getBody().get("role"));
        } catch (JwtException e) {
            System.out.println("❌ Signature INVALIDE !");
            System.out.println(e.getMessage());
        }
    }
}
