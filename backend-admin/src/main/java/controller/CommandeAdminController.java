package controller;

import model.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.CommandeService;
import util.PdfExportUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/commandes")
public class CommandeAdminController {

    @Autowired
    private CommandeService commandeService;

    // La liste des statuts possibles est maintenant une constante
    private static final List<String> STATUTS_POSSIBLES = Arrays.asList("EN_ATTENTE", "EN_COURS", "EXPEDIEE", "LIVREE", "ANNULEE");

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("commandes", commandeService.findAll());

        // 2. Préparer les données pour le layout
        model.addAttribute("pageTitle", "Gestion des Commandes");
        model.addAttribute("contentPage", "commandes/list.jsp");

        // 3. Retourner le layout
        return "layout";
    }

    @GetMapping("/details")
    public String details(Model model, @RequestParam Long id) {
        // On utilise .orElseThrow pour une gestion d'erreur plus propre
        Commande commande = commandeService.findByIdWithLignes(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de commande invalide:" + id));

        // 1. Préparer les données pour le contenu
        model.addAttribute("commande", commande);
        model.addAttribute("statuts", STATUTS_POSSIBLES);

        // 2. Préparer les données pour le layout
        model.addAttribute("pageTitle", "Détail Commande #" + commande.getId());
        model.addAttribute("contentPage", "commandes/details.jsp");

        // 3. Retourner le layout
        return "layout";
    }

    // Les méthodes qui font une redirection n'ont PAS besoin d'être changées !
    // Elles ne retournent pas de vue, donc le layout ne s'applique pas.
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




    // GENERER LE PDF
    @GetMapping("/export/pdf")
    public void exportPdf(HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=commandes.pdf");

            List<Commande> commandes = commandeService.findAllWithLignesEtUtilisateurs();
            PdfExportUtil.exportCommandes(commandes, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'export PDF");
        }
    }

}