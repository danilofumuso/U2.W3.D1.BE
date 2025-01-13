package it.epicode.esame_w6.dipendente;

import it.epicode.esame_w6.cloudinary.CloudinaryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Validated
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public List<Dipendente> findAll() {
        return dipendenteRepository.findAll();
    }

    public Dipendente findById(Long id) {
        if (!dipendenteRepository.existsById(id)) {
            throw new EntityNotFoundException("Dipendente non trovato!");
        }

        return dipendenteRepository.findById(id).get();
    }

    public Dipendente createDipendente(@Valid Dipendente dipendente, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            dipendente.setUrlFotoProfilo(cloudinaryService.uploader(file,"dipendenti").get("url").toString());
        }
        return dipendenteRepository.save(dipendente);
    }

    public Dipendente updateDipendente(@Valid Dipendente modifiedDipendente, MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            modifiedDipendente.setUrlFotoProfilo(cloudinaryService.uploader(file,"dipendenti").get("url").toString());
        }
        return dipendenteRepository.save(modifiedDipendente);
    }

    public void deleteDipendente(Long id) {
        dipendenteRepository.deleteById(id);
    }

}