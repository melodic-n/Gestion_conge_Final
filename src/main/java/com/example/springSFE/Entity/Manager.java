package com.example.springSFE.Entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Manager")
public class Manager extends Employe {

	public Manager() {
	}

	public Manager(String nom, String prenom, String email, String motdepass, Departement departement,
			Long soldeconge) {
		super(nom, prenom, email, motdepass, departement, soldeconge);
	}

}
