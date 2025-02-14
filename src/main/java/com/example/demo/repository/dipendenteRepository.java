package com.example.demo.repository;

import com.example.demo.model.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface dipendenteRepository extends JpaRepository<Dipendente, Long> {
}
