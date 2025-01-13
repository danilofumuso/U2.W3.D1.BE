package it.epicode.esame_w6.prenotazione;

import it.epicode.esame_w6.dipendente.Dipendente;
import it.epicode.esame_w6.dipendente.DipendenteService;
import it.epicode.esame_w6.exceptions.PrenotazioneNonEffettuabileException;
import it.epicode.esame_w6.viaggio.Viaggio;
import it.epicode.esame_w6.viaggio.ViaggioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private ViaggioService viaggioService;

    public List<Prenotazione> findAll() {
        return prenotazioneRepository.findAll();
    }

    public Prenotazione findById(Long id) {
        if (!prenotazioneRepository.existsById(id)) {
            throw new EntityNotFoundException("Prenotazione non trovata!");
        }

        return prenotazioneRepository.findById(id).get();
    }

    public Prenotazione createPrenotazione(@Valid PrenotazioneDTO prenotazioneDTO) {
        if (prenotazioneRepository.numeroPrenotazioniGiornata(prenotazioneDTO.getDipendenteId(), prenotazioneDTO.getDataPrenotazione()) > 0) {
            throw new PrenotazioneNonEffettuabileException("Il dipendente ha già effettuato una prenotazione in data odierna!");
        }

        Prenotazione prenotazione = new Prenotazione();
        BeanUtils.copyProperties(prenotazioneDTO, prenotazione);

        Viaggio viaggio = viaggioService.findById(prenotazioneDTO.getViaggioId());
        Dipendente dipendente = dipendenteService.findById(prenotazioneDTO.getDipendenteId());

        prenotazione.setViaggio(viaggio);
        prenotazione.setDipendente(dipendente);

        if (prenotazioneRepository.numeroPrenotazioneDataViaggio(prenotazioneDTO.getDipendenteId(), viaggio.getData()) > 0) {
            throw new PrenotazioneNonEffettuabileException("il dipendente ha già un viaggio per questa data");
        }

        return prenotazioneRepository.save(prenotazione);
    }

    public Prenotazione updatePrenotazione(Long id, @Valid PrenotazioneDTO modifiedPrenotazione) {
        if (prenotazioneRepository.numeroPrenotazioniGiornata(modifiedPrenotazione.getDipendenteId(), modifiedPrenotazione.getDataPrenotazione()) > 0) {
            throw new PrenotazioneNonEffettuabileException("Il dipendente ha già effettuato una prenotazione in data odierna!");
        }

        Prenotazione prenotazione = findById(id);
        BeanUtils.copyProperties(modifiedPrenotazione, prenotazione);

        Viaggio viaggio = viaggioService.findById(modifiedPrenotazione.getViaggioId());
        Dipendente dipendente = dipendenteService.findById(modifiedPrenotazione.getDipendenteId());

        prenotazione.setViaggio(viaggio);
        prenotazione.setDipendente(dipendente);

        return prenotazioneRepository.save(prenotazione);
    }

    public void deletePrenotazione(Long id) {
        prenotazioneRepository.deleteById(id);
    }

}
