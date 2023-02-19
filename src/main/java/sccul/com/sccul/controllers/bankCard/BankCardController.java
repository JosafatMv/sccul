package sccul.com.sccul.controllers.bankCard;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sccul.com.sccul.controllers.bankCard.dtos.BankCardDto;
import sccul.com.sccul.models.bank_card.BankCard;
import sccul.com.sccul.services.bankCard.BankCardService;
import sccul.com.sccul.utils.CustomResponse;

@RestController
@RequestMapping("/api/bankCards")
@CrossOrigin(origins = "*")
public class BankCardController {
    
    @Autowired
    private BankCardService service;

    //Get all
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<BankCard>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(),HttpStatus.OK);
    }
    //Get One by id
    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<BankCard>> getOne(@PathVariable long id){
        return new ResponseEntity<>(this.service.getOne(id),HttpStatus.OK);
    }

    //Insert
    @PostMapping("/")
    public ResponseEntity<CustomResponse<BankCard>> insert(@Valid @RequestBody BankCardDto bankCard){
        System.out.println("aqui llego");
        return new ResponseEntity<>(this.service.insert(bankCard.castToBankCard()),HttpStatus.CREATED);
    }

    //Update
    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<BankCard>> update(@PathVariable @Valid long id, @RequestBody BankCardDto bankCard){
        return new ResponseEntity<>(this.service.update(id,bankCard.castToBankCard()),HttpStatus.OK);
    }

    @PatchMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Integer>> enableOrDisable(@PathVariable long id, @RequestBody BankCard bankCard){
        bankCard.setId(id);
        return new ResponseEntity<>(this.service.changeStatus(bankCard),HttpStatus.OK);
    }
}
