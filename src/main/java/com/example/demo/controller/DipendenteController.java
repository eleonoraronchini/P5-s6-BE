package com.example.demo.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.DTO.DipendenteDTO;
import com.example.demo.DTO.PrenotazioneDTO;
import com.example.demo.model.Dipendente;
import com.example.demo.service.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
import java.util.Map;


import static com.example.demo.mapper.DipendenteMapper.toEntity;

@RestController
@RequestMapping("/dipendente")
public class DipendenteController {
    @Autowired
    DipendenteService service;
    @Autowired
    Cloudinary cloudinary;

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

    @PostMapping("/createConFoto")
    public DipendenteDTO createDipendenteConImmagine(@RequestPart("immagine") MultipartFile immagine, @RequestPart @Validated DipendenteDTO dipendenteDTO, BindingResult validazione){

        if(validazione.hasErrors()){
            String messaggioErr = "errore validazione \n";
            for (ObjectError errore : validazione.getAllErrors()){
                messaggioErr += errore.getDefaultMessage() + " \n";
            }
        }
        try {
            Map mappa = cloudinary.uploader().upload(immagine.getBytes(), ObjectUtils.emptyMap());
            // Recupero l'invio
            String urlImage = (String) mappa.get("secure_url");
            dipendenteDTO.setImmagine(urlImage);
            service.createDipendente(dipendenteDTO);
            return dipendenteDTO;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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