package com.example.demo.service;

import com.example.demo.DTO.ViaggioDTO;
import com.example.demo.model.Viaggio;
import com.example.demo.repository.viaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.mapper.ViaggioMapper.toDTO;
import static com.example.demo.mapper.ViaggioMapper.toEntity;

@Service
public class ViaggioService {
    @Autowired
    viaggioRepository viaggioRepository;

    public ViaggioDTO createViaggio (ViaggioDTO viaggioDTO){
        Viaggio v = toEntity(viaggioDTO);
        viaggioRepository.save(v);
        return viaggioDTO;
    }

    public ViaggioDTO getViaggioById (Long id){
        Optional<Viaggio> v = viaggioRepository.findById(id);
        if(v.isPresent()){
            return toDTO(v.get());
        } else {
            throw new RuntimeException("il viaggio con chiave: " + id + " non è presente");
        }
    }
    public ViaggioDTO getViaggioByDestinazione(String destinazione){
        Optional<Viaggio> v = Optional.ofNullable(viaggioRepository.findBydestinazione(destinazione));
        if (v.isPresent()){
            return toDTO(v.get());
        } else {
            throw new RuntimeException("il viaggio con destinazione: " + destinazione + " non è presente");
        }
    }

    public List <ViaggioDTO> getAllViaggi (){
        List<Viaggio> lista = viaggioRepository.findAll();
        List<ViaggioDTO> listaDTO = new ArrayList<>();
        for (Viaggio viaggio : lista){
            listaDTO.add(toDTO(viaggio));
        }
        return listaDTO;
    }

    public String deleteViaggio (Viaggio viaggio){
        Optional<Viaggio> viaggioRicercato = viaggioRepository.findById(viaggio.getId());
        if(viaggioRicercato.isPresent()){
            Viaggio viaggioTrovato = viaggioRicercato.get();
            viaggioRepository.delete(viaggioTrovato);
            ViaggioDTO viaggioDTO = toDTO(viaggioTrovato);
            return "viaggio" + viaggioDTO.getDestinazione() + "rimosso con successo!";
        }
        return "viaggio non trovato";
    }

    public ViaggioDTO updateViaggio (Viaggio viaggio){
        Optional<Viaggio> viaggioRicercato = viaggioRepository.findById(viaggio.getId());
        if(viaggioRicercato.isPresent()){
            Viaggio viaggioTrovato = viaggioRicercato.get();
            viaggioTrovato.setDestinazione(viaggio.getDestinazione());
            viaggioTrovato.setDataViaggio(viaggio.getDataViaggio());
            viaggioTrovato.setStatus(viaggio.getStatus());
            viaggioRepository.save(viaggioTrovato);
            ViaggioDTO vDTO = toDTO(viaggioTrovato);
            return vDTO;

        } else {
            throw new RuntimeException("nessun viaggio trovato con questo id");
        }
    }
}

