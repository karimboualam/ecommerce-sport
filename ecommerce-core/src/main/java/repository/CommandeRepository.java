package repository;

import model.Commande;
import model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByStatus(String status);
    List<Commande> findByUtilisateur(Utilisateur utilisateur);
}
