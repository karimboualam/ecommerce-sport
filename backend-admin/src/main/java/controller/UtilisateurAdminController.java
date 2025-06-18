package controller;

import security.RoleEnum;
import model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.UtilisateurService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/utilisateurs")
public class UtilisateurAdminController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("utilisateurs", utilisateurService.findAll());
        return "utilisateurs/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if (id != null) {
            utilisateurService.findById(id).ifPresent(user -> model.addAttribute("utilisateur", user));
        } else {
            model.addAttribute("utilisateur", new Utilisateur());
        }
        model.addAttribute("roles", RoleEnum.values());
        return "utilisateurs/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", RoleEnum.values());
            return "utilisateurs/form";
        }
        utilisateurService.save(utilisateur);
        return "redirect:/admin/utilisateurs";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        utilisateurService.deleteById(id);
        return "redirect:/admin/utilisateurs";
    }
}
