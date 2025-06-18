package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.CommandeService;


@Controller
@RequestMapping("/admin/commandes")
public class CommandeAdminController {

    @Autowired
    private CommandeService commandeService;

    private static final String[] STATUTS_POSSIBLES = {"EN_ATTENTE", "EN_COURS_DE_PREPARATION", "EXPEDIEE", "LIVREE", "ANNULEE"};

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("commandes", commandeService.findAll());
        return "commandes/list";
    }

    @GetMapping("/details")
    public String details(Model model, @RequestParam Long id) {
        commandeService.findByIdWithLignes(id).ifPresent(commande -> { // <-- Modifié
            model.addAttribute("commande", commande);
            model.addAttribute("statuts", STATUTS_POSSIBLES);
        });
        return "commandes/details";
    }

    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam Long commandeId, @RequestParam String status) {
        commandeService.findById(commandeId).ifPresent(commande -> {
            commande.setStatus(status);
            commandeService.save(commande);
        });
        return "redirect:/admin/commandes/details?id=" + commandeId;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        commandeService.deleteById(id);
        return "redirect:/admin/commandes";
    }
}