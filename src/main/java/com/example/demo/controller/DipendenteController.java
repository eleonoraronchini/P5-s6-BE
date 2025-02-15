package com.example.demo.controller;

import com.example.demo.DTO.DipendenteDTO;
import com.example.demo.DTO.PrenotazioneDTO;
import com.example.demo.model.Dipendente;
import com.example.demo.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.mapper.DipendenteMapper.toEntity;

@RestController
@RequestMapping("/dipendente")
public class DipendenteController {
    @Autowired
    DipendenteService service;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createDipendente(@RequestBody DipendenteDTO dipendenteDTO) {
        DipendenteDTO d = service.createDipendente(dipendenteDTO);
        return "Dipendente aggiunto con successo!";
    }
    @PostMapping("/createPrenotazione")
    @ResponseStatus(HttpStatus.CREATED)
    public String createPrenotazione(@RequestBody PrenotazioneDTO prenotazioneDTO){
        PrenotazioneDTO p = service.createPrenotazione(prenotazioneDTO);
        return "Prenotazione aggiunta con succeso!";
    }

    @GetMapping("/findAll")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<DipendenteDTO> getAllDipendenti() {
        return service.getAllDipendenti();
    }

    @GetMapping("/findById/{id}")
    public DipendenteDTO getDipendente(@PathVariable Long id) {
        return service.getDipendenteById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public String deleteById(@PathVariable Long id) {
        Dipendente dipendente = new Dipendente();
        dipendente.setId(id);
        if(dipendente != null){
            service.deleteDipendente(dipendente);
            return "Dipendente rimosso";
        }
         return "Dipendente non trovato";

    }

    @PatchMapping("/updateById/{id}")
    public ResponseEntity<DipendenteDTO> updateById(@PathVariable Long id, @RequestBody DipendenteDTO dipendenteDTO) {
      Dipendente dipendente = toEntity(dipendenteDTO);
      dipendente.setId(id);
       if (dipendente == null ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
         DipendenteDTO dip =  service.updateDipendente(dipendente);
            return new ResponseEntity<>(dip, HttpStatus.OK);
        }
    }

}