package com.dame.uasz.Controller;

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
public class NotificationController {

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


    @RequestMapping(value = "/notifications/user", method = RequestMethod.GET)
    public String getAllNotificationsUser(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        User user = userRepository.findByUsername(currentUserName).orElse(null);

        List<Notification> notifications = notificationService.getAllNotificationsUser(user.getUsername());
        model.addAttribute("notifications", notifications);
        return "home";
    }

   /* @GetMapping("/demande/{id}")
    public String getAllNotificationsDemande(@PathVariable("id") Long id, Model model){
        List<Notification> notifications = notificationService.getAllNotificationsDemande(id);
        model.addAttribute("notifications", notifications);
        return "notificationList";
    }

    @GetMapping("/{id}")
    public String getNotification(@PathVariable("id") Long id, Model model){
        Notification notification = notificationService.getNotification(id);
        model.addAttribute("notification", notification);
        return "notificationDetail";
    }

    @PostMapping("/create")
    public String createNotification(@ModelAttribute("notification") Notification notification, @ModelAttribute("user") User user, @ModelAttribute("demande") Demande demande){
        notificationService.createNotification(notification, user, demande);
        return "redirect:/notifications/user/" + user.getUsername();
    }

    @PostMapping("/update/{id}")
    public String updateNotification(@PathVariable("id") Long id, @ModelAttribute("notification") Notification notification){
        notificationService.updateNotification(id, notification);
        return "redirect:/notifications/" + id;
    }*/


    @RequestMapping(value = "/notifications/delete/{id}", method = RequestMethod.GET)
    public String deleteNotification(@PathVariable("id") Long id){
        notificationService.deleteNotification(id);
        return "redirect:/demandes/user/home"; // Assurez-vous de remplacer user.getUsername() par la valeur appropriée
    }

}
