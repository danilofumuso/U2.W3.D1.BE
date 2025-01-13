package it.epicode.esercizio.W7.D1.prenotazione;

import it.epicode.esercizio.W7.D1.dipendente.Dipendente;
import it.epicode.esercizio.W7.D1.viaggio.Viaggio;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "data_prenotazione")
    private LocalDate dataPrenotazione;

    @Column(name = "preferenze_dipendente")
    private String preferenzeDipendente;

    @ManyToOne
    @JoinColumn(name = "viaggio_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Viaggio viaggio;

    //TODO
    //per cancellare dipendente nonostante la prenotazione dovrei fare la relazione lato dipendente e riscrivere tutta la logica
    //se uso bidirezionalità posso fare la OnetoMany lato dipendente e viaggio e usare @JsonIgnoreProperties({"author"}),
    //però mi darebbe la prenotazione con dipendente con le prenotazioni

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Dipendente dipendente;

}
