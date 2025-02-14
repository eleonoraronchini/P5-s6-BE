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
        return "Dipendente aggiunto con successo!";
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
}
    /*
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
*/
