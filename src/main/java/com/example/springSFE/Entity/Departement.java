package com.example.springSFE.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "departement")
public class Departement {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "departement_seq")
	@SequenceGenerator(name = "departement_seq", 
					   sequenceName = "departement_seq",
					   allocationSize = 1)
	private Long id;

	@Column(name = "nom", nullable = false)
	private String nom;

	@OneToMany(mappedBy = "departement")
	private List<Employe> employes;

	public Departement() {

	}

	public Departement(String nom_dept) {
		this.nom = nom_dept;
	}

	public Long getId_dept() {
		return id;
	}

	public void setId_dept(Long id_dept) {
		this.id = id_dept;
	}

	public String getNom_dept() {
		return nom;
	}

	public void setNom_dept(String nom_dept) {
		this.nom = nom_dept;
	}

}