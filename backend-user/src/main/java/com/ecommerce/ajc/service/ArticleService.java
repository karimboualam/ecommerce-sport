package com.ecommerce.ajc.service;

import model.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getAllArticles();
    Article getArticleById(Integer id);
    List<Article> findByCategorie(String categorie);
    List<Article> searchArticles(String keyword);
    List<Article> filterArticles(Double minPrice, Double maxPrice, String marque, String couleur);
    List<Article> getFeaturedArticles();
}
