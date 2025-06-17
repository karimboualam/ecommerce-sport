package repository;

import model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    Article findByReference(int reference);
    List<Article> findByCategorie(String categorie);
    List<Article> findByPrixBetween(Double min, Double max);
    List<Article> findByMarque(String marque);
    List<Article> findByCouleur(String couleur);

    List<Article> findByNomContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String nom, String description);

    @Query("SELECT a FROM Article a WHERE LOWER(a.nom) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(a.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Article> searchByKeyword(String keyword);
}
