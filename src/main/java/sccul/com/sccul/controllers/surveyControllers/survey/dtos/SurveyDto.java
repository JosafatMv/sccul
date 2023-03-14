package sccul.com.sccul.controllers.surveyControllers.survey.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sccul.com.sccul.models.course.Course;
import sccul.com.sccul.models.surveyModels.survey.Survey;

@AllArgsConstructor
@Getter
@Setter
public class SurveyDto {

    private Long id;

    @NotNull(message = "El id del curso no puede ser nulo")
    private String name;

    public Survey castToSurvey() {
        return new Survey(getId(), getName(), null,null);
    }
}
