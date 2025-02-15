package com.example.demo.mapper;

import com.example.demo.DTO.PrenotazioneDTO;
import com.example.demo.model.Prenotazione;
import org.springframework.stereotype.Component;

@Component
public class PrenotazioneMapper {

    public static PrenotazioneDTO toDTO (Prenotazione prenotazione){
        PrenotazioneDTO prenotazioneDTO = new PrenotazioneDTO();
        prenotazioneDTO.setViaggio(prenotazione.getViaggio());
        prenotazioneDTO.setDipendente(prenotazione.getDipendente());
        prenotazioneDTO.setDataRichiesta(prenotazione.getDataRichiesta());
        prenotazioneDTO.setNote(prenotazione.getNote());
        return prenotazioneDTO;
    }

    public static Prenotazione toEntity (PrenotazioneDTO prenotazioneDTO){
        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setViaggio(prenotazioneDTO.getViaggio());
        prenotazione.setDipendente(prenotazioneDTO.getDipendente());
        prenotazione.setDataRichiesta(prenotazioneDTO.getDataRichiesta());
        prenotazione.setNote(prenotazioneDTO.getNote());
        return  prenotazione;
    }
}
