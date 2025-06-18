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

        // --- 4. Récupérer les commandes "en attente" (ou le statut que vous préférez) ---
        // Exemples de statuts: "EN_ATTENTE", "EN_COURS", "A_TRAITER"
        model.addAttribute("commandesEnAttente", commandeRepository.findByStatus("EN_ATTENTE"));

        return "accueil";
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

