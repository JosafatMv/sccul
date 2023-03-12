package sccul.com.sccul.controllers.surveyControllers.user_answers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sccul.com.sccul.controllers.surveyControllers.user_answers.dtos.UserAnswerDto;
import sccul.com.sccul.models.surveyModels.survey.Survey;
import sccul.com.sccul.models.surveyModels.user_answer.UserAnswer;
import sccul.com.sccul.models.user.User;
import sccul.com.sccul.services.surveyServices.user_answer.UserAnswerService;
import sccul.com.sccul.utils.CustomResponse;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user_answers")
@CrossOrigin(origins = {"*"})
public class UserAnswerController {
    @Autowired
    private UserAnswerService service;

    //getmapping by survey id and user id
    @GetMapping("/survey/{survey_id:[0-9]+}/user/{user_id:[0-9]+}")
    public ResponseEntity<CustomResponse<List<UserAnswer>>> getByUserAndSurvey(@PathVariable long survey_id, @PathVariable long user_id){
        return new ResponseEntity(this.service.findByUserIdAndSurveyId(survey_id, user_id), HttpStatus.OK);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<CustomResponse<List<UserAnswer>>> saveAll(@RequestBody List<UserAnswerDto> userAnswersdto){
        List<UserAnswer> userAnswers= new ArrayList<>();
        for (int i = 0; i <userAnswersdto.size() ; i++) {
            userAnswers.add(userAnswersdto.get(i).castToUserAnswer());
        }
        return new ResponseEntity(this.service.saveAll(userAnswers), HttpStatus.CREATED);
    }
}
