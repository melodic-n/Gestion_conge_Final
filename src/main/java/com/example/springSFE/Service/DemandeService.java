package com.example.springSFE.Service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springSFE.Entity.Demande;
import com.example.springSFE.Entity.Employe;
import com.example.springSFE.Exception.SoldeExcpetion;
import com.example.springSFE.Repository.DemandeRepository;
import com.example.springSFE.Repository.EmployeRepository;

@Service
public class DemandeService {
	private final DemandeRepository emprep;
	private final EmployeRepository emp;
	
			@Autowired
		public DemandeService(DemandeRepository emprep,EmployeRepository emp) {
				this.emprep = emprep;
				this.emp=emp;
		}
	
		//voir status de demande 
		public List<Demande> voirdemande() {
				return emprep.findAll();
		}
		
		
		public List<Demande> getDemandWithUserDetailsById(Long id) {
		    Employe user = emp.findById(id).orElse(null);
		    if (user != null) {
		        List<Demande> demandes = user.getDemandes();
		       
		        return demandes;
		    }
		    return null;
		}

		
		//creer demande 		
		public void createdemande( String motif ,
									Date date_debut, Date date_fin,
									Employe emp) throws SoldeExcpetion {
			
		
				if(motif == null|| date_debut == null || date_fin == null || emp == null)
				{
				    throw new IllegalArgumentException("All parameters must not be null");
				}
				
				Demande demande = new Demande(motif ,date_debut, date_fin, emp);
				if (demande.calculday()>demande.getEmp().getSoldeconge()) {
					throw new  SoldeExcpetion("Vous avez surpasser votre solde");
				}
				else if (demande.getEmp().getSoldeconge() == 0 ) {
					throw new  SoldeExcpetion("Vous n'avez aucun jour de conge");

				}
				else
					emprep.save(demande);

		}

}
