//21
package com.ecommerce.ajc.service;

import model.Utilisateur;

public interface UtilisateurService {
    Utilisateur register(Utilisateur utilisateur);
    String login(Utilisateur utilisateur);
    Utilisateur getUtilisateur(Long id);
    Utilisateur updateUtilisateur(Long id, Utilisateur updatedUser);
    void deleteUtilisateur(Long id);
}
