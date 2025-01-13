package it.epicode.esame_w6.viaggio;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;

    public List<Viaggio> findAll() {
        return viaggioRepository.findAll();
    }

    public Viaggio findById(Long id) {
        if (!viaggioRepository.existsById(id)) {
            throw new EntityNotFoundException("Viaggio non trovato!");
        }

        return viaggioRepository.findById(id).get();
    }

    public Viaggio createViaggio(@Valid Viaggio viaggio) {
        return viaggioRepository.save(viaggio);
    }

    public Viaggio updateViaggio(Long id,@Valid Viaggio modifiedViaggio) {
        Viaggio viaggio = findById(id);

        BeanUtils.copyProperties(modifiedViaggio,viaggio);

        return viaggioRepository.save(viaggio);
    }

    public Viaggio changeStato(Long id,@Valid Viaggio modifiedViaggio){
        Viaggio viaggio= findById(id);
        viaggio.setStatoViaggio(modifiedViaggio.getStatoViaggio());
        return viaggioRepository.save(viaggio);
    }

    public void deleteViaggio(Long id) {
        viaggioRepository.deleteById(id);
    }



}
