package com.example.demo.repository;

import com.example.demo.model.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface viaggioRepository extends JpaRepository<Viaggio,Long> {
    public Viaggio findBydestinazioneAndDataViaggio (String destinazione, LocalDate dataViaggio);
}
