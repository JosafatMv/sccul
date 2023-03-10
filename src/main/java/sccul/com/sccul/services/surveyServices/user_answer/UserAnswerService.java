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

    //insert
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<UserAnswer> insert(UserAnswer userAnswer){
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
    //saveALl
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<List<UserAnswer>> saveAll(List<UserAnswer> userAnswers){
        if(userAnswers.size()>10){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "No se pueden guardar mas de 10 respuestas"
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
