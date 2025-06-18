package com.ecommerce.ajc.service;

import model.Utilisateur;

public interface UtilisateurService {
    Utilisateur register(Utilisateur utilisateur);
    String login(Utilisateur utilisateur);

    Utilisateur findByEmail(String email);
    Utilisateur updateUserByEmail(String email, Utilisateur updatedUser);
    void deleteByEmail(String email);
}
