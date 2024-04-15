package com.dame.uasz.Controller;

import com.dame.uasz.Model.Demande;
import com.dame.uasz.Model.Notification;
import com.dame.uasz.Model.User;
import com.dame.uasz.Repository.DemandeRepository;
import com.dame.uasz.Repository.NotificationRepository;
import com.dame.uasz.Repository.RapportRepository;
import com.dame.uasz.Repository.UserRepository;
import com.dame.uasz.service.DemandeService;
import com.dame.uasz.service.NotificationService;
import com.dame.uasz.service.RapportService;
import com.dame.uasz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DemandeController {

    @Autowired
    private DemandeService demandeService;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private RapportService rapportService;
    @Autowired
    private DemandeRepository demandeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private RapportRepository rapportRepository;


    @RequestMapping(value="/demandes/all", method= RequestMethod.GET)
    public String getAllDemandes(Model model){
        List<Demande> demandes = demandeService.getAllDemandes();
        model.addAttribute("demandes", demandes);

        return "demandesReceived";
    }

    @RequestMapping(value="/demandes/dashboardResp", method= RequestMethod.GET)
    public String getAllDemandesDashboardResp(Model model){
        List<Demande> demandes = demandeService.getAllDemandes();
        model.addAttribute("demandes", demandes);

        return "dashboardResp";
    }

    @RequestMapping(value = "/demandes/user", method = RequestMethod.GET)
    public String getAllDemandesUser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        User user = userRepository.findByUsername(currentUserName).orElse(null);

        if (user != null) {
            List<Demande> demandes = demandeService.getDemandesUser(user);
            model.addAttribute("demandes", demandes);
            List<Notification> notifications = notificationService.getAllNotificationsUser(user.getUsername());
            model.addAttribute("notifications", notifications);
        }

        return "demandesUser";
    }

    @RequestMapping(value = "/demandes/user/home", method = RequestMethod.GET)
    public String getAllDemandesUserHome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        User user = userRepository.findByUsername(currentUserName).orElse(null);

        if (user != null) {
            List<Demande> demandes = demandeService.getDemandesUser(user);
            model.addAttribute("demandes", demandes);
            List<Notification> notifications = notificationService.getAllNotificationsUser(user.getUsername());
            model.addAttribute("notifications", notifications);
        }

        return "home";
    }

    @RequestMapping(value = "/demandes/user/accepted", method = RequestMethod.GET)
    public String getDemandesUserAccepted(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        User user = userRepository.findByUsername(currentUserName).orElse(null);

        if (user!=null){
            List<Demande> demandes = demandeService.getDemandesUserAccepted(user);
            model.addAttribute("demandes", demandes);
            List<Notification> notifications = notificationService.getAllNotificationsUser(user.getUsername());
            model.addAttribute("notifications", notifications);
        }

        return "demandesUserAccepted";
    }

    @RequestMapping(value = "/demandes/user/rejected", method = RequestMethod.GET)
    public String getDemandesUserRejected(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        User user = userRepository.findByUsername(currentUserName).orElse(null);

        if (user!=null){
            List<Demande> demandes = demandeService.getDemandesUserRejected(user);
            model.addAttribute("demandes", demandes);
            List<Notification> notifications = notificationService.getAllNotificationsUser(user.getUsername());
            model.addAttribute("notifications", notifications);
        }

        return "demandesUserRejected";
    }

    @RequestMapping(value = "/demandes/user/validated", method = RequestMethod.GET)
    public String getDemandesUserValidated(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        User user = userRepository.findByUsername(currentUserName).orElse(null);

        if (user!=null){
            List<Demande> demandes = demandeService.getDemandesUserValidated(user);
            model.addAttribute("demandes", demandes);
            List<Notification> notifications = notificationService.getAllNotificationsUser(user.getUsername());
            model.addAttribute("notifications", notifications);
        }

        return "insertionsUserValidated";
    }

    @RequestMapping(value = "/demandes/user/invalidated", method = RequestMethod.GET)
    public String getDemandesUserInvalidated(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        User user = userRepository.findByUsername(currentUserName).orElse(null);

        if (user!=null){
            List<Demande> demandes = demandeService.getDemandesUserInvalidated(user);
            model.addAttribute("demandes", demandes);
            List<Notification> notifications = notificationService.getAllNotificationsUser(user.getUsername());
            model.addAttribute("notifications", notifications);
        }

        return "insertionsUserAwaiting";
    }

    @RequestMapping(value = "/demandes/accepted", method = RequestMethod.GET)
    public String getDemandesAccepted(Model model){
        List<Demande> demandes = demandeService.getDemandesAccepted();
        model.addAttribute("demandes", demandes);

        return "demandesAccepted";
    }

    @RequestMapping(value = "/demandes/rejected", method = RequestMethod.GET)
    public String getDemandesRejected(Model model){
        List<Demande> demandes = demandeService.getDemandesRejected();
        model.addAttribute("demandes", demandes);

        return "demandesRejected";
    }

    @RequestMapping(value = "/demandes/validated", method = RequestMethod.GET)
    public String getDemandesValidated(Model model){
        List<Demande> demandes = demandeService.getDemandesValidated();
        model.addAttribute("demandes", demandes);
        return "insertionsValidated";
    }

    @RequestMapping(value = "/demandes/invalidated", method = RequestMethod.GET)
    public String getDemandesInvalidated(Model model){
        List<Demande> demandes = demandeService.getDemandesInvalidated();
        model.addAttribute("demandes", demandes);
        return "insertionsAwaiting";
    }

    @RequestMapping(value = "/demandes/dashboard", method = RequestMethod.GET)
    public String getDemandesInvalidatedDashboard(Model model){
        List<Demande> demandes = demandeService.getDemandesInvalidated();
        model.addAttribute("demandes", demandes);

        return "dashboard";
    }

    @RequestMapping(value = "/demandes/create", method = RequestMethod.POST)
    public String createDemande(Demande demande){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        User user = userRepository.findByUsername(currentUserName).orElse(null);

        demandeService.createDemande(demande, user);

        return "redirect:/demandes/user";
    }

    @RequestMapping(value = "/demandes/update/{id}", method = RequestMethod.POST)
    public String updateDemande(@PathVariable("id") Long id, Demande demande){
        demandeService.updateDemande(id, demande);

        return "redirect:/demandes/user";
    }

    @RequestMapping(value = "/demandes/delete/{id}", method = RequestMethod.GET)
    public String deleteDemande(@PathVariable("id") Long id){
        demandeService.deleteDemande(id);

        return "redirect:/demandes/user";
    }

    @RequestMapping(value = "/demandes/accept/{id}", method = RequestMethod.GET)
    public String acceptDemande(@PathVariable("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userRepository.findByUsername(currentUserName).orElse(null);

        Demande demande = demandeRepository.findById(id).get();
        demandeService.setDemandeAccepted(demande, user);

        Notification notification = new Notification();
        notification.setTitre(demande.getAction().getType());
        notification.setLibelle("Votre demande d'insertion "+demande.getTitre()+" créé le "+demande.getDateCreation()+" a été acceptée!");
        notificationService.createNotification(notification, user, demande);

        return "redirect:/demandes/all";
    }

    @RequestMapping(value = "/demandes/reject/{id}", method = RequestMethod.GET)
    public String rejectDemande(@PathVariable("id") Long id, String motif){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userRepository.findByUsername(currentUserName).orElse(null);

        Demande demande = demandeRepository.findById(id).get();
        demandeService.setDemandeRejected(demande, user);

        Notification notification = new Notification();
        notification.setTitre(demande.getAction().getType());
        notification.setLibelle("Votre demande d'insertion "+demande.getTitre()+" créé le "+demande.getDateCreation()+" a été refusée!\n" +
                "Motif: "+motif);
        notificationService.createNotification(notification, user, demande);

        return "redirect:/demandes/all";
    }

    @RequestMapping(value = "/demandes/validate/{id}", method = RequestMethod.GET)
    public String validateDemande(@PathVariable("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userRepository.findByUsername(currentUserName).orElse(null);

        Demande demande = demandeRepository.findById(id).get();
        demandeService.setDemandeValidated(demande, user);

        Notification notification = new Notification();
        notification.setTitre(demande.getAction().getType());
        notification.setLibelle("Votre demande d'insertion "+demande.getTitre()+" créé le "+demande.getDateCreation()+" a été insérée avec succès!");
        notificationService.createNotification(notification, user, demande);

        return "redirect:/demandes/validated";
    }

    @RequestMapping(value = "/demandes/invalidate/{id}", method = RequestMethod.GET)
    public String invalidateDemande(@PathVariable("id") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userRepository.findByUsername(currentUserName).orElse(null);

        Demande demande = demandeRepository.findById(id).get();
        demandeService.setDemandeInvalidated(demande, user);

        return "redirect:/demandes/invalidated";
    }

    @RequestMapping(value = "/demandes/modify/{id}", method = RequestMethod.GET)
    public String modifyDemande(@PathVariable("id") Long id, String motif){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User user = userRepository.findByUsername(currentUserName).orElse(null);

        Demande demande = demandeRepository.findById(id).get();
        demandeService.setDemandeModification(demande, user);

        Notification notification = new Notification();
        notification.setTitre(demande.getAction().getType());
        notification.setLibelle("Votre demande d'insertion "+demande.getTitre()+" créé le "+demande.getDateCreation()+" nécessite des modifications!\n" +
                "Objet modification: "+motif);
        notificationService.createNotification(notification, user, demande);

        return "redirect:/demandes/invalidated";
    }

}
