package com.ecommerce.ajc.security;

import model.Utilisateur;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import repository.UtilisateurRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    public MyUserDetailsService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
//                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé : " + email);
        }
        return User.withUsername(utilisateur.getEmail())
                .password(utilisateur.getPassword()) // ⚠️ sera ignoré dans JWT
                .roles(utilisateur.getRole().name())  // ex: ROLE_CLIENT
                .build();
    }
}
