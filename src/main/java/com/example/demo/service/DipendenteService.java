package com.example.demo.service;

import com.example.demo.DTO.DipendenteDTO;
import com.example.demo.mapper.DipendenteMapper;
import com.example.demo.model.Dipendente;
import com.example.demo.repository.dipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.controller.DipendenteController.dipendenti;
import static com.example.demo.mapper.DipendenteMapper.toDTO;
import static com.example.demo.mapper.DipendenteMapper.toEntity;

@Service

public class DipendenteService {
    @Autowired
    dipendenteRepository dipendenteRepository;
    @Autowired
    DipendenteMapper dipendenteMapper;

    public DipendenteDTO createDipendente(DipendenteDTO dipendenteDTO) {
        Dipendente d = toEntity(dipendenteDTO);
        dipendenteRepository.save(d);
        return dipendenteDTO;
    }

    public DipendenteDTO getDipendenteById(Long id) {
        Optional<Dipendente> dip = dipendenteRepository.findById(id);
        if (dip.isPresent()) {
            return toDTO(dip.get());
        } else {
            throw new RuntimeException("il dipendente con chiave:" + id + "non è presente");
        }
    }

    public List<DipendenteDTO> getAllDipendenti() {
        List<Dipendente> lista = dipendenteRepository.findAll();
        List<DipendenteDTO> listaDTO = new ArrayList<>();
        for (Dipendente dipendente : lista) {
            listaDTO.add(toDTO(dipendente));
        }
        return listaDTO;
    }


    public String deleteDipendente(Dipendente dipendente) {
        Optional<Dipendente> dipendenteRicercato = dipendenteRepository.findById(dipendente.getId());
        if (dipendenteRicercato.isPresent()) {
            Dipendente dipendenteTrovato = dipendenteRicercato.get();
            dipendenteRepository.delete(dipendenteTrovato);
            DipendenteDTO dipendenteDTO = toDTO(dipendenteTrovato);
            return "dipendente" +dipendenteDTO.getUsername()+  "rimosso con successo!";
        }
        return "dipendente non trovato!";
    }
}
    /*

    public DipendenteDTO updateDipendente(Dipendente dipendente) {
        Optional<Dipendente> dipendenteRicercato = dipendenteRepository.findById(dipendente.getId());
        if (dipendenteRicercato.isPresent()) {
            Dipendente dipendenteTrovato = dipendenteRicercato.get();
            dipendenteTrovato.setUsername(dipendente.getUsername());
            dipendenteTrovato.setNome(dipendente.getNome());
            dipendenteTrovato.setCognome(dipendente.getCognome());
            dipendenteTrovato.setEmail(dipendente.getEmail());
            dipendenteRepository.save(dipendenteTrovato);
            DipendenteDTO dipendenteUpdateDTO = toDTO(dipendenteTrovato);
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
            dipendenti.remove(dip.get());
            return "Dipendente rimosso dalla lista";
        } else {
            return "Nessun dipendente trovato";
        }
    }
}

*/