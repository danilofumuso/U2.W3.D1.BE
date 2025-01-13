package it.epicode.esame_w6.dipendente;

import it.epicode.esame_w6.auth.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;

import java.util.Set;

@Data
@Entity
@Table(name = "dipendenti")
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Il campo username non può essere vuoto!")
    private String username;

    @NotBlank(message = "Il campo nome non può essere vuoto!")
    private String nome;

    @NotBlank(message = "Il campo cognome non può essere vuoto!")
    private String cognome;

    @Email(message = "Inserisci un indirizzo email valido!")
    private String email;

    @Column(name="url_foto_profilo")
    private String urlFotoProfilo;
    
}
