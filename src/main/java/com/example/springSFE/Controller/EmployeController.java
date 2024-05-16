package com.example.springSFE.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springSFE.Entity.Departement;
import com.example.springSFE.Entity.Employe;
import com.example.springSFE.Exception.DepartmentExistException;
import com.example.springSFE.Exception.EmployeExistException;
import com.example.springSFE.Exception.NoCompteException;
import com.example.springSFE.Service.EmployeService;

@RestController
@RequestMapping("/employe")
public class EmployeController {

	private final EmployeService empser;
		@Autowired
	public EmployeController(EmployeService empser) {
		this.empser = empser;
	}
	 
	//works
		@PostMapping("/add")
		public void createemp(@RequestBody Employe emp, @RequestParam String role) throws EmployeExistException {
		    // Retrieve the department ID from the employe object
		    Long departmentId = emp.getDepartement().getId_dept();
		    
		    // Check if the department ID is null
		    if (departmentId == null) {
		        throw new IllegalArgumentException("Department ID cannot be null");
		    }

		    // Call the createEmployee method with the department ID
		    empser.createEmployee(emp.getNom(), emp.getPrenom(), emp.getEmail(),
		                          emp.getMotdepass(), departmentId, emp.getSoldeconge(), role);
		}
	
		@PutMapping("/modify")
		public void modmanager(@RequestBody Employe emp, @RequestParam(required = false) String role) throws DepartmentExistException {
		

		    empser.modifyEmployee(emp.getId(), emp.getNom(), emp.getPrenom(), emp.getEmail(),
		            emp.getMotdepass(), emp.getDepartement(), emp.getSoldeconge(), role);
		}

	//works
	@DeleteMapping("/delete/{id}")
	public void deletemanager(@PathVariable Long id) throws NoCompteException {
		empser.deletecompte(id);
	}
	
	
	//works
	@GetMapping("/all")
	public List<Object> getAllEmployees() {
	    List<Employe> employees = empser.findallemploye();
	    List<Object> customResponse = new ArrayList<>();

	    for (Employe employee : employees) {
	        Map<String, Object> employeeData = new HashMap<>();
	        employeeData.put("id", employee.getId());
	        employeeData.put("nom", employee.getNom());
	        employeeData.put("prenom", employee.getPrenom());
	        employeeData.put("departement", employee.getDepartement().getNom_dept());
	        employeeData.put("email", employee.getEmail());
	        employeeData.put("demandes", employee.getDemandes());
	        employeeData.put("conges", employee.getConges());
	        employeeData.put("soldeconge", employee.getSoldeconge());

	        customResponse.add(employeeData);
	    }

	    return customResponse;
	}
	
	//works
	@GetMapping("/allsolde")
	public Map<String, Long> getallsolde() {
		return empser.voirsoldeall();
	}
	
	
	//works
	@GetMapping("/all/{id}")
	public Optional<Employe> getallempid(@PathVariable Long id) {
		return empser.findallemployeid(id);
	}
	
}
