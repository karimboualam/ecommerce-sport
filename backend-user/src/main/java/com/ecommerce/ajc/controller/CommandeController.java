package com.ecommerce.ajc.controller;

import com.ecommerce.ajc.service.CommandeService;
import model.Commande;
import model.LigneCommande;
import model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import repository.CommandeRepository;
import repository.UtilisateurRepository;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/client/commandes")
@PreAuthorize("hasRole('CLIENT')")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }
    @Autowired
  UtilisateurRepository utilisateurRepository;

    @Autowired
    CommandeRepository commandeRepository;


    @PostMapping
    @PreAuthorize("hasRole('CLIENT')")
    public Commande passerCommande(@RequestBody Commande commande) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        String email;

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else if (principal instanceof String) {
            email = (String) principal;
        } else {
            throw new RuntimeException("Utilisateur non authentifié");
        }

        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);

        commande.setUtilisateur(utilisateur);
        commande.setDate(new Date());

        for (LigneCommande ligne : commande.getLigneCommandes()) {
            ligne.setCommande(commande);
        }

        return commandeRepository.save(commande);
    }




    /*@GetMapping
    public List<Commande> mesCommandes(@AuthenticationPrincipal UserDetails userDetails) {
        return commandeService.getCommandesByEmail(userDetails.getUsername());
    } */

    /*@GetMapping("/commandes")
    @PreAuthorize("hasRole('CLIENT')")
    public List<Commande> mesCommandes(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            throw new RuntimeException("Utilisateur non authentifié");
        }

        String email = ((UserDetails) authentication.getPrincipal()).getUsername();

        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur == null) {
            throw new RuntimeException("Utilisateur non trouvé");
        }

        return commandeRepository.findByUtilisateur(utilisateur);
    }*/
    @GetMapping
    public List<Commande> mesCommandes(Authentication authentication) {
        String email;

        if (authentication.getPrincipal() instanceof UserDetails) {
            email = ((UserDetails) authentication.getPrincipal()).getUsername();
        } else {
            email = authentication.getName(); // fallback
        }

        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur == null) {
            throw new RuntimeException("Utilisateur non trouvé");
        }

        return commandeRepository.findByUtilisateur(utilisateur);
    }



    @GetMapping("/{id}")
    public Commande getCommande(@PathVariable Long id) {
        return commandeService.getCommandeById(id);
    }
}
