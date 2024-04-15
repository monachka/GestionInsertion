package com.dame.uasz.service;

import com.dame.uasz.Model.Action;
import com.dame.uasz.Model.Demande;
import com.dame.uasz.Model.User;
import com.dame.uasz.Repository.DemandeRepository;
import com.dame.uasz.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class DemandeService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DemandeRepository demandeRepository;


    public List<Demande> getAllDemandes(){
        return demandeRepository.findAll();
    }

    public List<Demande> getDemandesUser(User user){
        return userRepository.findByUser2(user);
    }

    public List<Demande> getDemandesUserAccepted(User user){
        if (userRepository.findByUser2(user).isEmpty())
        {
            return null;
        }
        else {
            List<Demande> demandes = new ArrayList<>();
            for (Demande demande: userRepository.findByUser2(user)){
                if (demande.isAccepted()){
                    demandes.add(demande);
                }
            }

            return demandes;
        }
    }

    public List<Demande> getDemandesUserRejected(User user){
        if (userRepository.findByUser2(user).isEmpty())
        {
            return null;
        }
        else {
            List<Demande> demandes = new ArrayList<>();
            for (Demande demande: userRepository.findByUser2(user)){
                if (!demande.isAccepted()){
                    demandes.add(demande);
                }
            }

            return demandes;
        }
    }

    public List<Demande> getDemandesUserValidated(User user){
        if (userRepository.findByUser2(user).isEmpty())
        {
            return null;
        }
        else {
            List<Demande> demandes = new ArrayList<>();
            for (Demande demande: userRepository.findByUser2(user)){
                if (demande.isValidated()){
                    demandes.add(demande);
                }
            }

            return demandes;
        }
    }

    public List<Demande> getDemandesUserInvalidated(User user){
        if (userRepository.findByUser2(user).isEmpty())
        {
            return null;
        }
        else {
            List<Demande> demandes = new ArrayList<>();
            for (Demande demande: userRepository.findByUser2(user)){
                if (!demande.isValidated()){
                    demandes.add(demande);
                }
            }

            return demandes;
        }
    }

    public List<Demande> getDemandesAccepted(){
        if (demandeRepository.findAll().isEmpty())
        {
            return null;
        }
        else {
            List<Demande> demandes = new ArrayList<>();
            for (Demande demande: demandeRepository.findAll()){
                if (demande.isAccepted()){
                    demandes.add(demande);
                }
            }

            return demandes;
        }
    }

    public List<Demande> getDemandesRejected(){
        if (demandeRepository.findAll().isEmpty())
        {
            return null;
        }
        else {
            List<Demande> demandes = new ArrayList<>();
            for (Demande demande: demandeRepository.findAll()){
                if (!demande.isAccepted()){
                    demandes.add(demande);
                }
            }

            return demandes;
        }
    }

    public List<Demande> getDemandesValidated(){
        if (demandeRepository.findAll().isEmpty())
        {
            return null;
        }
        else {
            List<Demande> demandes = new ArrayList<>();
            for (Demande demande: demandeRepository.findAll()){
                if (demande.isValidated()){
                    demandes.add(demande);
                }
            }

            return demandes;
        }
    }

    public List<Demande> getDemandesInvalidated(){
        if (demandeRepository.findAll().isEmpty())
        {
            return null;
        }
        else {
            List<Demande> demandes = new ArrayList<>();
            for (Demande demande: demandeRepository.findAll()){
                if (!demande.isValidated()){
                    demandes.add(demande);
                }
            }

            return demandes;
        }
    }

    public Demande createDemande(Demande demande, User user){
        demande.setUser(user);
        demande.setDateCreation(new Date());

        return demandeRepository.save(demande);
    }

    public Demande updateDemande(Long id, Demande demande){
        if (demandeRepository.findById(id).isPresent()){
            Demande demande1 = demandeRepository.findById(id).get();

            demande1.setTitre(demande.getTitre());
            demande1.setContenu(demande.getContenu());
            demande1.setDateCreation(new Date());

            return demandeRepository.save(demande1);
        }

        return null;
    }

    public void deleteDemande(Long id){
        demandeRepository.deleteById(id);
    }

    public Demande setDemandeAccepted(Demande demande, User user){
        Action action = new Action("Acceptation Demande", user.getEmail(), new Date());
        demande.setAccepted(true);
        demande.setAction(action);

        return demandeRepository.save(demande);
    }

    public Demande setDemandeRejected(Demande demande, User user){
        Action action = new Action("Rejet Demande", user.getEmail(), new Date());
        demande.setAccepted(false);
        demande.setAction(action);

        return demandeRepository.save(demande);
    }

    public Demande setDemandeValidated(Demande demande, User user){
        Action action = new Action("Validation Insertion", user.getEmail(), new Date());
        demande.setValidated(true);
        demande.setAction(action);

        return demandeRepository.save(demande);
    }

    public Demande setDemandeInvalidated(Demande demande, User user){
        Action action = new Action("Invalidation Insertion", user.getEmail(), new Date());
        demande.setValidated(false);
        demande.setAction(action);

        return demandeRepository.save(demande);
    }

    public Demande setDemandeModification(Demande demande, User user){
        Action action = new Action("Demande de modification", user.getEmail(), new Date());
        demande.setAction(action);
        demande.setValidated(false);

        return demandeRepository.save(demande);
    }

}
