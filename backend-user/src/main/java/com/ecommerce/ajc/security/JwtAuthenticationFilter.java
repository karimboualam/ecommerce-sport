package com.ecommerce.ajc.security;

import io.jsonwebtoken.Claims;
import model.Utilisateur;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import repository.UtilisateurRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final MyUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil,  MyUserDetailsService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = extractToken(request);

        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {


                String username = jwtTokenUtil.extractUsername(token);
                Claims claims = jwtTokenUtil.extractAllClaims(token);
                String role = claims.get("role", String.class); // 🔐 lecture directe du rôle
                System.out.println("🔐 CLAIM ROLE : " + role); // pour afficher le role
               /* if (!role.startsWith("ROLE_")) {
                    role = "ROLE_" + role;
                }*/

                // ✅ AJOUTE LES LOGS ICI
                System.out.println("🔐 Token reçu : " + token);
                System.out.println("🔐 Username : " + username);
                System.out.println("🔐 Authority injectée : " + role);

        //     UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (username != null && role != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                  username,
                               //     userDetails,
                                    null,
                                    Collections.singletonList(new SimpleGrantedAuthority(role)) // 👈 ex: ROLE_ADMIN
                            );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    System.out.println("🔐 ROLE injecté dans le contexte Spring : " + role);
                }

            } catch (Exception e) {
                System.err.println("❌ Erreur dans JwtAuthenticationFilter : " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }



    public Utilisateur getUserFromToken(String token, UtilisateurRepository utilisateurRepository) {
    //   String email = getUsernameFromToken(token.replace("Bearer ", ""));
        String email = jwtTokenUtil.extractUsername(token.replace("Bearer ", ""));

        return utilisateurRepository.findByEmail(email);
    }
}
