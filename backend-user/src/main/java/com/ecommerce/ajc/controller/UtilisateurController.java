// ✅ 18. UtilisateurController.java
package com.ecommerce.ajc.controller;

import com.ecommerce.ajc.model.Utilisateur;
import com.ecommerce.ajc.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // 🔍 Récupérer les infos d'un utilisateur par son ID
    @GetMapping("/{id}")
    public Utilisateur getUtilisateur(@PathVariable Long id) {
        return utilisateurService.getUtilisateur(id);
    }

    // ✏️ Mettre à jour le profil d'un utilisateur
    @PutMapping("/{id}")
    public Utilisateur updateProfil(@PathVariable Long id, @RequestBody Utilisateur updatedUser) {
        return utilisateurService.updateUtilisateur(id, updatedUser);
    }

    // 🗑️ Supprimer un compte utilisateur
    @DeleteMapping("/{id}")
    public void deleteCompte(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
    }
}
