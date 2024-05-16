package com.example.springSFE.Controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springSFE.Service.CongeService;

@RestController
public class CongeController {
	
	private final CongeService conge;
	
	public CongeController(CongeService conge) {
		this.conge = conge;
	}
	
	
	@GetMapping("/conges")
	public Map<String, Object> showconge(){
		return conge.getconge();
	}
	
	@PostMapping("/valide/{choix}/{id}")
	public void psotconge(@PathVariable String choix,@PathVariable Long id) {
		conge.gererdemandes( choix, id);
	}

}
