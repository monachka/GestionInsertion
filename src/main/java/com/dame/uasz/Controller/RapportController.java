package com.dame.uasz.Controller;

import com.dame.uasz.Model.Demande;
import com.dame.uasz.Model.Rapport;
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

import static java.lang.String.valueOf;

@Controller
public class RapportController {

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



    @RequestMapping(value = "/rapports/all", method = RequestMethod.GET)
    public String getAllRapports(Model model){
        List<Rapport> rapports = rapportService.getAllRapports();
        model.addAttribute("rapports", rapports);
        return "rapports";
    }

    @RequestMapping(value = "/rapports/{id}", method = RequestMethod.GET)
    public String getRapport(@PathVariable("id") Long id, Model model){
        Rapport rapport = rapportService.getRapport(id);
        System.out.println("current id:  "+id);
        System.out.println("current rapport:  "+rapport.getUser().getUsername());
        List<Demande> demandes = rapport.getDemandes();
        System.out.println("current demandes:  "+demandes.isEmpty());
        model.addAttribute("demandes", demandes);
        model.addAttribute("rapport", rapport);
        return "rapportDetails";
    }

    @RequestMapping(value = "/rapports/createMensuel", method = RequestMethod.POST)
    public String createRapportMensuel(int mois){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        System.out.println("current user:  "+currentUserName);
        User user = userRepository.findByUsername(currentUserName).orElse(null);
        System.out.println("current user:  "+user.getUsername());
        List<Demande> demandes = demandeRepository.findAll();

        rapportService.createRapportMensel(user, demandes, mois);
        return "redirect:/rapports/all";
    }

    @RequestMapping(value = "/rapports/createAnnuel", method = RequestMethod.POST)
    public String createRapportAnnuel(@RequestParam("an") Integer annee){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        System.out.println("current user:  "+currentUserName);
        User user = userRepository.findByUsername(currentUserName).orElse(null);
        System.out.println("current user:  "+user.getUsername());
        List<Demande> demandes = demandeRepository.findAll();

        rapportService.createRapportAnnuel(user, demandes, annee);
        return "redirect:/rapports/all";
    }

    @RequestMapping(value = "/rapports/delete/{id}", method = RequestMethod.GET)
    public String deleteRapport(@PathVariable("id") Long id){
        rapportService.deleteRapport(id);
        return "redirect:/rapports/all";
    }
}
