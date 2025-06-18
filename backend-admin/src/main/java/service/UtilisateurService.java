package service;

import model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.UtilisateurRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;


    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> findById(Long id) {
        return utilisateurRepository.findById(id);
    }

    public void deleteById(Long id) {
        utilisateurRepository.deleteById(id);
    }

    public void save(Utilisateur utilisateur) {
        if (!utilisateur.getPassword().startsWith("$2a$")) {
            utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        }
        utilisateurRepository.save(utilisateur);
    }

    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }
}
