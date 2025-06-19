package config;

import model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import repository.UtilisateurRepository;

@Controller
public class TestController {
/*
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Assurez-vous que le bean PasswordEncoder est bien injecté
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/reset-password-for-admin") // URL facile à appeler
    @ResponseBody
    public String resetAdminPassword() {
        Utilisateur admin = utilisateurRepository.findByEmail("admin2@mail.com");

        if (admin == null) {
            return "ERREUR : Utilisateur 'admin@site.com' non trouvé.";
        }

        // Le mot de passe que nous allons utiliser pour le test
        String nouveauMotDePasseClair = "123456";

        // On utilise le même encodeur que Spring Security pour générer le hash
        String nouveauMotDePasseHashe = passwordEncoder.encode(nouveauMotDePasseClair);

        // On met à jour l'objet et on sauvegarde
        admin.setPassword(nouveauMotDePasseHashe);
        utilisateurRepository.save(admin);

        System.out.println("--- MOT DE PASSE REINITIALISE ---");
        System.out.println("Utilisateur: admin@site.com");
        System.out.println("Nouveau mot de passe en clair: " + nouveauMotDePasseClair);
        System.out.println("Nouveau hash en BDD: " + nouveauMotDePasseHashe);

        return "Mot de passe pour 'admin@site.com' réinitialisé à 'admin123'. Vous pouvez maintenant vous connecter.";
    } */
}
