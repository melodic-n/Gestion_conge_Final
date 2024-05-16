package com.example.springSFE.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springSFE.Entity.Conge;
import com.example.springSFE.Entity.Demande;
import com.example.springSFE.Repository.CongeRepository;
import com.example.springSFE.Repository.DemandeRepository;

@Service
public class CongeService {

	private final CongeRepository repcong;
	private final DemandeRepository repdem;
	private final EmailService email;

	@Autowired
	public CongeService(CongeRepository repcong, DemandeRepository repdem2,EmailService email) {
		this.repcong = repcong;
		this.repdem = repdem2;
		this.email= email;
	}
	
	
	// employe = solde
	
	  public Map<String,Object> getconge(){
	  
	  List<Conge> list = repcong.findAll(); Map<String,Object> mp = new
	  HashMap<>(); ArrayList<Object> info = new ArrayList<>();

		try {
			for (Conge c : list) {
				Conge rh = c;
				info.add(rh.getMotif());
				info.add(rh.getDate_debut());
				info.add(rh.getDate_fin());
				mp.put(rh.getEmp().getNomPrenom(), info);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return mp;
	}
	 
	
	  public void gererdemandes(String choix, Long de) {
		  Optional<Demande> demande = repdem.findById(de);


	        
	        if ("accepted".equals(choix)) {
	        	
	            Conge conge;

				if (demande.get().getMotif() == null || demande.get().getDate_debut() == null
						|| demande.get().getDate_fin() == null || demande.get().getEmp() == null)
					throw new IllegalArgumentException("no parameter");

				conge = new Conge(demande.get().getMotif(), demande.get().getDate_debut(), demande.get().getDate_fin(),
						demande.get().getEmp());

				repcong.save(conge); // save DB

				Long newsolde = (demande.get().getEmp().getSoldeconge() - conge.calculday()); // update solde

				demande.get().getEmp().setSoldeconge(newsolde);

				email.sendEmail(demande.get().getEmp().getEmail(), "Status de votre demande de conge",
						"Votre demande de conge a été accepter ");

				repdem.delete(demande.get());
	  }
	        else {
	        	repdem.delete(demande.get());
	        	email.sendEmail(demande.get().getEmp().getEmail(),
			        			"Status de votre demande de conge",
			        			"Votre demande de conge a été refuser ");
	        }
	  }

}
