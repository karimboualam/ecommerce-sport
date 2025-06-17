package com.ecommerce.ajc.service;

import model.Panier;
import model.PanierItem;
import model.Article;
import model.Utilisateur;

public interface PanierService {
    Panier getPanier(Utilisateur utilisateur);
    Panier addToPanier(Utilisateur utilisateur, Article article, int quantite);
    void removeFromPanier(Utilisateur utilisateur, Long itemId);
}
