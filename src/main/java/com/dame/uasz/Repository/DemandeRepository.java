package com.dame.uasz.Repository;

import com.dame.uasz.Model.Demande;
import com.dame.uasz.Model.Notification;
import com.dame.uasz.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande, Long> {
    @Query("SELECT n FROM Notification n WHERE n.demande = ?1 ")
    List<Notification> findByDemande(Demande demande);
}
