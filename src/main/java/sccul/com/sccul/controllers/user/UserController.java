package sccul.com.sccul.controllers.user;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sccul.com.sccul.controllers.user.dtos.UserDto;
import sccul.com.sccul.models.user.User;
import sccul.com.sccul.services.user.UserService;
import sccul.com.sccul.utils.CustomResponse;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"*"})
public class UserController {
@Autowired
    private UserService service;

    //Get all
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<User>>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    //Get one by id
    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<User>> getOne(@PathVariable long id) {
        return new ResponseEntity<>(this.service.getOne(id), HttpStatus.OK);
    }

    //Insert
    @PostMapping("/")
    public ResponseEntity<CustomResponse<User>> insert(@Valid @RequestBody UserDto user) {
        return new ResponseEntity<>(this.service.insert(user.castToUser()), HttpStatus.CREATED);
    }

    //Update
    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<User>> update(@Valid @PathVariable long id, @RequestBody UserDto user) {
        user.setId(id);
        return new ResponseEntity<>(this.service.update(user.castToUser()), HttpStatus.OK);
    }

    @PatchMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Integer>> enableOrDisable(@PathVariable Long id, @RequestBody UserDto user) {
        user.setId(id);
        return new ResponseEntity<>(this.service.changeStatus(user.castToUser()), HttpStatus.OK);
    }

}
