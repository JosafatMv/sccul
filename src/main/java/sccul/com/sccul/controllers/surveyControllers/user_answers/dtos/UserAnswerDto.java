package sccul.com.sccul.controllers.surveyControllers.user_answers.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sccul.com.sccul.models.surveyModels.questions.Question;
import sccul.com.sccul.models.surveyModels.user_answer.UserAnswer;
import sccul.com.sccul.models.user.User;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserAnswerDto{
    private Long id;
    private int answer;
    private Question question;
    private User user;

    public UserAnswer castToUserAnswer() {
        if(getAnswer()>3 || getAnswer()<1){
            return null;
        }
        return new UserAnswer(getId(),getUser(),getAnswer(),getQuestion()
        );
    }
}
