package com.ecommerce.ajc.service;

import model.Commande;

import java.util.List;

public interface CommandeService {

    Commande createCommande(Commande commande, String email);

    Commande getCommandeById(Long id);

    List<Commande> getAllCommandes();

    Commande updateCommande(Long id, Commande updatedCommande);

    void deleteCommande(Long id);

    List<Commande> getCommandesByEmail(String email);
}
