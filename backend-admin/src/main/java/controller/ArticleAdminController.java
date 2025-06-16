package controller;
import model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import repository.ArticleRepository;
import javax.validation.Valid;

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

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("article", new Article());
        return "articles/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("article") Article article, BindingResult result) {
        if (result.hasErrors()) {
            return "articles/form";
        }
        articleRepository.save(article);
        return "redirect:/admin/articles";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam Long id, Model model) {
        articleRepository.findById(id).ifPresent(article -> {

            model.addAttribute("article", article);
        });

        return "articles/form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        articleRepository.deleteById(id);
        return "redirect:/admin/articles";
    }

}