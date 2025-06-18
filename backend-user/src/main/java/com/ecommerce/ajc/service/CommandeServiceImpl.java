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

@Service
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    @Override
    public Commande createCommande(Commande commande, String email) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        commande.setUtilisateur(utilisateur);

        if (commande.getDate() == null) {
            commande.setDate(new Date());
        }

        for (LigneCommande ligne : commande.getLigneCommandes()) {
            ligne.setCommande(commande);

            Article article = articleRepository.findById(ligne.getArticle().getReference())
                    .orElseThrow(() -> new RuntimeException("Article non trouvé"));

            ligne.setArticle(article);
        }

        double total = commande.getLigneCommandes().stream()
                .mapToDouble(l -> l.getPrix() * l.getQuantite())
                .sum();
        commande.setMontant(total);

        return commandeRepository.save(commande);
    }

    @Override
    public Commande getCommandeById(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande introuvable"));
    }

    @Override
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public Commande updateCommande(Long id, Commande updatedCommande) {
        Commande existing = getCommandeById(id);
        existing.setStatus(updatedCommande.getStatus());
        existing.setMontant(updatedCommande.getMontant());
        existing.setAdresseLivraison(updatedCommande.getAdresseLivraison());
        return commandeRepository.save(existing);
    }

    @Override
    public void deleteCommande(Long id) {
        commandeRepository.deleteById(id);
    }

    @Override
    public List<Commande> getCommandesByEmail(String email) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        return commandeRepository.findByUtilisateur(utilisateur);
    }
}
