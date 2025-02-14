package com.example.demo.mapper;

import com.example.demo.DTO.DipendenteDTO;
import com.example.demo.model.Dipendente;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DipendenteMapper {

    public static DipendenteDTO toDTO(Dipendente dipendente) {
        DipendenteDTO dipendenteDTO = new DipendenteDTO();
        dipendenteDTO.setUsername(dipendente.getUsername());
        dipendenteDTO.setNome(dipendente.getNome());
        dipendenteDTO.setCognome(dipendente.getCognome());
        dipendenteDTO.setEmail(dipendente.getEmail());
        return dipendenteDTO;

    }

    public static Dipendente toEntity(DipendenteDTO dipendenteDTO) {
        Dipendente dipendente = new Dipendente();
        dipendente.setUsername(dipendenteDTO.getUsername());
        dipendente.setNome(dipendenteDTO.getNome());
        dipendente.setCognome(dipendenteDTO.getCognome());
        dipendente.setEmail(dipendenteDTO.getEmail());
        return dipendente;
    }

    public static DipendenteDTO toDTOpzional(Optional<Dipendente> dipendente) {
        if (dipendente.isPresent()) {
            Dipendente dipendenteValue = dipendente.get();
            DipendenteDTO dipendenteDTO = new DipendenteDTO();
            dipendenteDTO.setUsername(dipendenteValue.getUsername());
            dipendenteDTO.setNome(dipendenteValue.getNome());
            dipendenteDTO.setCognome(dipendenteValue.getCognome());;
            dipendenteDTO.setEmail(dipendenteValue.getEmail());
            return dipendenteDTO;
        } else {
            return null;
        }
    }
}

