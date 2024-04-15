package com.dame.uasz.service;


import com.dame.uasz.Model.Demande;
import com.dame.uasz.Model.Rapport;
import com.dame.uasz.Model.User;
import com.dame.uasz.Repository.DemandeRepository;
import com.dame.uasz.Repository.RapportRepository;
import com.dame.uasz.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormatSymbols;
import java.util.*;


@Service
public class RapportService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DemandeRepository demandeRepository;
    @Autowired
    private RapportRepository rapportRepository;

    public List<Rapport> getAllRapports(){
        return rapportRepository.findAll();
    }

    public Rapport getRapport(Long id){
        return rapportRepository.findById(id).get();
    }

    public Rapport createRapportMensel(User user, List<Demande> demandes, int mois) {
        Rapport rapport = new Rapport();
        // Mettre à jour les attributs du rapport
        rapport.setDateCreation(new Date());
        rapport.setUser(user);

        if (demandes!=null)
        {
            // Initialisation de la liste pour stocker les demandes du mois spécifié
            List<Demande> demandesDuMois = new ArrayList<>();

            // Parcourir toutes les demandes
            for (Demande demande : demandes) {
                // Récupérer la date de création de la demande
                Date dateCreation = demande.getDateCreation();

                // Utiliser un objet Calendar pour extraire le mois de la date de création
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateCreation);
                int moisCreation = cal.get(Calendar.MONTH) + 1; // Ajouter 1 car les mois commencent à 0

                // Utiliser un tableau pour mapper les noms des mois aux valeurs numériques
                String[] moisNoms = new DateFormatSymbols(Locale.FRENCH).getMonths();

                // Obtenir l'index du mois spécifié dans le tableau (index + 1 car les mois commencent à 0)
                int moisIndex = Arrays.asList(moisNoms).indexOf(mois) + 1;

                // Comparer le mois de création avec l'index du mois spécifié
                System.out.println("moisCre: "+moisCreation+"   moisIn: "+moisIndex);
                System.out.println(moisCreation==moisIndex);
                if (moisCreation == mois) {
                    // Si les mois correspondent, ajouter la demande à la liste des demandes du mois
                    List<Demande> demande1 = new ArrayList<>();
                    rapport.setDemandes(demande1);
                    rapport.getDemandes().add(demande);
                    demandesDuMois.add(demande);
                    System.out.println("demandes mois: "+demandesDuMois.isEmpty());
                }
            }
            System.out.println("demandes mois: "+demandesDuMois.isEmpty());
            rapport.setTitre("Rapport du mois de "+mois);
            rapport.setLibelle("Rapport généré par "+user.getEmail());
            rapport.setDemandes(demandesDuMois); // Mettre la liste filtrée des demandes
        }
        else {
            rapport.setTitre("Rapport du mois de "+mois);
            rapport.setLibelle("Le rapport de ce mois est vide!");
        }

        // Sauvegarder et retourner le rapport
        return rapportRepository.save(rapport);
    }

    public Rapport createRapportAnnuel(User user, List<Demande> demandes, int an) {
        Rapport rapport = new Rapport();
        // Mettre à jour les attributs du rapport
        rapport.setDateCreation(new Date());
        rapport.setUser(user);

        if (demandes!=null)
        {
            // Initialisation de la liste pour stocker les demandes du mois spécifié
            List<Demande> demandesAnnee = new ArrayList<>();

            // Parcourir toutes les demandes
            for (Demande demande : demandes) {
                // Récupérer la date de création de la demande
                Date dateCreation = demande.getDateCreation();

                // Utiliser un objet Calendar pour extraire le mois de la date de création
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateCreation);
                int annee = cal.get(Calendar.YEAR); // Ajouter 1 car les mois commencent à 0

                // Comparer le mois de création avec l'index du mois spécifié
                System.out.println("annee: "+annee+"   an: "+an);
                System.out.println(annee==an);
                if (annee == an) {
                    // Si les mois correspondent, ajouter la demande à la liste des demandes du mois
                    List<Demande> demande1 = new ArrayList<>();
                    rapport.setDemandes(demande1);
                    rapport.getDemandes().add(demande);
                    demandesAnnee.add(demande);
                    System.out.println("demandes an: "+demandesAnnee.isEmpty());
                }
            }
                System.out.println("demandes an: "+demandesAnnee.isEmpty());
            rapport.setTitre("Rapport de l'année "+an);
            rapport.setLibelle("Rapport généré par "+user.getEmail());
            rapport.setDemandes(demandesAnnee); // Mettre la liste filtrée des demandes
        }
        else {
            rapport.setTitre("Rapport de l'année "+an);
            rapport.setLibelle("Le rapport de cette année est vide!");
        }

        // Sauvegarder et retourner le rapport
        return rapportRepository.save(rapport);
    }

    public void deleteRapport(Long id){
        rapportRepository.deleteById(id);
    }
}
