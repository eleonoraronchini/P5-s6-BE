package com.example.demo.repository;

import com.example.demo.model.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface viaggioRepository extends JpaRepository<Viaggio,Long> {
    public Viaggio findBydestinazione (String destinazione);
}
