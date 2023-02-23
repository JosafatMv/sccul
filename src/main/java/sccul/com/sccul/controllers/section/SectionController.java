package sccul.com.sccul.controllers.section;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sccul.com.sccul.controllers.section.dtos.SectionDto;
import sccul.com.sccul.models.course.Course;
import sccul.com.sccul.models.section.Section;
import sccul.com.sccul.services.section.SectionService;
import sccul.com.sccul.utils.CustomResponse;

import java.util.List;

@RestController
@RequestMapping("/api/sections")
@CrossOrigin(origins = {"*"})
public class SectionController {
    @Autowired
    private SectionService service;

    //consulta por id de curso
    @GetMapping("/course/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<List<Section>>> getByCourse(@PathVariable long id) {
        return new ResponseEntity<>(this.service.getByCourse(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Section>>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Section>> insert(@Valid @RequestBody SectionDto section){
        return new ResponseEntity<>(this.service.insert(section.castToSection()), HttpStatus.CREATED);
    }

    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Section>> update(@PathVariable @Valid long id, @RequestBody SectionDto section){
        return new ResponseEntity<>(
                this.service.update(id, section.castToSection()), HttpStatus.OK
        );
    }


}
