package test;

import model.Article;
import repository.ArticleRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

public class TestArticle {

    public static void main(String[] args) {
        testInsert();
        testFindAll();
    }

    static void testInsert() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ArticleRepository articleRepository = context.getBean(ArticleRepository.class);

        Article article = new Article();
        article.setNom("Clavier Mécanique TKL");
        article.setDescription("Un clavier compact et performant pour les gamers.");
        article.setPrix(95.00);
        article.setStock(50);

        articleRepository.save(article);
        System.out.println("Article inséré avec ID : " + article.getReference());

        context.close();
    }

    static void testFindAll() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ArticleRepository articleRepository = context.getBean(ArticleRepository.class);

        List<Article> articles = articleRepository.findAll();
        System.out.println("\n--- Liste des articles (" + articles.size() + ") ---");

        for (Article article : articles) {
            System.out.println(String.format("ID: %d | Nom: %s | Prix: %.2f€",
                    article.getReference(), article.getNom(), article.getPrix()));
        }

        context.close();
    }
}