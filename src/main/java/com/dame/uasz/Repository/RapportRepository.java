package com.dame.uasz.Repository;

import com.dame.uasz.Model.Demande;
import com.dame.uasz.Model.Rapport;
import com.dame.uasz.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RapportRepository extends JpaRepository<Rapport, Long> {
    @Query("SELECT d FROM Demande d WHERE d.rapport = ?1 ")
    List<Demande> findByRapport(Rapport rapport);
}
