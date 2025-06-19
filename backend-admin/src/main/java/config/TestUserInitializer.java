package config;

import model.Utilisateur;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import repository.UtilisateurRepository;
import security.RoleEnum;

@Configuration
public class TestUserInitializer {

    @Bean
    public CommandLineRunner initTestAdminUser(UtilisateurRepository utilisateurRepository,
                                               PasswordEncoder passwordEncoder) {
        return args -> {
            String emailTest = "admin1@test.com";
            if (utilisateurRepository.findByEmail(emailTest) == null) {
                Utilisateur admin = new Utilisateur();
                admin.setEmail(emailTest);
                admin.setUsername("admin-test");
                admin.setNom("Admin");
                admin.setPrenom("Test");
                admin.setRole(RoleEnum.ROLE_ADMIN);
                admin.setPassword(passwordEncoder.encode("123456"));

                utilisateurRepository.save(admin);

                System.out.println("✅ Utilisateur de test créé : admin@test.com / 123456");
            } else {
                System.out.println("ℹ️ Utilisateur de test déjà existant : " + emailTest);
            }
        };
    }

}
