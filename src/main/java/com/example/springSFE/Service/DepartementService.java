package com.example.springSFE.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.springSFE.Entity.Departement;
import com.example.springSFE.Exception.DepartmentExistException;
import com.example.springSFE.Repository.DepartementRepository;

@Service
public class DepartementService {

	private final DepartementRepository rep;

	public DepartementService(DepartementRepository rep) {
		this.rep = rep;
	}
	
	public List<Departement> showdepartement() {
		return rep.findAll();
	}
	
	
	public void adddepartement(String name) throws DepartmentExistException {
	List<Departement> depts = rep.findAll();
	if (name == null || name.isEmpty()) {
		throw new IllegalArgumentException("No name provided for Department");
	}
	Boolean exists = false;
	for (Departement departement : depts) {
		if(departement.getNom_dept().equals(name))
			exists= true;
	}
		    if (!exists) {
				Departement empp = new Departement(name);
				rep.save(empp);
			}
		    else
		    	throw new DepartmentExistException("this departement already exists");
	}
	
	
	public void deletedept(Long id) {
		if(rep.findById(id).isEmpty())
			throw new NullPointerException("Aucun department existe");
		rep.deleteById(id);
	}
	
	
	public void modifydept(Departement dept) {
			
		Optional<Departement> depts = rep.findById(dept.getId_dept());
		if(depts.isEmpty())
			throw new NullPointerException("Aucun department existe");
		
			depts.get().setNom_dept(dept.getNom_dept());
			rep.save(depts.get());
	}
}
