package sccul.com.sccul.controllers.surveyControllers.survey.dtos;

import sccul.com.sccul.models.surveyModels.survey.Survey;

public class SurveyDto {

    

    public Survey castToSurvey() {
        return new Survey();
    }
}
