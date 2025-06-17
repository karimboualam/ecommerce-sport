// ✅ 17. CommandeService.java
package com.ecommerce.ajc.service;

import model.Article;
import model.Commande;
import model.LigneCommande;
import model.Utilisateur;
import repository.ArticleRepository;
import repository.CommandeRepository;
import repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
    private
    UtilisateurRepository utilisateurRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    /*public Commande createCommande(Commande commande) {
        return commandeRepository.save(commande);
    }*/
    public Commande createCommande(Commande commande, String email) {
        // Lier l’utilisateur connecté
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        commande.setUtilisateur(utilisateur);

        // Ajouter la date du jour si non renseignée
        if (commande.getDate() == null) {
            commande.setDate(new Date());
        }

        //  Pour chaque ligne, lier la commande et charger l'article complet
        for (LigneCommande ligne : commande.getLigneCommandes()) {
            ligne.setCommande(commande);

            Article article = articleRepository.findById(ligne.getArticle().getReference())
                    .orElseThrow(() -> new RuntimeException("Article non trouvé"));

            ligne.setArticle(article);
        }

        //  Sauvegarde en cascade
        return commandeRepository.save(commande);
    }


    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id).orElseThrow(() -> new RuntimeException("Commande not found"));
    }

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Commande updateCommande(Long id, Commande updatedCommande) {
        Commande existing = getCommandeById(id);
        existing.setStatus(updatedCommande.getStatus());
        existing.setMontant(updatedCommande.getMontant());
        existing.setAdresseLivraison(updatedCommande.getAdresseLivraison());
        return commandeRepository.save(existing);
    }

    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }

    public List<Commande> getCommandesByEmail(String email) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        return commandeRepository.findByUtilisateur(utilisateur);
    }

}