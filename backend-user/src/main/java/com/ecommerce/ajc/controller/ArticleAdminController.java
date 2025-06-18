// A SUPPRIMER
package com.ecommerce.ajc.controller;

import model.Article;
import com.ecommerce.ajc.service.ArticleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/articles")
public class ArticleAdminController {
/*
    private final ArticleService articleService;

    public ArticleAdminController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // 🔐 Sécurité supplémentaire côté méthode
    public Article createArticle(@RequestBody Article article) {

        return articleService.saveArticle(article);
    }
    @PostMapping("/bulk")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Article> createMultipleArticles(@RequestBody List<Article> articles) {
        return articleService.saveAllArticles(articles);
    }


    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody Article article) {
        article.setReference(id.intValue());
        return articleService.saveArticle(article);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Integer  id) {
        articleService.deleteArticle(id);
    }

    @GetMapping("/exists/{id}")
    public boolean articleExists(@PathVariable Integer  id) {
        return articleService.articleExists(id);
    }

*/
}
