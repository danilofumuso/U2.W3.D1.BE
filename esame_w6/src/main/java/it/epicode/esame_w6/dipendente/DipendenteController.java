package it.epicode.esame_w6.dipendente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping
    public ResponseEntity<List<Dipendente>> getAllDipendenti() {

        List<Dipendente> dipendenti = dipendenteService.findAll();

        return ResponseEntity.ok(dipendenti);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dipendente> findDipendenteById(@PathVariable Long id) {
        return ResponseEntity.ok(dipendenteService.findById(id));
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Dipendente> createDipendente(@RequestParam("username") String username,
                                                       @RequestParam("nome") String nome,
                                                       @RequestParam("cognome") String cognome,
                                                       @RequestParam("email") String email,
                                                       @RequestParam(value = "fotoProfilo", required = false) MultipartFile file) {

        Dipendente dipendente = new Dipendente();
        dipendente.setUsername(username);
        dipendente.setNome(nome);
        dipendente.setCognome(cognome);
        dipendente.setEmail(email);
        return new ResponseEntity<>(dipendenteService.createDipendente(dipendente, file), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<Dipendente> updateDipendente(@PathVariable Long id,
                                                       @RequestParam("username") String username,
                                                       @RequestParam("nome") String nome,
                                                       @RequestParam("cognome") String cognome,
                                                       @RequestParam("email") String email,
                                                       @RequestParam(value = "fotoProfilo", required = false) MultipartFile file) {

        Dipendente dipendente = dipendenteService.findById(id);
        dipendente.setUsername(username);
        dipendente.setNome(nome);
        dipendente.setCognome(cognome);
        dipendente.setEmail(email);

        return ResponseEntity.ok(dipendenteService.updateDipendente(dipendente, file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDipendente(@PathVariable Long id) {
        dipendenteService.deleteDipendente(id);
        return new ResponseEntity<>("Dipendente eliminato correttamente!", HttpStatus.NO_CONTENT);
    }

}
