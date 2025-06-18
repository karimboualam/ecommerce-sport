package controller; // Assurez-vous que le package est correct

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
        model.addAttribute("articles", articleRepository.findAll());
        return "articles/list";
    }

    @GetMapping({"/new", "/edit"})
    public String showForm(@RequestParam(required = false) Integer id, Model model) {

        Article article;
        if (id != null) {

            article = articleRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("ID d'article invalide:" + id));
        } else {
            article = new Article();
        }
        model.addAttribute("article", article);

        List<String> categories = Arrays.asList("Vêtements", "Chaussures", "Accessoires", "Électronique");
        List<String> types = Arrays.asList("T-shirt", "Pantalon", "Baskets", "Montre", "Smartphone");

        model.addAttribute("categories", categories);
        model.addAttribute("types", types);

        return "articles/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("article") Article article, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<String> categories = Arrays.asList("Vêtements", "Chaussures", "Accessoires", "Électronique");
            List<String> types = Arrays.asList("T-shirt", "Pantalon", "Baskets", "Montre", "Smartphone");
            model.addAttribute("categories", categories);
            model.addAttribute("types", types);

            return "articles/form";
        }
        articleRepository.save(article);
        return "redirect:/admin/articles";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Integer id) {
        articleRepository.deleteById(id);
        return "redirect:/admin/articles";
    }

}