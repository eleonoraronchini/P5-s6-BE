package com.example.demo.repository;

import com.example.demo.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface prenotazioneRepository extends JpaRepository<Prenotazione,Long> {
}
