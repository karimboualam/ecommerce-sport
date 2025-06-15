
// ✅ 14. CommandeRepository.java
package com.ecommerce.ajc.repository;

import com.ecommerce.ajc.model.Commande;
import com.ecommerce.ajc.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByStatus(String status);
    List<Commande> findByUtilisateur(Utilisateur utilisateur);
}
