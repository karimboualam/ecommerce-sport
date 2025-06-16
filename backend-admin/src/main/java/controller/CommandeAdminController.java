package controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import repository.ArticleRepository;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin/commandes")
public class CommandeAdminController {
/*
    @Autowired
    private CommandeRepository commandeRepository;

    private final String[] STATUTS = {"EN_ATTENTE", "EN_COURS_DE_PREPARATION", "EXPEDIEE", "LIVREE", "ANNULEE"};

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("commandes", commandeRepository.findAll());
        return "commandes/list";
    }

    @GetMapping("/details")
    public String details(Model model, @RequestParam Long id) {
        commandeRepository.findById(id).ifPresent(commande -> {
            model.addAttribute("commande", commande);
            model.addAttribute("statuts", STATUTS);
        });
        return "commandes/details";
    }

    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam Long id, @RequestParam String status) {
        commandeRepository.findById(id).ifPresent(commande -> {
            commande.setStatus(status);
            commandeRepository.save(commande);
        });
        return "redirect:/admin/commandes/details?id=" + id;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        commandeRepository.deleteById(id);
        return "redirect:/admin/commandes";
    }*/
}
