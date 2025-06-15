package com.ecommerce.ajc.service;

import com.ecommerce.ajc.model.Article;
import com.ecommerce.ajc.repository.ArticleRepository;
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
    public Article getArticleById(Integer  id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Integer  id) {
        articleRepository.deleteById(id);
    }

    @Override
    public boolean articleExists(Integer  id) {
        return articleRepository.existsById(id);
    }

    @Override
    public List<Article> saveAllArticles(List<Article> articles) {
        return articleRepository.saveAll(articles);
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
