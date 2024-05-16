package com.example.springSFE.Entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("RH")
public class Rh extends Employe {

	public Rh() {
	}

	public Rh(String nom, String prenom, String email, String motdepass, Departement departement, Long soldeconge) {
		super(nom, prenom, email, motdepass, departement, soldeconge);
	}

}
