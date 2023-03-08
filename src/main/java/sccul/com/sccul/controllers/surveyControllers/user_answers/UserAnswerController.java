package sccul.com.sccul.controllers.surveyControllers.user_answers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sccul.com.sccul.models.surveyModels.user_answer.UserAnswer;
import sccul.com.sccul.services.surveyServices.user_answer.UserAnswerService;
import sccul.com.sccul.utils.CustomResponse;

import java.util.List;

@RestController
@RequestMapping("/api/user_answers")
@CrossOrigin(origins = {"*"})
public class UserAnswerController {
    @Autowired
    private UserAnswerService service;

    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<UserAnswer>> getByid(@PathVariable long id){
        return ResponseEntity.ok(this.service.getOne(id));
    }
    @PostMapping("/")
    public ResponseEntity<CustomResponse<UserAnswer>> insert(@RequestBody UserAnswer userAnswer){
        return ResponseEntity.ok(this.service.insert(userAnswer));
    }
    @GetMapping("/count/question/{id:[0-9]+}/answer/{id2:[0-9]+}")
    public ResponseEntity<CustomResponse<Integer>> countByQuestionIdAndAnswer(@PathVariable long id, @PathVariable int id2){
        return ResponseEntity.ok(this.service.countByQuestionIdAndAnswer(id, id2));
    }

    @GetMapping("/user/{id:[0-9]+}/survey/{id2:[0-9]+}")
    public ResponseEntity<CustomResponse<List< UserAnswer>>> getByUserAndSurvey(@PathVariable long id, @PathVariable long id2){
        return ResponseEntity.ok(this.service.findByUserIdAndSurveyId(id, id2));
    }

}
