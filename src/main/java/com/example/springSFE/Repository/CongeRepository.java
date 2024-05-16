package com.example.springSFE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.springSFE.Entity.Conge;

@Repository
public interface CongeRepository extends JpaRepository<Conge,Long> {

}
