package sccul.com.sccul.controllers.surveyControllers.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sccul.com.sccul.models.surveyModels.questions.Question;
import sccul.com.sccul.services.surveyServices.question.QuestionService;
import sccul.com.sccul.utils.CustomResponse;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = {"*"})
public class QuestionController {
    @Autowired
    private QuestionService service;

    @GetMapping("/bysurvey/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<List<Question>>> getBySurveyId(@PathVariable long id){
        return ResponseEntity.ok(this.service.getBySurveyId(id));
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Question>> insert(@RequestBody Question question){
        return ResponseEntity.ok(this.service.insert(question));
    }

    @PutMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Question>> update(@PathVariable long id, @RequestBody Question question){
        question.setId(id);
        return ResponseEntity.ok(this.service.update(question));
    }

    @PostMapping("/saveAll")
    public ResponseEntity<CustomResponse<List<Question>>> saveall(@RequestBody List<Question> questions){
        return ResponseEntity.ok(this.service.saveAll(questions));
    }
}
