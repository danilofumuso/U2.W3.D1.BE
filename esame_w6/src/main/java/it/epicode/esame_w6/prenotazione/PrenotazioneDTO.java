package it.epicode.esame_w6.prenotazione;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioneDTO {

    @NotNull(message = "La data di richiesta deve essere inserita!")
    private LocalDate dataPrenotazione;

    private String preferenzeDipendente;

    @Min(value = 1, message = "l'Id non può essere minore di 1")
    private Long viaggioId;

    @Min(value = 1, message = "l'Id non può essere minore di 1")
    private Long dipendenteId;


}
