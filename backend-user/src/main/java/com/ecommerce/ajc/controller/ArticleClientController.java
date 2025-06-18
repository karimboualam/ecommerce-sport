package com.ecommerce.ajc.controller;

import com.ecommerce.ajc.service.ArticleService;
import model.Article;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/articles")
@PreAuthorize("hasRole('CLIENT')")
public class ArticleClientController {

    private final ArticleService articleService;

    public ArticleClientController(ArticleService articleService) {
        this.articleService = articleService;
    }

    /**
     * Récupérer tous les articles
     */
    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    /**
     *  Récupérer un article par son ID
     */
    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable Integer id) {
        return articleService.getArticleById(id);
    }

    /**
     *  Filtrer les articles par catégorie
     */
    @GetMapping("/categorie/{categorie}")
    public List<Article> findByCategorie(@PathVariable String categorie) {
        return articleService.findByCategorie(categorie);
    }

    /**
     *  Rechercher des articles par mot-clé (nom ou description)
     */
    @GetMapping("/search")
    public List<Article> searchArticles(@RequestParam String keyword) {
        return articleService.searchArticles(keyword);
    }

    /**
     * Filtrage multiple (prix, marque, couleur)
     */
    @GetMapping("/filter")
    public List<Article> filterArticles(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String marque,
            @RequestParam(required = false) String couleur
    ) {
        return articleService.filterArticles(minPrice, maxPrice, marque, couleur);
    }

    /**
     *  Afficher les articles en vedette (ex: top 5)
     */
    @GetMapping("/featured")
    public List<Article> getFeaturedArticles() {
        return articleService.getFeaturedArticles();
    }
}
