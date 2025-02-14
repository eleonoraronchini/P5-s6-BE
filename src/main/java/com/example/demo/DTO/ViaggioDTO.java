package com.example.demo.DTO;

import com.example.demo.model.enumerations.statoViaggio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ViaggioDTO {
    @NotBlank(message = "'destinazione' non può essere dei caratteri vuoti")
    @NotNull(message = "'destinazione' non può essere vuota")
    private String destinazione;
    @NotNull(message = "'datViaggio' non può essere vuota")
    private LocalDate dataViaggio;
    private statoViaggio status;
}
