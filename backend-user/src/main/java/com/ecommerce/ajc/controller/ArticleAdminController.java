package com.ecommerce.ajc.controller;

import com.ecommerce.ajc.model.Article;
import com.ecommerce.ajc.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/articles")
public class ArticleAdminController {

    private final ArticleService articleService;

    public ArticleAdminController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.saveArticle(article);
    }

    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody Article article) {
        article.setReference(id.intValue());
        return articleService.saveArticle(article);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }

    @GetMapping("/exists/{id}")
    public boolean articleExists(@PathVariable Long id) {
        return articleService.articleExists(id);
    }
}
