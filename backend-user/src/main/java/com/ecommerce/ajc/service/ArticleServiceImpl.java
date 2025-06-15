package com.ecommerce.ajc.service;

import com.ecommerce.ajc.model.Article;
import com.ecommerce.ajc.repository.ArticleRepository;
import com.ecommerce.ajc.service.ArticleService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticleServiceImpl {}
/* implements ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    @Override
    public List<Article> findByCategorie(String categorie) {
        return articleRepository.findByCategorie(categorie);
    }

    @Override
    public List<Article> searchArticles(String keyword) {
        return articleRepository.findByNomContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }
//}

    @Override
    public boolean articleExists(Long id) {
        return articleRepository.existsById(id);
    }

    @Override
    public List<Article> filterArticles(Double minPrice, Double maxPrice, String marque, String couleur) {
        List<Article> allArticles = articleRepository.findAll();

        return allArticles.stream()
                .filter(article -> minPrice == null || article.getPrix() >= minPrice)
                .filter(article -> maxPrice == null || article.getPrix() <= maxPrice)
                .filter(article -> marque == null || article.getMarque().equalsIgnoreCase(marque))
                .filter(article -> couleur == null || article.getCouleur().equalsIgnoreCase(couleur))
                .collect(Collectors.toList());
    }

    @Override
    public List<Article> getFeaturedArticles() {
        // Implémentation basique - pourrait utiliser un champ 'featured' dans Article
        return articleRepository.findAll().stream()
                .sorted((a1, a2) -> Double.compare(a2.getPrix(), a1.getPrix())) // Articles les plus chers
                .limit(5)
                .collect(Collectors.toList());
    }
}*/