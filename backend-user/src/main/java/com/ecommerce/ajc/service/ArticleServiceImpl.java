package com.ecommerce.ajc.service;

import model.Article;
import repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Article> findByCategorie(String categorie) {
        return articleRepository.findByCategorie(categorie);
    }

    @Override
    public List<Article> searchArticles(String keyword) {
        return articleRepository.findByNomContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

    @Override
    public List<Article> filterArticles(Double minPrice, Double maxPrice, String marque, String couleur) {
        return articleRepository.findAll().stream()
                .filter(a -> minPrice == null || a.getPrix() >= minPrice)
                .filter(a -> maxPrice == null || a.getPrix() <= maxPrice)
                .filter(a -> marque == null || a.getMarque().equalsIgnoreCase(marque))
                .filter(a -> couleur == null || a.getCouleur().equalsIgnoreCase(couleur))
                .collect(Collectors.toList());
    }

    @Override
    public List<Article> getFeaturedArticles() {
        return articleRepository.findAll().stream()
                .sorted((a1, a2) -> Double.compare(a2.getPrix(), a1.getPrix()))
                .limit(5)
                .collect(Collectors.toList());
    }
}
