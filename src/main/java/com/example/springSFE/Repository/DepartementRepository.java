package com.example.springSFE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.springSFE.Entity.Departement;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long>{

}
