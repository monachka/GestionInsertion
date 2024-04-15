package com.dame.uasz.service;


import com.dame.uasz.Model.Demande;
import com.dame.uasz.Model.Notification;
import com.dame.uasz.Model.User;
import com.dame.uasz.Repository.DemandeRepository;
import com.dame.uasz.Repository.NotificationRepository;
import com.dame.uasz.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DemandeRepository demandeRepository;

    public List<Notification> getAllNotificationsUser(String username){
        User user = userRepository.findByUsername(username).get();
        return userRepository.findByUser1(user);
    }

    public List<Notification> getAllNotificationsDemande(Long id){
        Demande demande = demandeRepository.findById(id).get();
        return demandeRepository.findByDemande(demande);
    }

    public Notification getNotification(Long id){
        return notificationRepository.findById(id).get();
    }

    public Notification createNotification(Notification notification, User user, Demande demande){
        notification.setDateCreation(new Date());
        notification.setUser(user);
        notification.setDemande(demande);
        return notificationRepository.save(notification);
    }

    public Notification updateNotification(Long id, Notification notification){
        if (notificationRepository.findById(id).isPresent()){
            Notification notif = notificationRepository.findById(id).get();

            notif.setTitre(notification.getTitre());
            notif.setLibelle(notification.getLibelle());
            notif.setDateCreation(new Date());

            return notificationRepository.save(notif);
        }
        else {
            return null;
        }
    }

    public void deleteNotification(Long id){
        notificationRepository.deleteById(id);
    }
}
