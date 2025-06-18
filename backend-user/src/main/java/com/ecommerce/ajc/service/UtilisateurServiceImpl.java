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
        if (utilisateur.getRole() == null) {
            utilisateur.setRole(RoleEnum.ROLE_CLIENT);
        }
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public String login(Utilisateur utilisateur) {
        Utilisateur existingUser = utilisateurRepository.findByEmail(utilisateur.getEmail());
        if (existingUser != null && passwordEncoder.matches(utilisateur.getPassword(), existingUser.getPassword())) {
            return jwtTokenUtil.generateToken(existingUser);
        }
        throw new RuntimeException("Invalid credentials");
    }

    @Override
    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    @Override
    public Utilisateur updateUserByEmail(String email, Utilisateur updatedUser) {
        Utilisateur existing = findByEmail(email);
        existing.setUsername(updatedUser.getUsername());
        existing.setAdresse(updatedUser.getAdresse());
        existing.setPrenom(updatedUser.getPrenom());
        existing.setNom(updatedUser.getNom());
        return utilisateurRepository.save(existing);
    }

    @Override
    public void deleteByEmail(String email) {
        Utilisateur user = findByEmail(email);
        utilisateurRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur user = utilisateurRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur introuvable avec l'email : " + email);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
}
