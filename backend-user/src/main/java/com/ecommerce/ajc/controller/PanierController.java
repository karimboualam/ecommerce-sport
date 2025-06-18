package com.ecommerce.ajc.controller;

import com.ecommerce.ajc.service.PanierService;
import model.Article;
import model.Panier;
import model.Utilisateur;
import com.ecommerce.ajc.security.JwtTokenUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import repository.ArticleRepository;
import repository.UtilisateurRepository;

@RestController
@RequestMapping("/api/client/panier")
@PreAuthorize("hasRole('CLIENT')")
public class PanierController {

    private final PanierService panierService;
    private final JwtTokenUtil jwtTokenUtil;
    private final UtilisateurRepository utilisateurRepository;
    private final ArticleRepository articleRepository;

    public PanierController(PanierService panierService, JwtTokenUtil jwtTokenUtil, UtilisateurRepository utilisateurRepository, ArticleRepository articleRepository) {
        this.panierService = panierService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.utilisateurRepository = utilisateurRepository;
        this.articleRepository = articleRepository;
    }

    @PostMapping("/add")
    public Panier add(@RequestHeader("Authorization") String token,
                      @RequestParam int articleId,
                      @RequestParam int quantite) {
        Utilisateur user = jwtTokenUtil.getUserFromToken(token, utilisateurRepository);
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article non trouvé"));

        return panierService.addToPanier(user, article, quantite);
    }

    @GetMapping
    public Panier get(@RequestHeader("Authorization") String token) {
        Utilisateur user = jwtTokenUtil.getUserFromToken(token, utilisateurRepository);
        return panierService.getPanier(user);
    }
    @PutMapping("/update")
    public Panier update(@RequestHeader("Authorization") String token,
                         @RequestParam Long itemId,
                         @RequestParam int quantite) {
        Utilisateur user = jwtTokenUtil.getUserFromToken(token, utilisateurRepository);
        return panierService.updateQuantite(user, itemId, quantite);
    }

    @DeleteMapping("/remove/{itemId}")
    public void remove(@RequestHeader("Authorization") String token, @PathVariable Long itemId) {
        Utilisateur user = jwtTokenUtil.getUserFromToken(token, utilisateurRepository);
        panierService.removeFromPanier(user, itemId);
    }

    @PostMapping("/promo")
    public Panier applyPromo(@RequestHeader("Authorization") String token,
                             @RequestParam String code) {
        Utilisateur user = jwtTokenUtil.getUserFromToken(token, utilisateurRepository);
        return panierService.applyPromoCode(user, code);
    }


}
