package sccul.com.sccul.services.surveyServices.user_answer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sccul.com.sccul.models.course.Course;
import sccul.com.sccul.models.surveyModels.questions.QuestionRepository;
import sccul.com.sccul.models.surveyModels.survey.Survey;
import sccul.com.sccul.models.surveyModels.user_answer.UserAnswer;
import sccul.com.sccul.models.surveyModels.user_answer.UserAnswerRepository;
import sccul.com.sccul.utils.CustomResponse;

@Service
@Transactional
public class UserAnswerService {
    @Autowired
    private UserAnswerRepository repository;

    @Autowired
    private QuestionRepository questionRepository;

    @Transactional(readOnly = true)
    public CustomResponse<List<UserAnswer>> findByUserIdAndSurveyId(Long survey, Long course){
        return new CustomResponse<>(
                this.repository.findAllBySurveyidAndUserid(survey,course).get(),
                false,
                200,
                "Ok"
        );
    }

    //saveALl
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<List<UserAnswer>> saveAll(List<UserAnswer> userAnswers){

        for (int i = 0; i < userAnswers.size(); i++) {
            if(userAnswers.get(i)==null){
                return new CustomResponse<>(
                        null,
                        true,
                        400,
                        "Solo puedes contestar del 1 al 3"
                );
            }
            if(this.repository.countByQuestionIdAndUser(userAnswers.get(i).getQuestion().getId(), userAnswers.get(i).getUser().getId())!=0){
                return new CustomResponse<>(
                        null,
                        true,
                        400,
                        "No puede contestar 2 veces la misma encuesta"
                );
            }
            if(!this.questionRepository.existsById(userAnswers.get(i).getQuestion().getId())){
                return new CustomResponse<>(
                        null,
                        true,
                        400,
                        "La pregunta no existe"
                );
            }

        }
        if(userAnswers.size()!=10){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "No se pueden guardar mas o menos de 10 respuestas"
            );
        }
        return new CustomResponse<>(
                this.repository.saveAllAndFlush(userAnswers),
                false,
                200,
                "Ok"
        );
    }
}
