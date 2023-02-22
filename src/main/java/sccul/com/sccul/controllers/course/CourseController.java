package sccul.com.sccul.controllers.course;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sccul.com.sccul.controllers.course.dtos.ChangeDiscountDto;
import sccul.com.sccul.controllers.course.dtos.CourseDto;
import sccul.com.sccul.models.course.Course;
import sccul.com.sccul.services.courses.CourseService;
import sccul.com.sccul.utils.CustomResponse;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = {"*"})
public class CourseController {
    @Autowired
    private CourseService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Course>>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Course>> getOne(@PathVariable long id) {
        return new ResponseEntity<>(this.service.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Course>> insert(@Valid @RequestBody CourseDto course) {
        return new ResponseEntity<>(this.service.insert(course.castToCourse()), HttpStatus.CREATED);
    }

    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Course>> update(@PathVariable long id, @Valid @RequestBody CourseDto course) {
        course.setId(id);
        return new ResponseEntity<>(this.service.update(course.castToCourse()), HttpStatus.OK);
    }

    @PatchMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Integer>> changeDiscount(@PathVariable Long id, @Valid @RequestBody ChangeDiscountDto course) {
        course.setId(id);
        return new ResponseEntity<>(this.service.changeDiscount(course.castToCourse()), HttpStatus.OK);
    }

}
