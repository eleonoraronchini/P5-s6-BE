package com.example.demo.service;

import com.example.demo.DTO.DipendenteDTO;
import com.example.demo.mapper.DipendenteMapper;
import com.example.demo.model.Dipendente;
import com.example.demo.repository.dipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.demo.controller.DipendenteController.dipendenti;

@Service

public class DipendenteService {
    @Autowired
    dipendenteRepository dipendenteRepository;
    @Autowired
    DipendenteMapper dipendenteMapper;

    public DipendenteDTO createDipendente(DipendenteDTO dipendenteDTO) {
        Dipendente d = dipendenteMapper.toEntity(dipendenteDTO);
        dipendenteRepository.save(d);
        return dipendenteDTO;
    }

    public String deleteDipendente(Dipendente dipendente) {
        Optional<Dipendente> dipendenteRicercato = dipendenteRepository.findById(dipendente.getId());
        if (dipendenteRicercato.isPresent()) {
            Dipendente dipendenteTrovato = dipendenteRicercato.get();
            dipendenteRepository.delete(dipendenteTrovato);
        }
        return "dipendente rimosso con successo!";
    }

    public DipendenteDTO updateDipendente(Dipendente dipendente) {
        Optional<Dipendente> dipendenteRicercato = dipendenteRepository.findById(dipendente.getId());
        if (dipendenteRicercato.isPresent()) {
            Dipendente dipendenteTrovato = dipendenteRicercato.get();
            dipendenteTrovato.setUsername(dipendente.getUsername());
            dipendenteTrovato.setNome(dipendente.getNome());
            dipendenteTrovato.setCognome(dipendente.getCognome());
            dipendenteTrovato.setEmail(dipendente.getEmail());
            dipendenteRepository.save(dipendenteTrovato);
            DipendenteDTO dipendenteUpdateDTO = dipendenteMapper.toDTO(dipendenteTrovato);
            return dipendenteUpdateDTO;
        } else {
            throw new RuntimeException("nessun dipendente trovato con questo id");
        }
    }

    public Optional<Dipendente> ricercaDipendenteById(Long id) {
        Optional<Dipendente> dipendenteRecuperato = dipendenteRepository.findById(id);
        return dipendenteRecuperato;

    }

    public String deleteDipendentelist(Dipendente dipendente) {
        Optional<Dipendente> dip = dipendenti.stream().filter(d -> d.getId() == dipendente.getId()).findFirst();
        if (dip.isPresent()) {
            dipendenti.remove(dip);
            return "Dipendente rimosso dalla lista";
        } else {
            return "Nessun dipendente trovato";
        }
    }
}

