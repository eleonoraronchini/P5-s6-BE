package com.example.demo.mapper;

import com.example.demo.DTO.ViaggioDTO;
import com.example.demo.model.Viaggio;
import org.springframework.stereotype.Component;

@Component
public class ViaggioMapper {

    public static ViaggioDTO toDTO (Viaggio viaggio){
        ViaggioDTO viaggioDTO = new ViaggioDTO();
        viaggioDTO.setDestinazione(viaggio.getDestinazione());
        viaggioDTO.setDataViaggio(viaggio.getDataViaggio());
        viaggioDTO.setStatus(viaggio.getStatus());
        return viaggioDTO;
    }

    public static Viaggio toEntity (ViaggioDTO viaggioDTO){
        Viaggio viaggio = new Viaggio();
        viaggio.setDestinazione(viaggioDTO.getDestinazione());
        viaggio.setDataViaggio(viaggioDTO.getDataViaggio());
        viaggio.setStatus(viaggioDTO.getStatus());
        return viaggio;
    }
}
