package sccul.com.sccul.services.surveyServices.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sccul.com.sccul.models.surveyModels.questions.Question;
import sccul.com.sccul.models.surveyModels.questions.QuestionRepository;
import sccul.com.sccul.utils.CustomResponse;

@Service
@Transactional
public class QuestionService {
    
    @Autowired
    private QuestionRepository repository;


    //get by id
    @Transactional(readOnly = true)
    public CustomResponse<Question> getById(long id){
        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false,
                200,
                "Ok"
        );
    }
    
    //get por survey
    @Transactional(readOnly = true)
    public CustomResponse<List<Question>> getBySurveyId(long survey_id){
        return new CustomResponse<>(
                this.repository.findBySurveyId(survey_id).get(),
                false,
                200,
                "Ok"
        );
    }
    //insert 
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Question> insert(Question question){
        if(this.repository.countBySurveyId(question.getSurvey().getId()) >= 10){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "No se pueden agregar mas de 10 preguntas"
            );
        }

        return new CustomResponse<>(
                this.repository.save(question),
                false,
                200,
                "Ok"
        );
    }
    
    //Update
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Question> update(Question question){
        if(!this.repository.existsById(question.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "La pregunta no existe"
            );
        }

        return new CustomResponse<>(
                this.repository.save(question),
                false,
                200,
                "Ok"
        );
    }

    //

}