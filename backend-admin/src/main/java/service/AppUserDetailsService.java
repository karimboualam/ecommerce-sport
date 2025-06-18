package service;

import model.Utilisateur;
import repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import security.CustomUserDetails;

import java.util.Collections;
import java.util.Optional;

@Service("appUserDetailsService")
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("--- [DEBUG] 1 AppUserDetailsService.loadUserByUsername a été appelé pour l'email : " + email);
        System.out.println("--- [DEBUG] 2 AppUserDetailsService.loadUserByUsername a été appelé pour l'email : " + email);

        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        System.out.println("--- [DEBUG] 3 AppUserDetailsService.loadUserByUsername a été appelé pour l'email : " + utilisateur);

        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email: " + email);
        }

        /*return new User(
                utilisateur.getEmail(),
                utilisateur.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(utilisateur.getRole().name()))
        );*/
        return new CustomUserDetails(utilisateur);
    }
}