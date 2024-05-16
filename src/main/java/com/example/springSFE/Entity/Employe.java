package com.example.springSFE.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name= "employe")
public class Employe {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		protected Long id;
		protected String nom;
		protected String prenom;
		protected String email;
		protected String motdepass;
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "departement")
		protected Departement departement;
		protected Long soldeconge;
	    @OneToMany (mappedBy = "emp")
		protected List<Demande> demandes;
	    @OneToMany(mappedBy="emp")
		protected List<Conge> conges;
		protected LocalDate created_at;
		@OneToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "user_id")
		private User user;
		
		public Employe() {
			
		}

		public Employe(String nom, String prenom, String email, String motdepass, 
				Departement departement,
				Long soldeconge) {
			this.nom = nom;
			this.prenom = prenom;
			this.email = email;
			this.motdepass = motdepass;
			this.departement = departement;
			this.soldeconge = soldeconge;
			this.created_at = LocalDate.now(); 
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMotdepass() {
			return motdepass;
		}

		public void setMotdepass(String motdepass) {
			this.motdepass = motdepass;
		}

		public Departement getDepartement() {
			return departement;
		}

		public void setDepartement(Departement departement) {
			this.departement = departement;
		}

		public Long getSoldeconge() {
			return soldeconge;
		}

		
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public void setSoldeconge(Long soldeconge) {
			this.soldeconge = soldeconge;
		}

		public List<Demande> getDemandes() {
			return demandes;
		}

		public void setDemandes(List<Demande> demandes) {
			this.demandes = demandes;
		}

		public List<Conge> getConges() {
			return conges;
		}

		public void setConges(List<Conge> conges) {
			this.conges = conges;
		}

		public LocalDate getCreated_at() {
			return created_at;
		}

		public void setCreated_at(LocalDate created_at) {
			this.created_at = created_at;
		}

		public String getNomPrenom() {
			return this.nom + " " + this.prenom;
		}
		
		
		
		
}
