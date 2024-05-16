package com.example.springSFE.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springSFE.Entity.Demande;
import com.example.springSFE.Exception.SoldeExcpetion;
import com.example.springSFE.Service.DemandeService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DemandeController {
	 
		private final DemandeService emp;
		
		@Autowired
		public DemandeController(DemandeService emp) { 
			this.emp = emp;
			}
		
	//wprls
	@GetMapping("/demandes")
	public List<Demande> getdemande(){
			return emp.voirdemande();
	}
	
	@GetMapping("/demandes/{id}")
	public List<Demande> getdemandebyid(@PathVariable Long id){
			return emp.getDemandWithUserDetailsById(id);
	}
	
	//works
	@PostMapping("/effectuerdemande")
	public void setdemande (@RequestBody Demande demande ) throws SoldeExcpetion {
		    emp.createdemande(demande.getMotif(),demande.getDate_debut(),
		    					demande.getDate_fin(), demande.getEmp());	
	}

}
