package sccul.com.sccul.controllers.bankCard;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<CustomResponse<List<BankCard>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(),HttpStatus.OK);
    }
    //Get One by id
    @GetMapping("/{id:[0-9]+}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<CustomResponse<BankCard>> getOne(@PathVariable long id){
        return new ResponseEntity<>(this.service.getOne(id),HttpStatus.OK);
    }

    //Get all by user id
    @GetMapping("/user/{id:[0-9]+}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('user')")
    public ResponseEntity<CustomResponse<List<BankCard>>> getAllByUserId(@PathVariable long id){
        return new ResponseEntity<>(this.service.getAllByUserId(id),HttpStatus.OK);
    }

    //Get one by user id
    @GetMapping("/user/{userId:[0-9]+}/card/{cardId:[0-9]+}")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('user')")
    public ResponseEntity<CustomResponse<BankCard>> getOneByUserId(@PathVariable long userId,@PathVariable long cardId){
        return new ResponseEntity<>(this.service.getOneByUserId(userId,cardId),HttpStatus.OK);
    }

    //Insert
    @PostMapping("/")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('user')")
    public ResponseEntity<CustomResponse<BankCard>> insert(@Valid @RequestBody BankCardDto bankCard){
        return new ResponseEntity<>(this.service.insert(bankCard.castToBankCard()),HttpStatus.CREATED);
    }

    //Update
    @PutMapping("/{id:[0-9]+}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<CustomResponse<BankCard>> update(@PathVariable  long id,@Valid @RequestBody BankCardDto bankCard){
        return new ResponseEntity<>(this.service.update(id,bankCard.castToBankCard()),HttpStatus.OK);
    }

    @PatchMapping("/{id:[0-9]+}")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseEntity<CustomResponse<Integer>> enableOrDisable(@PathVariable long id,@Valid @RequestBody BankCard bankCard){
        bankCard.setId(id);
        return new ResponseEntity<>(this.service.changeStatus(bankCard),HttpStatus.OK);
    }
}
