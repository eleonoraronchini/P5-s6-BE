package com.example.demo.DTO;

import com.example.demo.model.Dipendente;
import com.example.demo.model.Viaggio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDate;
@Data
public class PrenotazioneDTO {
    @NotBlank(message = "'viaggio' non può essere dei caratteri vuoti")
    @NotNull(message = "'viaggio' non può essere vuota")
    private Viaggio viaggio;
    @NotNull(message = "'dataRichiesta' non può essere vuota")
    private LocalDate dataRichiesta;
    private String note;
    @NotBlank(message = "'dipendente' non può essere dei caratteri vuoti")
    @NotNull(message = "'dipendente' non può essere vuota")
    private Dipendente dipendente;
}
