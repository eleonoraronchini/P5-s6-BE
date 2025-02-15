package com.example.demo.service;

import com.example.demo.DTO.DipendenteDTO;
import com.example.demo.DTO.PrenotazioneDTO;
import com.example.demo.DTO.ViaggioDTO;
import com.example.demo.mapper.DipendenteMapper;
import com.example.demo.mapper.PrenotazioneMapper;
import com.example.demo.mapper.ViaggioMapper;
import com.example.demo.model.Dipendente;
import com.example.demo.model.Prenotazione;
import com.example.demo.model.Viaggio;
import com.example.demo.repository.dipendenteRepository;
import com.example.demo.repository.prenotazioneRepository;
import com.example.demo.repository.viaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.mapper.DipendenteMapper.toDTO;
import static com.example.demo.mapper.DipendenteMapper.toEntity;

@Service

public class DipendenteService {
    @Autowired
    dipendenteRepository dipendenteRepository;
    @Autowired
    prenotazioneRepository prenotazioneRepository;
    @Autowired
    viaggioRepository viaggioRepository;

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
            throw new RuntimeException("il dipendente con chiave: " + id + " non è presente");
        }
    }
    public DipendenteDTO getDipendenteByUsername (String username){
        Optional<Dipendente> dip = Optional.ofNullable(dipendenteRepository.findByusername(username));
        if (dip.isPresent()){
            return toDTO(dip.get());
        } else {
            throw new RuntimeException("il dipendente con username: " + username + " non è presente");
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

    public PrenotazioneDTO createPrenotazione (PrenotazioneDTO pDTO){
        Prenotazione p = PrenotazioneMapper.toEntity(pDTO);
       Optional <Dipendente> dip = Optional.ofNullable(dipendenteRepository.findByusername(p.getDipendente().getUsername()));
        if (dip.isPresent()){
            Dipendente D = dip.get();
            p.setDipendente(D);
    } else {
            DipendenteDTO d = new DipendenteDTO();
            d.setUsername(p.getDipendente().getUsername());
            d.setNome(p.getDipendente().getNome());
            d.setCognome(p.getDipendente().getCognome());
            d.setEmail(p.getDipendente().getEmail());
            dipendenteRepository.save(toEntity(d));
            p.setDipendente(toEntity(d));

        }
        Optional<Viaggio> v = Optional.ofNullable(viaggioRepository.findBydestinazione(p.getViaggio().getDestinazione()));
        if (v.isPresent()){
            Viaggio viaggio = v.get();
            p.setViaggio(viaggio);
        } else {
            ViaggioDTO viaggioDTO = new ViaggioDTO();
            viaggioDTO.setDestinazione(p.getViaggio().getDestinazione());
            viaggioDTO.setDataViaggio(p.getViaggio().getDataViaggio());
            viaggioDTO.setStatus(p.getViaggio().getStatus());
            viaggioRepository.save(ViaggioMapper.toEntity(viaggioDTO));
            p.setViaggio(ViaggioMapper.toEntity(viaggioDTO));
        }
        boolean prenotazioneEsistente = prenotazioneRepository.existsByDipendenteAndViaggio_DataViaggio(p.getDipendente(),p.getViaggio().getDataViaggio());
        if (prenotazioneEsistente){
            throw new RuntimeException("Il dipendente ha già una prenotazione per questo giorno.");
        }
        prenotazioneRepository.save(p);

        return PrenotazioneMapper.toDTO(p);
    }
}
