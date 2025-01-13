package it.epicode.esame_w6.viaggio;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "viaggi")
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Il campo destinazioe non può essere vuoto!")
    private String destinazione;

    @FutureOrPresent(message = "La data del viaggio non può essere nel passato!")
    private LocalDate data;

    @Column(name = "stato_viaggio")
    @Enumerated(EnumType.STRING)
    private StatoViaggio statoViaggio;

}
