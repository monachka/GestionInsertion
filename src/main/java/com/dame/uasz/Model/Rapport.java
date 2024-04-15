package com.dame.uasz.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rapport {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable=false, updatable=false)
    private Long id;

    private String titre;
    private String libelle;
    private Date dateCreation;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "rapport")
    private List<Demande> demandes;
}
