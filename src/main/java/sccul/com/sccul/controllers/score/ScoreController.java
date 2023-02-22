package sccul.com.sccul.controllers.score;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import sccul.com.sccul.controllers.score.dtos.ScoreDto;
import sccul.com.sccul.models.course.Course;
import sccul.com.sccul.models.score.Score;
import sccul.com.sccul.models.user.User;
import sccul.com.sccul.services.score.ScoreService;
import sccul.com.sccul.utils.CustomResponse;

@RestController
@RequestMapping("/api/scores")
@CrossOrigin(origins = {"*"})
public class ScoreController {
    @Autowired
    private ScoreService service;

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<List<Score>>> getByCourse(@PathVariable Long id) {
        Course course = new Course();
        course.setId(id);
        return new ResponseEntity<>(this.service.getByCourse(course), HttpStatus.OK );
    }

    @GetMapping("/avg/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Double>> getAverageScore(@PathVariable Long id) {
        return new ResponseEntity<>(this.service.getAverageScore(id), HttpStatus.OK );
    }

    @GetMapping("/{userId:[0-9]+}/{courseId:[0-9]+}")
    public ResponseEntity<CustomResponse <Score>> getByUserAndCourse(@PathVariable Long userId, @PathVariable Long courseId) {
        Course course = new Course();
        course.setId(courseId);
        User user = new User();
        user.setId(userId);
        return new ResponseEntity<>(this.service.getByUserAndCourse(user,course), HttpStatus.OK );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Score>> insert(@Valid @RequestBody ScoreDto score) {
        return new ResponseEntity<>(this.service.insert(score.castToScore()), HttpStatus.CREATED);
    }
}
