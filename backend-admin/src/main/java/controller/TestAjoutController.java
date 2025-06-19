package controller;
import model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import repository.UtilisateurRepository;
import security.RoleEnum;


@Controller

public class TestAjoutController {



    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/reset-password-for-admin")
    @ResponseBody
    public String resetAdminPassword() {
        Utilisateur admin = utilisateurRepository.findByEmail("admin2@mail.com");
        if (admin == null) return "❌ Utilisateur non trouvé.";
        String hash = passwordEncoder.encode("123456");
        admin.setPassword(hash);
        utilisateurRepository.save(admin);
        return "✅ Mot de passe réinitialisé à 123456";
    }

    @GetMapping("/create-admin")
    @ResponseBody
    public String createAdmin() {
        String email = "admin1@test.com";
        if (utilisateurRepository.findByEmail(email) != null) {
            return "ℹ️ Utilisateur déjà existant.";
        }

        Utilisateur admin = new Utilisateur();
        admin.setEmail(email);
        admin.setUsername("admin-test");
        admin.setNom("Admin");
        admin.setPrenom("Test");
        admin.setRole(RoleEnum.ROLE_ADMIN);
        admin.setPassword(passwordEncoder.encode("123456"));

        utilisateurRepository.save(admin);
        return "✅ Admin créé avec succès : admin1@test.com / 123456";
    }

}
