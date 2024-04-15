package com.dame.uasz.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Table(name = "Users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, updatable=false)
	private Long id;

	@Column(nullable=false, unique=true)
	private String username;
	 
	@Column(nullable=false)
	private String password;

	@Column(nullable=false)
	private String role;

	@Column(nullable=false, unique=true)
	private String email;

	@OneToMany(mappedBy = "user")
	private List<Notification> notifications;

	@OneToMany(mappedBy = "user")
	private List<Demande> demandes;

}