// ✅ 16. ArticleService.java
package com.ecommerce.ajc.service;

import com.ecommerce.ajc.model.Article;
import com.ecommerce.ajc.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(Long id) {
        return articleRepository.findById(id.intValue()).orElse(null);
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id.intValue());
    }

    public boolean articleExists(Long id) {
        return articleRepository.existsById(id.intValue());
    }

    public List<Article> findByCategorie(String categorie) {
        return articleRepository.findByCategorie(categorie);
    }

    public List<Article> searchArticles(String keyword) {
        return articleRepository.searchByKeyword(keyword);
    }

    public List<Article> filterArticles(Double minPrice, Double maxPrice, String marque, String couleur) {
        return articleRepository.findAll().stream()
                .filter(a -> minPrice == null || a.getPrix() >= minPrice)
                .filter(a -> maxPrice == null || a.getPrix() <= maxPrice)
                .filter(a -> marque == null || a.getMarque().equalsIgnoreCase(marque))
                .filter(a -> couleur == null || a.getCouleur().equalsIgnoreCase(couleur))
                .collect(Collectors.toList());
    }

    public List<Article> getFeaturedArticles() {
        return articleRepository.findAll().stream()
                .sorted((a1, a2) -> Double.compare(a2.getPrix(), a1.getPrix()))
                .limit(5)
                .collect(Collectors.toList());
    }
}