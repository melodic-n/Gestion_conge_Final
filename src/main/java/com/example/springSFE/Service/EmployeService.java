package com.example.springSFE.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springSFE.Entity.Departement;
import com.example.springSFE.Entity.ERole;
import com.example.springSFE.Entity.Employe;
import com.example.springSFE.Entity.Manager;
import com.example.springSFE.Entity.Rh;
import com.example.springSFE.Entity.Role;
import com.example.springSFE.Entity.User;
import com.example.springSFE.Exception.DepartmentExistException;
import com.example.springSFE.Exception.EmployeExistException;
import com.example.springSFE.Exception.NoCompteException;
import com.example.springSFE.Repository.DepartementRepository;
import com.example.springSFE.Repository.EmployeRepository;
import com.example.springSFE.Repository.RoleRepository;
import com.example.springSFE.Repository.UserRepository;


@Service
public class EmployeService {

	
	@Autowired
	private final EmployeRepository emp;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DepartementRepository dept;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
    
	public EmployeService(EmployeRepository emp, BCryptPasswordEncoder bCryptPasswordEncoder,
			 DepartementRepository dept2,UserRepository userRepository, RoleRepository roleRepository) {
			this.emp = emp;
			this.bCryptPasswordEncoder = bCryptPasswordEncoder;
			this.dept = dept2;
			this.userRepository=userRepository;
			this.roleRepository = roleRepository;
	}
	
	   // get all employee
		public List<Employe> findallemploye(){
			return emp.findAll();
		}
		
		
	   //get  one specific employe
		public Optional<Employe> findallemployeid(Long id){ // i could turn into oBject type Employe 
			return emp.findById(id);
		}
		

		 public String encryptPassword(String pass) {
			 return bCryptPasswordEncoder.encode(pass); 
			 }
		 
		 public void createEmployee(String nom, String prenom, String email, String motdepass, Long deptId, Long solde,
			        String role) throws EmployeExistException {

			    if (nom == null || prenom == null || email == null || motdepass == null ||
			            solde == null || role == null) {
			        throw new IllegalArgumentException("All parameters must not be null");
			    }

			    // Getting department from id
			    Optional<Departement> departementOpt = dept.findById(deptId);
			    Departement departement = departementOpt.orElse(null);

			    if (departement == null) {
			        throw new IllegalArgumentException("Department with ID " + deptId + " not found");
			    }

			    // Checking if employee already exists
			    boolean exists = emp.existsByEmail(email);
			    if (exists) {
			        throw new EmployeExistException("Employee with email " + email + " already exists");
			    }

			    // Create the Employee entity
			    Employe employe;
			    switch (role) {
			        case "Employe":
			            employe = new Employe(nom, prenom, email, encryptPassword(motdepass), departement, solde);
			            break;
			        case "Manager":
			            employe = new Manager(nom, prenom, email, encryptPassword(motdepass), departement, solde);
			            break;
			        case "RH":
			            employe = new Rh(nom, prenom, email, encryptPassword(motdepass), departement, solde);
			            break;
			        default:
			            throw new IllegalArgumentException("Invalid role: " + role);
			    }

			    // Save the Employee entity
			    emp.save(employe);
			    EmployeToUser(employe, role);
			}

		  
		  public void modifyEmployee(Long id, String nom, String prenom, String email, String motdepass, Departement departement,
                  Long solde, String role) throws NoSuchElementException, DepartmentExistException {
			    Optional<Employe> employeOpt = emp.findById(id);
			    Employe employe = employeOpt.orElseThrow(() -> new NoSuchElementException("Employee not found with ID: " + id));
			    User user = employe.getUser();
	
	    // Check if the provided attributes are not null and update them accordingly
	    if (nom != null) {
	        employe.setNom(nom);
	    }
	    if (prenom != null) {
	        employe.setPrenom(prenom);
	    }
	    if (email != null) {
	        employe.setEmail(email);
	        if (user != null) {
	          user.setEmail(email);
	        }
	    }
	    if (motdepass != null) {
	        employe.setMotdepass(encryptPassword(motdepass));
	        if (user != null) {
	          user.setPassword(encryptPassword(motdepass));
	        }
	    }
	    if (solde != null) {
	        employe.setSoldeconge(solde);
	    }
	
	    if (departement != null) {
	        Optional<Departement> departementOpt = dept.findById(departement.getId_dept());
	        Departement departement1 = departementOpt.orElseThrow(() -> new IllegalArgumentException("Department with ID " + departement.getId_dept() + " not found"));
	        if (departement1 != null) {
	          employe.setDepartement(departement1);
	        } else {
	          throw new DepartmentExistException("Department not found");
	        }
	    }
	    Employe  employenew;
	    if (role != null) {
	        switch (role) {
	          case "Employe":
	              if (!(employe instanceof Employe)) {
	                  employenew = new Employe(employe.getNom(), employe.getPrenom(), employe.getEmail(),
	                          employe.getMotdepass(), employe.getDepartement(), employe.getSoldeconge());
	                  emp.save(employenew);
	                  if (user != null) {
	                      userRepository.save(user);
	                  }
	              }
	              break;
	          case "Manager":
	              if (!(employe instanceof Manager)) {
	                  employenew = new Manager(employe.getNom(), employe.getPrenom(), employe.getEmail(),
	                          employe.getMotdepass(), employe.getDepartement(), employe.getSoldeconge());
	                  emp.save(employenew);
	                  if (user != null) {
	                      userRepository.save(user);
	                  }
	              }
	              break;
	          case "RH":
	              if (!(employe instanceof Rh)) {
	                  employenew = new Rh(employe.getNom(), employe.getPrenom(), employe.getEmail(),
	                          employe.getMotdepass(), employe.getDepartement(), employe.getSoldeconge());
	                  emp.save(employenew);
	                  if (user != null) {
	                      userRepository.save(user);
	                  }
	              }
	              break;
	          default:
	              throw new IllegalArgumentException("Invalid role: " + role);
	        }
	    }else {
	    	 emp.save(employe);
	    	    if (user != null) {
	    	        userRepository.save(user);
	    	    }
	    }

}
			//supprimmer
			public void deletecompte(Long id) throws NoCompteException  {
			    Optional<Employe> employeOpt = emp.findById(id);
			    Employe employe = employeOpt.orElseThrow(() -> new NoCompteException("Employee not found with ID: " + id));

			    // Retrieve the associated user
			    User user = employe.getUser();

			    // Delete the employee
			    emp.deleteById(id);

			    // Delete the associated user if it exists
			    if (user != null) {
			        userRepository.delete(user);
			    }
			}
			

			public Long voirsolde(Long id) {
				Optional<Employe> empl = emp.findById(id);
				if(empl.isEmpty() ) {
					Employe rh = empl.get();
					return rh.getSoldeconge();
					}
					throw new NoSuchElementException("Cet employée n'exist pas ");
			}
			
			
			// if a year passes from creating the profile all soldes should reset to 0
			 public void expiresolde (Employe empl) {
				 if(LocalDate.now().getDayOfMonth() == 1 && 
					LocalDate.now().getMonthValue() == 1) {
					 empl.setSoldeconge((long) 0);
				 }
			 }

				
			public Map<String, Long> voirsoldeall() {
				List<Employe> empl=emp.findAll();
				Map<String, Long> solde = new HashMap<String, Long>();
				for (Employe emp : empl) {
					Employe rh = emp;
					solde.put(rh.getNomPrenom(),rh.getSoldeconge());
				}
				return solde;
			}

	
			
			public void EmployeToUser(Employe emp,String role) {
				
		    User user = new User( 
		               emp.getEmail(),
		               emp.getMotdepass());
		
		    String strRoles = role;
		    Set<Role> roles = new HashSet<>();

		        switch (strRoles) {
		        case "Employe":
			          Role employeRole = roleRepository.findByName(ERole.ROLE_EMPLOYE)
			              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			          roles.add(employeRole);
			
			          break;
		        case "Manager":
		          Role managerRole = roleRepository.findByName(ERole.ROLE_MANAGER)
		              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		          roles.add(managerRole);
		
		          break;
		        case "RH":
		          Role rhRole = roleRepository.findByName(ERole.ROLE_RH)
		              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		          roles.add(rhRole);
		
		          break;
		        default:
		          Role userRole = roleRepository.findByName(ERole.ROLE_EMPLOYE)
		              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		          roles.add(userRole);
		        }
		
		    user.setRoles(roles);
		    userRepository.save(user);
}
			}
	
	

