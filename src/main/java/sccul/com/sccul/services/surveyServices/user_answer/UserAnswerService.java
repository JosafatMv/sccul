package sccul.com.sccul.services.surveyServices.user_answer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sccul.com.sccul.models.surveyModels.user_answer.UserAnswer;
import sccul.com.sccul.models.surveyModels.user_answer.UserAnswerRepository;
import sccul.com.sccul.utils.CustomResponse;

@Service
@Transactional
public class UserAnswerService {
    @Autowired
    private UserAnswerRepository repository;
/*
Creo que es innecesario el getAll
    //get all
    @Transactional(readOnly = true)
    public CustomResponse<List<UserAnswer>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }
*/
    //get one
    @Transactional(readOnly = true)
    public CustomResponse<UserAnswer> getOne(long id){
        if(!this.repository.existsById(id)){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "La respuesta no existe"
            );
        }

        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false,
                200,
                "Ok"
        );
    }

    //insert que verifica que el usuario solo haya contstestado una vez
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<UserAnswer> insert(UserAnswer userAnswer){
        if(this.repository.countByUserIdAndQuestionId(userAnswer.getUser().getId(),userAnswer.getQuestion().getId())>0){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "El usuario ya ha contestado esta pregunta"
            );
        }
        return new CustomResponse<>(
                this.repository.save(userAnswer),
                false,
                200,
                "Ok"
        );
    }

    //Creo que el update no es necesario


    //get countByQuestionIdAndAnswer
    @Transactional(readOnly = true)
    public CustomResponse<Integer> countByQuestionIdAndAnswer(long idQuestion, int answer){
        return new CustomResponse<>(
                this.repository.countByQuestionIdAndAnswer(idQuestion, answer),
                false,
                200,
                "Ok"
        );
    }

   /* //findByUserIdAndAnswerId

   Cuando lo hice tenia sentido ahora no lo veo

    @Transactional(readOnly = true)
    public CustomResponse<UserAnswer> findByUserIdAndAnswerId(long userId, long answerId){
        return new CustomResponse<>(
                this.repository.findByUserIdAndAnswerId(userId, answerId),
                false,
                200,
                "Ok"
        );
    }*/
    //findByUserIdAndSurveyId
    @Transactional(readOnly = true)
    public CustomResponse<List<UserAnswer>> findByUserIdAndSurveyId(long userId, long surveyId){
        return new CustomResponse<>(
                this.repository.findByUserIdAndSurveyId(userId, surveyId),
                false,
                200,
                "Ok"
        );
    }

    //inserar varios
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<List<UserAnswer>> insertAll(List<UserAnswer> userAnswers){
        return new CustomResponse<>(
                this.repository.saveAll(userAnswers),
                false,
                200,
                "Ok"
        );
    }
/*

De nuevo no encuentro sentido en este metodo ahora

    //findBySurveyId
    @Transactional(readOnly = true)
    public CustomResponse<List<UserAnswer>> findBySurveyId(long surveyId){
        return new CustomResponse<>(
                this.repository.findBySurveyId(surveyId),
                false,
                200,
                "Ok"
        );
    }
*/
}
