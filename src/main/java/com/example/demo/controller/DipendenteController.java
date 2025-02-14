package com.example.demo.controller;

import com.example.demo.DTO.DipendenteDTO;
import com.example.demo.mapper.DipendenteMapper;
import com.example.demo.model.Dipendente;
import com.example.demo.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dipendente")
public class DipendenteController {
    @Autowired
    DipendenteService service;

    public static List<Dipendente> dipendenti = new ArrayList<Dipendente>();

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createDipendente(@RequestBody DipendenteDTO dipendenteDTO) {
        DipendenteDTO d = service.createDipendente(dipendenteDTO);
        Dipendente dEntity = DipendenteMapper.toEntity(d);
        dipendenti.add(dEntity);
        return "Dipendente aggiunto con successo!";
    }

    @GetMapping("/findAll")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Dipendente> searchAllDipendenti() {
        return dipendenti;
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Dipendente> searchById(@PathVariable Long id) {
        Optional<Dipendente> dipendenteRicercato = service.ricercaDipendenteById(id);
        if (dipendenteRicercato.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(dipendenteRicercato.get(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        Optional<Dipendente> dipendenteRicercato = service.ricercaDipendenteById(id);
        if (dipendenteRicercato.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Dipendente dipendenteTrovato = dipendenteRicercato.get();
            service.deleteDipendente(dipendenteTrovato);
            service.deleteDipendentelist(dipendenteTrovato);
            return new ResponseEntity<>("dipendente eliminato con successo",HttpStatus.ACCEPTED);
        }
    }
    @PatchMapping("/updateById/{id}")
    public ResponseEntity<DipendenteDTO> updateById(@PathVariable Long id, @RequestBody DipendenteDTO dipendenteDTO) {
        Optional<Dipendente> dipendenteRicercato = dipendenti.stream().filter(dipendente -> dipendente.getId()==id).findFirst();
        if (dipendenteRicercato.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Dipendente d = dipendenteRicercato.get();
            service.updateDipendente(d);
            DipendenteDTO updateDipendenteDTO = DipendenteMapper.toDTO(d);
            d.setUsername(dipendenteDTO.getUsername());
            d.setNome(dipendenteDTO.getNome());
            d.setCognome(dipendenteDTO.getCognome());
            d.setEmail(dipendenteDTO.getEmail());

            return new ResponseEntity<>(updateDipendenteDTO, HttpStatus.OK);
        }
    }

}

