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
    

    //get all
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Survey>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(),HttpStatus.OK);
    }

    //Insert
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Survey>> insert(@RequestBody SurveyDto survey){
        return new ResponseEntity<>(this.service.insert(survey.castToSurvey()), HttpStatus.OK);
    }


}
