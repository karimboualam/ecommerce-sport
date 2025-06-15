// ✅ 13. UtilisateurRepository.java
package com.ecommerce.ajc.repository;

import com.ecommerce.ajc.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);
}