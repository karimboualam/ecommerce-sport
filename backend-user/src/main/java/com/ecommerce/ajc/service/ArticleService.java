// ✅ 16. ArticleService.java
package com.ecommerce.ajc.service;

import com.ecommerce.ajc.model.Article;

import java.util.List;

public interface ArticleService {

    List<Article> getAllArticles();

    Article getArticleById(Integer  id);

    Article saveArticle(Article article);

    void deleteArticle(Integer  id);

    boolean articleExists(Integer  id);

    List<Article> saveAllArticles(List<Article> articles);

    List<Article> findByCategorie(String categorie);

    List<Article> searchArticles(String keyword);

    List<Article> filterArticles(Double minPrice, Double maxPrice, String marque, String couleur);

    List<Article> getFeaturedArticles();
}
