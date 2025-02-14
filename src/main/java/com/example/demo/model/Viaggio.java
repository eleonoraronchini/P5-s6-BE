package com.example.demo.model;

import com.example.demo.model.enumerations.statoViaggio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Entity(name = "viaggi")
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String destinazione;
    @Column(nullable = false)
    private LocalDate dataViaggio;
    @Enumerated(EnumType.STRING)
    private statoViaggio status;
}
