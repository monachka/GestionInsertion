package com.dame.uasz.Controller;

import com.dame.uasz.Model.AccountCredentials;
import com.dame.uasz.Model.User;

import com.dame.uasz.service.JwtService;
import com.dame.uasz.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	private JwtService jwtService;



	@RequestMapping(value="/pageConnect", method= RequestMethod.GET)
	public String loginPage() {
		return "sign-in"; // Retourne le nom de la vue de connexion
	}

	// Gère la soumission du formulaire de connexion
	@RequestMapping(value="/connect", method= RequestMethod.POST)
	public String login(User user, Model model) {

		// Obtient l'utilisateur à partir du service UserService
		User users = userService.findByUsername(user.getUsername()).orElse(null);

		AccountCredentials credentials = new AccountCredentials();
		credentials.setEmail(user.getEmail());
		credentials.setUsername(user.getUsername());
		credentials.setPassword(user.getPassword());
		credentials.setRole(user.getRole());

		UsernamePasswordAuthenticationToken creds =
				new UsernamePasswordAuthenticationToken(
						credentials.getUsername(),
						credentials.getPassword());

		Authentication auth = authenticationManager.authenticate(creds);

		// Generate token
		String jwts = jwtService.getToken(auth.getName());

		model.addAttribute("token", jwts);

		if (users!=null){
			switch (users.getRole()) {
				case "USER":
					// Redirection vers la page home pour les utilisateurs
					return "redirect:/demandes/user/home";
				case "RESP":
					// Redirection vers la page dashboardResp pour les responsables
					return "redirect:/demandes/dashboardResp";
				case "ADMIN":
					// Redirection vers la page dashboard pour les administrateurs
					return "redirect:/demandes/dashboard";
				default:
					// Redirection vers la page d'accueil par défaut pour les autres cas
					return "redirect:/demandes/user/home";
			}
		}
		 else {
			// Authentification échouée, redirige vers la page de connexion avec un message d'erreur
			model.addAttribute("error", "Authentication failed. Please try again.");
			return "redirect:/loginPage"; // Redirige vers la page de connexion
		}
	}

	// Gère la déconnexion de l'utilisateur
	@RequestMapping(value="/disconnect", method= RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		// Invalidation de la session
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		// Déconnexion de l'utilisateur de Spring Security
		SecurityContextHolder.clearContext();

		// Redirige vers la page de connexion ou toute autre page appropriée après la déconnexion
		return "redirect:/pageConnect";
	}


}



