package com.dame.uasz.Controller;

import java.util.List;
import java.util.Optional;

import com.dame.uasz.Model.User;
import com.dame.uasz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

/*
    // Affiche la liste de tous les utilisateurs
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userList"; // Nom de la page Thymeleaf à afficher
    }

    // Affiche le formulaire pour créer un nouvel utilisateur
    @GetMapping("/users/new")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "sign-up"; // Nom de la page Thymeleaf à afficher
    }

    // Enregistre un nouvel utilisateur
    @PostMapping("/users")
    public String createUser(@RequestBody User user) {
        userService.createUser(user);
        return "redirect:/users";
    }

    // Affiche le formulaire pour mettre à jour un utilisateur
    @GetMapping("/users/edit/{id}")
    public String showUpdateUserForm(@PathVariable("id") Long id, Model model) {
        Optional<User> user = userService.getUserById(id);
        user.ifPresent(u -> model.addAttribute("user", u));
        return "updateUser"; // Nom de la page Thymeleaf à afficher
    }

    // Met à jour un utilisateur
    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable("id") Long id, @RequestBody User userDetails) {
        userService.updateUser(id, userDetails);
        return "redirect:/users";
    }

    // Supprime un utilisateur
    @DeleteMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }*/
}
