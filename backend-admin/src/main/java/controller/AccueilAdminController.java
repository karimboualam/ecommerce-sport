package controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import repository.ArticleRepository;
import repository.CommandeRepository;
import repository.UtilisateurRepository;
import security.CustomUserDetails;

@Controller
public class AccueilAdminController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;


    @GetMapping("/")
    public String showDashboard(Model model, Authentication authentication) {

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        model.addAttribute("utilisateurConnecte", userDetails.getUtilisateur());

        long totalArticles = articleRepository.count();
        long totalCommandes = commandeRepository.count();
        long totalClients = utilisateurRepository.countClients();

        model.addAttribute("totalArticles", totalArticles);
        model.addAttribute("totalCommandes", totalCommandes);
        model.addAttribute("totalClients", totalClients);


        model.addAttribute("commandesEnAttente", commandeRepository.findByStatus("EN_ATTENTE"));

        model.addAttribute("pageTitle", "Tableau de Bord");

        model.addAttribute("contentPage", "accueil.jsp");

        return "layout";
    }
    /*@GetMapping("/")
public String showDashboard(Model model, Authentication authentication) {
    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
    model.addAttribute("utilisateurConnecte", userDetails.getUtilisateur());
    return "accueil";
}*/


    @GetMapping("/admin")
    public String redirectToDashboard() {
        return "redirect:/";
    }
}

