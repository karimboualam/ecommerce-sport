// ✅ 15. UtilisateurService.java
package com.ecommerce.ajc.service;

import model.Utilisateur;
import repository.UtilisateurRepository;
import com.ecommerce.ajc.security.JwtTokenUtil;
import security.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UtilisateurServiceImpl implements UtilisateurService, UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Utilisateur register(Utilisateur utilisateur) {
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        // ✅ Si aucun rôle n'est fourni, on attribue ROLE_CLIENT
        if (utilisateur.getRole() == null) {
            utilisateur.setRole(RoleEnum.ROLE_CLIENT);
        }

        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public String login(Utilisateur utilisateur) {
        Utilisateur existingUser = utilisateurRepository.findByEmail(utilisateur.getEmail());
        if (existingUser != null && passwordEncoder.matches(utilisateur.getPassword(), existingUser.getPassword())) {
            System.out.println("🛡️ ROLE UTILISATEUR CONNECTÉ : " + existingUser.getRole());
            return jwtTokenUtil.generateToken(existingUser);
        }
        throw new RuntimeException("Invalid credentials");
    }

    @Override
    public Utilisateur getUtilisateur(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur not found"));
    }

    @Override
    public Utilisateur updateUtilisateur(Long id, Utilisateur updatedUser) {
        Utilisateur existing = getUtilisateur(id);
        existing.setUsername(updatedUser.getUsername());
        existing.setAdresse(updatedUser.getAdresse());
        // ⚠️ ne jamais changer le mot de passe ici sans vérification
        return utilisateurRepository.save(existing);
    }

    @Override
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur user = utilisateurRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur introuvable avec l'email : " + email);
        }

        // ✅ le rôle doit être converti en authority avec le préfixe ROLE_
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
}
