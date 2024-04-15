package com.dame.uasz.service;

import java.util.List;
import java.util.Optional;

import com.dame.uasz.Model.User;
import com.dame.uasz.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Méthode pour créer un utilisateur
    public User createUser(User user) {
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    // Méthode pour récupérer tous les utilisateurs
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    // Méthode pour récupérer un utilisateur par son identifiant
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Méthode pour mettre à jour un utilisateur
    public User updateUser(Long id, User userDetails) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setRole(userDetails.getRole());
            user.setEmail(userDetails.getEmail());

            String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(encodedPassword);

            return userRepository.save(user);
        } else {
            // Gérer l'absence de l'utilisateur avec l'identifiant spécifié
            return null;
        }
    }

    // Méthode pour supprimer un utilisateur
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Méthode pour trouver un utilisateur par nom d'utilisateur
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Méthode pour trouver un utilisateur par email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
