package com.ecommerce.ajc.controller;

import com.ecommerce.ajc.service.UtilisateurService;
import model.Utilisateur;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private String getCurrentEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    @GetMapping
    public Utilisateur getProfil() {
        return utilisateurService.findByEmail(getCurrentEmail());
    }

    @PutMapping
    public Utilisateur updateProfil(@RequestBody Utilisateur updated) {
        return utilisateurService.updateUserByEmail(getCurrentEmail(), updated);
    }

    @DeleteMapping
    public void deleteProfil() {
        utilisateurService.deleteByEmail(getCurrentEmail());
    }
}
