package controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccueilAdminController {
    @GetMapping("/")
    public String showDashboard() {
        return "accueil";
    }

    @GetMapping("/admin")
    public String redirectToDashboard() {
        return "redirect:/";
    }
}

