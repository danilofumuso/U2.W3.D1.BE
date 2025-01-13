package it.epicode.esame_w6.prenotazione;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione,Long> {

    @Query("SELECT COUNT(p) FROM Prenotazione p WHERE p.dipendente.id = :dipendenteId AND p.dataPrenotazione = :dataPrenotazione")
    public int numeroPrenotazioniGiornata(Long dipendenteId, LocalDate dataPrenotazione);


    @Query("SELECT COUNT(p) FROM Prenotazione p WHERE p.dipendente.id = :dipendenteId AND p.viaggio.data = :data")
    int numeroPrenotazioneDataViaggio(Long dipendenteId, LocalDate data);

}
