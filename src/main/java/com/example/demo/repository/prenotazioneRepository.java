package com.example.demo.repository;

import com.example.demo.model.Dipendente;
import com.example.demo.model.Prenotazione;
import com.example.demo.model.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface prenotazioneRepository extends JpaRepository<Prenotazione,Long> {
    boolean existsByDipendenteAndViaggio_DataViaggio(Dipendente dipendente, LocalDate dataViaggio);
}
