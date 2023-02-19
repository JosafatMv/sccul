package sccul.com.sccul.controllers.category;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sccul.com.sccul.controllers.category.dtos.CategoryDto;
import sccul.com.sccul.models.category.Category;
import sccul.com.sccul.services.category.CategoryService;
import sccul.com.sccul.utils.CustomResponse;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = {"*"})
public class CategoryController {

    @Autowired
    private CategoryService service;

    //Get all
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Category>>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    //Get one by id
    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Category>> getOne(@PathVariable @Positive long id){
        return new ResponseEntity<>(this.service.getOne(id), HttpStatus.OK);
    }

    //Insert
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Category>> insert(@Valid @RequestBody CategoryDto category) {
        return new ResponseEntity<>(this.service.insert(category.castToCategory()), HttpStatus.CREATED);
    }

    //Update
    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Category>> update(@PathVariable @Valid long id ,@RequestBody CategoryDto category) {
        return new ResponseEntity<>(this.service.update(id, category.castToCategory()), HttpStatus.OK);
    }

    @PatchMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Integer>> enableOrDisable(@PathVariable Long id ,@RequestBody CategoryDto category){
        category.setId(id);
        return new ResponseEntity<>(this.service.changeStatus(category.castToCategory()), HttpStatus.OK);
    }

}
