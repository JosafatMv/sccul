package sccul.com.sccul.controllers.survey.dtos;

import sccul.com.sccul.models.survey.Survey;

public class SurveyDto {
    public Survey castToSurvey() {
        return new Survey();
    }
}
