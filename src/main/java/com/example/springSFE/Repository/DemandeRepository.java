package com.example.springSFE.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.springSFE.Entity.Demande;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {

}
