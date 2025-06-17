// ✅ 17. CommandeService.java
package com.ecommerce.ajc.service;

import model.Commande;
import repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    public Commande createCommande(Commande commande) {
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
}