package com.example.springSFE.Entity;

import java.sql.Date;
import java.util.concurrent.TimeUnit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="conge")
public class Conge  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	protected String motif ;
	protected Date date_debut;
	protected Date date_fin;
	protected Long nbrjours;
	@OneToOne
	@ManyToOne(targetEntity = Employe.class)
    @JsonBackReference 
    protected Employe emp;
    
	public Conge() {
		
	}


	public Conge(String motif, Date date_debut, 
				 Date date_fin ,Employe employe) {
		this.motif = motif;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
	    this.nbrjours = calculday();
		this.emp = employe;
	}

	public Long calculday() {
		long diffmil = date_fin.getTime() - date_debut.getTime();
		return TimeUnit.DAYS.convert(diffmil, TimeUnit.MILLISECONDS);
	}


	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	public Long getNombre_jours() {
		return nbrjours;
	}

	public void setNombre_jours(Long long1) {
		this.nbrjours = (long) long1;
	}

	public Employe getEmp() {
		return emp;
	}

	public void setEmp(Employe emp) {
		this.emp = emp;
	}
	 
}
