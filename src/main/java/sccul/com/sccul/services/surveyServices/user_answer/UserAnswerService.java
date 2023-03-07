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


    //get countByanserid
    @Transactional(readOnly = true)
    public CustomResponse<Long> countByAnswerId(long id){
        return new CustomResponse<>(
                this.repository.countByAnswerId(id),
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
}
