package com.example.demo.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DipendenteDTO {
    @NotBlank(message = "'username' non può essere dei caratteri vuoti")
    @NotNull(message = "'username' non può essere vuoto")
    private String username;
    @NotBlank(message = "'nome' non può essere dei caratteri vuoti")
    @NotNull(message = "'nome' non può essere vuoto")
    private String nome;
    @NotBlank(message = "'cognome' non può essere dei caratteri vuoti")
    @NotNull(message = "'cognome' non può essere vuoto")
    private String cognome;
    @NotBlank(message = "'email' non può essere dei caratteri vuoti")
    @NotNull(message = "'email' non può essere vuoto")
    private String email;
}
