package tn.esprit.twin1.EducationSpringApp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.twin1.EducationSpringApp.entities.User;
import tn.esprit.twin1.EducationSpringApp.services.impl.CustomUserDetail;

import java.security.Principal;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")



public class UserController {
    @Autowired
    private CustomUserDetail userService;
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal)
    {

        return (User) this.userService.loadUserByUsername(principal.getName());

    }

}
