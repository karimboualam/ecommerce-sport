// ✅ 19. ArticleController.java
package com.ecommerce.ajc.controller;

import com.ecommerce.ajc.model.Article;
import com.ecommerce.ajc.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//@RestController
//@RequestMapping("/api/articles")
public class ArticleController {
/*
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        Article article = articleService.getArticleById(id);
        return article != null ? ResponseEntity.ok(article) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.saveArticle(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article details) {
        Article article = articleService.getArticleById(id);
        if (article == null) return ResponseEntity.notFound().build();

        article.setNom(details.getNom());
        article.setDescription(details.getDescription());
        article.setPrix(details.getPrix());
        article.setStock(details.getStock());
        // etc. (autres champs à mettre à jour si besoin)

        return ResponseEntity.ok(articleService.saveArticle(article));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        if (!articleService.articleExists(id)) return ResponseEntity.notFound().build();
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<Article>> getByCategorie(@PathVariable String categorie) {
        return ResponseEntity.ok(articleService.findByCategorie(categorie));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Article>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(articleService.searchArticles(keyword));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Article>> filter(@RequestParam(required = false) Double minPrice,
                                                @RequestParam(required = false) Double maxPrice,
                                                @RequestParam(required = false) String marque,
                                                @RequestParam(required = false) String couleur) {
        return ResponseEntity.ok(articleService.filterArticles(minPrice, maxPrice, marque, couleur));
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<Article> updateStock(@PathVariable Long id, @RequestParam int quantity) {
        Article article = articleService.getArticleById(id);
        if (article == null) return ResponseEntity.notFound().build();
        article.setStock(article.getStock() + quantity);
        return ResponseEntity.ok(articleService.saveArticle(article));
    }

    @GetMapping("/featured")
    public ResponseEntity<List<Article>> featured() {
        return ResponseEntity.ok(articleService.getFeaturedArticles());
    }*/
}
