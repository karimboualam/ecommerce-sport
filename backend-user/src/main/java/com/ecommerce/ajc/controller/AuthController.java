
package com.ecommerce.ajc.controller;

import model.Utilisateur;
import security.RoleEnum;
import com.ecommerce.ajc.service.UtilisateurService;
import com.ecommerce.ajc.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public Utilisateur register(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.register(utilisateur);
    }



    @PostMapping("/login")
    public String login(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.login(utilisateur);
    }
}
