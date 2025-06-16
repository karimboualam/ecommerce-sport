package com.ecommerce.ajc.controller;


import com.ecommerce.ajc.model.Article;
import com.ecommerce.ajc.service.ArticleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client/articles")
public class ArticleClientController {

    private final ArticleService articleService;

    public ArticleClientController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
//    @PreAuthorize("hasRole('CLIENT')") // 🔐 Sécurité supplémentaire côté méthode
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable Integer  id) {
        return articleService.getArticleById(id);
    }

    @GetMapping("/categorie/{categorie}")
    public List<Article> findByCategorie(@PathVariable String categorie) {
        return articleService.findByCategorie(categorie);
    }

    @GetMapping("/search")
    public List<Article> searchArticles(@RequestParam String keyword) {
        return articleService.searchArticles(keyword);
    }

    @GetMapping("/filter")
    public List<Article> filterArticles(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String marque,
            @RequestParam(required = false) String couleur
    ) {
        return articleService.filterArticles(minPrice, maxPrice, marque, couleur);
    }

    @GetMapping("/featured")
    public List<Article> getFeaturedArticles() {
        return articleService.getFeaturedArticles();
    }
}
