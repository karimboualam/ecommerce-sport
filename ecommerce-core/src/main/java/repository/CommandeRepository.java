package repository;

import model.Commande;
import model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByStatus(String status);
    List<Commande> findByUtilisateur(Utilisateur utilisateur);

    @Query("SELECT c FROM Commande c LEFT JOIN FETCH c.ligneCommandes WHERE c.id = :id")
    Optional<Commande> findByIdWithLignes(@Param("id") Long id);

    @Query("SELECT c FROM Commande c LEFT JOIN FETCH c.ligneCommandes LEFT JOIN FETCH c.utilisateur")
    List<Commande> findAllWithLignesEtUtilisateurs();

}
