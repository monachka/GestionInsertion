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
public class Demande {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable=false, updatable=false)
    private Long id;

    private String titre;
    private String contenu;
    private boolean accepted;
    private boolean validated;
    private Date dateCreation;

    @Transient // Indique à JPA de ne pas mapper ce champ à une colonne dans la base de données
    private Action action;

    @ManyToOne
    private User user;

    @ManyToOne
    private Rapport rapport;

    @OneToMany(mappedBy = "demande")
    private List<Notification> notifications;
}
