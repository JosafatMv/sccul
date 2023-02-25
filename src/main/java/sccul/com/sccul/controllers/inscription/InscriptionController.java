package sccul.com.sccul.controllers.inscription;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sccul.com.sccul.controllers.inscription.dtos.ChangePercentageDto;
import sccul.com.sccul.controllers.inscription.dtos.InscriptionDto;
import sccul.com.sccul.models.inscription.Inscription;
import sccul.com.sccul.services.inscription.InscriptionService;
import sccul.com.sccul.utils.CustomResponse;

import java.util.List;

@RestController
@RequestMapping("/api/inscriptions")
@Validated
@CrossOrigin(origins = "*")
public class InscriptionController {

    @Autowired
    private InscriptionService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Inscription>>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Inscription>> getOne(@PathVariable long id) {
        return new ResponseEntity<>(this.service.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Inscription>> insert(@Valid @RequestBody InscriptionDto inscription) {
        return new ResponseEntity<>(this.service.insert(inscription.castToInscription()), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Inscription>> delete(@PathVariable long id) {
        return new ResponseEntity<>(this.service.removeInscription(id), HttpStatus.OK);
    }

    @PatchMapping("/changeStatus/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Inscription>> changeStatus(@PathVariable long id) {
        return new ResponseEntity<>(this.service.changeStatus(id), HttpStatus.OK);
    }

    @PatchMapping("/changePercentage/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Inscription>> changePercentage(@PathVariable long id, @Valid @RequestBody ChangePercentageDto percentage) {
        Inscription inscriptionToUpdate = new Inscription();
        inscriptionToUpdate.setId(id);
        return new ResponseEntity<>(this.service.changePercentage(inscriptionToUpdate, percentage.getPercentage()), HttpStatus.OK);
    }

}
