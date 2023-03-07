package sccul.com.sccul.controllers.surveyControllers.answers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sccul.com.sccul.models.surveyModels.answer.Answer;
import sccul.com.sccul.services.surveyServices.answer.AnswerService;
import sccul.com.sccul.utils.CustomResponse;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
@CrossOrigin(origins = {"*"})
public class AnswerController {
    @Autowired
    private AnswerService service;


    @GetMapping("/getbyquestion/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<List<Answer>>> getByQuestion (@PathVariable long id){
        return new ResponseEntity<>(this.service.getByQuestionId(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Answer>> insert(@RequestBody Answer answer){
        return new ResponseEntity<>(this.service.insert(answer), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CustomResponse<Answer>> update(@RequestBody Answer answer){
        return new ResponseEntity<>(this.service.update(answer), HttpStatus.OK);
    }
}
