package controller;

import model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import repository.ArticleRepository;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/articles")
public class ArticleAdminController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("")
    public String list(Model model) {
        // 1. Préparer les données pour la vue de contenu
        model.addAttribute("articles", articleRepository.findAll());

        // 2. Préparer les données pour le layout
        model.addAttribute("pageTitle", "Gestion des Articles");
        model.addAttribute("contentPage", "articles/list.jsp");

        // 3. Retourner le layout
        return "layout";
    }

    @GetMapping({"/new", "/edit"})
    public String showForm(@RequestParam(required = false) Integer id, Model model) {
        Article article;
        String pageTitle;

        if (id != null) {
            article = articleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID invalide:" + id));
            pageTitle = "Modifier l'Article";
        } else {
            article = new Article();
            pageTitle = "Nouvel Article";
        }
        model.addAttribute("article", article);

        // Données pour les listes déroulantes
        List<String> categories = Arrays.asList("Vêtements", "Chaussures", "Accessoires", "Électronique");
        List<String> types = Arrays.asList("T-shirt", "Pantalon", "Baskets", "Montre", "Smartphone");
        model.addAttribute("categories", categories);
        model.addAttribute("types", types);

        // Préparer les données pour le layout
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("contentPage", "articles/form.jsp");

        return "layout";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("article") Article article, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // En cas d'erreur, il faut re-fournir TOUTES les données nécessaires à la vue et au layout

            // Données pour le formulaire
            List<String> categories = Arrays.asList("Vêtements", "Chaussures", "Accessoires", "Électronique");
            List<String> types = Arrays.asList("T-shirt", "Pantalon", "Baskets", "Montre", "Smartphone");
            model.addAttribute("categories", categories);
            model.addAttribute("types", types);

            // Données pour le layout
            model.addAttribute("pageTitle", article.getReference() == 0 ? "Nouvel Article" : "Modifier l'Article");
            model.addAttribute("contentPage", "articles/form.jsp");

            return "layout"; // On retourne au layout, qui affichera le formulaire avec les erreurs
        }

        articleRepository.save(article);
        return "redirect:/admin/articles"; // La redirection ne change pas
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Integer id) {
        articleRepository.deleteById(id);
        return "redirect:/admin/articles"; // La redirection ne change pas
    }
}