package repository;

import model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    @Query("SELECT COUNT(u) FROM Utilisateur u WHERE u.role = 'ROLE_CLIENT'")
    long countClients();

    Utilisateur findByEmail(String email);
}