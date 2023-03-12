package sccul.com.sccul.controllers.surveyControllers.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import sccul.com.sccul.controllers.surveyControllers.survey.dtos.SurveyDto;
import sccul.com.sccul.models.surveyModels.survey.Survey;
import sccul.com.sccul.services.surveyServices.survey.SurveyService;
import sccul.com.sccul.utils.CustomResponse;

import java.util.List;

@RestController
@RequestMapping("/api/surveys")
@CrossOrigin(origins = {"*"})
public class SurveyController {
    @Autowired
    private SurveyService service;
    
    //Mejor usa el quet questions by survey id del controller questions
    @GetMapping("/course/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Survey>> getByCourseId(@PathVariable long id){
        return new ResponseEntity<>(this.service.getByCourseId(id),HttpStatus.OK);
    }

    //Insert
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Survey>> insert(@RequestBody SurveyDto survey){
        return new ResponseEntity<>(this.service.insert(survey.castToSurvey()), HttpStatus.OK);
    }


}
