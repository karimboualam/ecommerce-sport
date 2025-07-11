package com.ecommerce.ajc.service;

import model.*;
import org.springframework.stereotype.Service;
import repository.ArticleRepository;
import repository.PanierItemRepository;
import repository.PanierRepository;

import java.util.Optional;

@Service
public class PanierServiceImpl implements PanierService {

    private final PanierRepository panierRepository;
    private final PanierItemRepository panierItemRepository;
    private final ArticleRepository articleRepository;

    public PanierServiceImpl(PanierRepository panierRepository, PanierItemRepository panierItemRepository, ArticleRepository articleRepository) {
        this.panierRepository = panierRepository;
        this.panierItemRepository = panierItemRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public Panier getPanier(Utilisateur utilisateur) {
        return panierRepository.findByUtilisateur(utilisateur).orElseGet(() -> {
            Panier panier = new Panier();
            panier.setUtilisateur(utilisateur);
            return panierRepository.save(panier);
        });
    }

    @Override
    public Panier addToPanier(Utilisateur utilisateur, Article article, int quantite) {
        Panier panier = getPanier(utilisateur);

        Optional<PanierItem> existingItem = panier.getItems().stream()
                .filter(item -> item.getArticle().getReference() == article.getReference())
                .findFirst();

        if (existingItem.isPresent()) {
            PanierItem item = existingItem.get();
            item.setQuantite(item.getQuantite() + quantite);
            item.setPrixUnitaire(article.getPrix());
        } else {
            PanierItem item = new PanierItem();
            item.setArticle(article);
            item.setQuantite(quantite);
            item.setPrixUnitaire(article.getPrix());
            item.setPanier(panier);
            panier.getItems().add(item);
        }
        return panierRepository.save(panier);
    }
    @Override
    public Panier updateQuantite(Utilisateur utilisateur, Long itemId, int quantite) {
        Panier panier = getPanier(utilisateur);
        panier.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .ifPresent(item -> item.setQuantite(quantite));
        return panierRepository.save(panier);
    }

    @Override
    public void removeFromPanier(Utilisateur utilisateur, Long itemId) {
        Panier panier = getPanier(utilisateur);
        panier.getItems().removeIf(item -> item.getId().equals(itemId));
        panierRepository.save(panier);
    }

    @Override
    public Panier applyPromoCode(Utilisateur utilisateur, String codePromo) {
        Panier panier = getPanier(utilisateur);

        double totalSansRemise = panier.getItems().stream()
                .mapToDouble(item -> item.getPrixUnitaire() * item.getQuantite())
                .sum();

        double remise = 0;

        // 💡 Exemple simple : fixe par code
        switch (codePromo.toLowerCase()) {
            case "welcome10":
                remise = 10.0;
                break;
            case "freeship":
                remise = 5.0;
                break;
            case "vip20":
                remise = totalSansRemise * 0.20;
                break;
            default:
                throw new RuntimeException("Code promo invalide");
        }

        panier.setCodePromo(codePromo);
        panier.setRemise(remise);
        panier.setTotal(totalSansRemise - remise);

        return panierRepository.save(panier);
    }

}