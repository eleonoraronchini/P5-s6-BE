package com.example.demo.controller;

import com.example.demo.DTO.ViaggioDTO;
import com.example.demo.mapper.ViaggioMapper;
import com.example.demo.model.Viaggio;
import com.example.demo.model.enumerations.statoViaggio;
import com.example.demo.service.ViaggioService;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaggio")
public class ViaggioController {
    @Autowired
    ViaggioService service;
    @Autowired
    ViaggioMapper mapper;


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createViaggio(@RequestBody ViaggioDTO viaggioDTO) {
        ViaggioDTO v = service.createViaggio(viaggioDTO);
        return "Viaggio aggiunto con successo!";
    }

    @GetMapping("/findAll")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ViaggioDTO> getAllViaggi() {
        return service.getAllViaggi();
    }

    @GetMapping("/findById/{id}")
    public ViaggioDTO getViaggio(@PathVariable Long id) {
        return service.getViaggioById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public String deleteById(@PathVariable Long id) {
        Viaggio viaggio = new Viaggio();
        viaggio.setId(id);
        if(viaggio != null){
            service.deleteViaggio(viaggio);
            return "Viaggio rimosso";
        }
        return "Viaggio non trovato";

    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<ViaggioDTO> updateById(@PathVariable Long id, @RequestBody ViaggioDTO viaggioDTO) {
        Viaggio viaggio = ViaggioMapper.toEntity(viaggioDTO);
        viaggio.setId(id);
        if (viaggio == null ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            ViaggioDTO v =  service.updateViaggio(viaggio);
            return new ResponseEntity<>(v, HttpStatus.OK);
        }
    }
    @PatchMapping("/updateStatusById/{id}")
    public String updateStatus (@PathVariable Long id, @RequestBody ViaggioDTO dto) {
        ViaggioDTO viaggioDTO = service.getViaggioById(id);
        Viaggio viaggio = mapper.toEntity(viaggioDTO);
        viaggio.setStatus(dto.getStatus());
        if (viaggio != null) {
            service.changeStatus(viaggio);
            return "status del viaggio con id: " + id + "aggiornato!";
        }
        throw new RuntimeException( "nessun viaggio trovato con id: " + id);
    }
}
