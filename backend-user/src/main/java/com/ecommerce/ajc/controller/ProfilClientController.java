package com.ecommerce.ajc.controller;

import com.ecommerce.ajc.service.UtilisateurService;
import model.Utilisateur;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client/profil")
@PreAuthorize("hasRole('CLIENT')")
public class ProfilClientController {

    private final UtilisateurService utilisateurService;

    public ProfilClientController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public Utilisateur getProfil(@AuthenticationPrincipal UserDetails userDetails) {
        return utilisateurService.findByUsername(userDetails.getUsername());
    }

    @PutMapping
    public Utilisateur updateProfil(@AuthenticationPrincipal UserDetails userDetails,
                                    @RequestBody Utilisateur updated) {
        return utilisateurService.updateUser(userDetails.getUsername(), updated);
    }

    @DeleteMapping
    public void deleteProfil(@AuthenticationPrincipal UserDetails userDetails) {
        utilisateurService.deleteByUsername(userDetails.getUsername());
    }
}
