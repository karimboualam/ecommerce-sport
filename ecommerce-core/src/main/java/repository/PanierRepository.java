package repository;

import model.Panier;
import model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PanierRepository extends JpaRepository<Panier, Integer> {
    Optional<Panier> findByUtilisateur(Utilisateur utilisateur);
}
