package com.example.demo.model;

import com.example.demo.model.enumerations.statoViaggio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "prenotazioni")
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "id_viaggio")
    private Viaggio viaggio;
    private LocalDate dataRichiesta;
    private String note;
    @ManyToOne
    @JoinColumn(name = "id_dipendente")
    private Dipendente dipendente;

}
