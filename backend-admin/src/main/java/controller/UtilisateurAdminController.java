package controller;

import security.RoleEnum;
import model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String list(Model model) {
        // 1. Préparer les données pour le contenu
        model.addAttribute("utilisateurs", utilisateurService.findAll());

        // 2. Préparer les données pour le layout
        model.addAttribute("pageTitle", "Gestion des Utilisateurs");
        model.addAttribute("contentPage", "utilisateurs/list.jsp");

        // 3. Retourner le layout
        return "layout";
    }

    @GetMapping({"/new", "/edit"})
    public String showForm(@RequestParam(required = false) Long id, Model model) {
        Utilisateur utilisateur;
        String pageTitle;

        if (id != null) {
            utilisateur = utilisateurService.findById(id).orElseThrow(() -> new IllegalArgumentException("ID invalide:" + id));
            utilisateur.setPassword("");
            pageTitle = "Modifier l'Utilisateur";
        } else {
            utilisateur = new Utilisateur();
            pageTitle = "Nouvel Administrateur";
        }

        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("roles", RoleEnum.values());

        // Préparer les données pour le layout
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("contentPage", "utilisateurs/form.jsp");

        return "layout";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // En cas d'erreur, il faut re-fournir les données au layout et à la vue
            model.addAttribute("roles", RoleEnum.values());
            model.addAttribute("pageTitle", utilisateur.getId() == null ? "Nouvel Administrateur" : "Modifier l'Utilisateur");
            model.addAttribute("contentPage", "utilisateurs/form.jsp");
            return "layout";
        }

        if (utilisateur.getId() != null) { // Modification
            if (utilisateur.getPassword() == null || utilisateur.getPassword().isEmpty()) {
                Utilisateur existingUser = utilisateurService.findById(utilisateur.getId()).get();
                utilisateur.setPassword(existingUser.getPassword());
            } else {
                utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
            }
        } else { // Création
            utilisateur.setRole(RoleEnum.ROLE_ADMIN);
            utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
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