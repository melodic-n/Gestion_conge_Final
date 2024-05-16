package com.example.springSFE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.springSFE.Entity.Employe;

@Repository
public interface EmployeRepository  extends JpaRepository<Employe, Long>{
	Boolean existsByEmail(String email);
}
