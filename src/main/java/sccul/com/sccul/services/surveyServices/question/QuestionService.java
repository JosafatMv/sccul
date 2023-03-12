package sccul.com.sccul.services.surveyServices.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sccul.com.sccul.models.surveyModels.questions.Question;
import sccul.com.sccul.models.surveyModels.questions.QuestionRepository;
import sccul.com.sccul.models.surveyModels.survey.SurveyRepository;
import sccul.com.sccul.utils.CustomResponse;

@Service
@Transactional
public class QuestionService {
    
    @Autowired
    private QuestionRepository repository;
    @Autowired
    private SurveyRepository surveyRepository;

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

    //saveAll
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<List<Question>> saveAll(List<Question> questions){
        if(this.repository.countBySurveyId(questions.get(1).getSurvey().getId()) >= 10){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "No se pueden agregar mas de 10 preguntas"
            );
        }
        if(questions.size()!=10){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "No puedes asignar mas o menos de 10 preguntas a un survey"
            );

        }
        return new CustomResponse<>(
                this.repository.saveAllAndFlush(questions),
                false,
                200,
                "OK"
        );
    }

}