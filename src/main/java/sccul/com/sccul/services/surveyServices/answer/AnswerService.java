package sccul.com.sccul.services.surveyServices.answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sccul.com.sccul.models.surveyModels.answer.Answer;
import sccul.com.sccul.models.surveyModels.answer.AnswerRepository;
import sccul.com.sccul.utils.CustomResponse;

import java.util.List;

@Service
@Transactional
public class AnswerService {
    
    @Autowired
    private AnswerRepository repository;


    //getbyquestion
    @Transactional(readOnly = true)
    public CustomResponse<List <Answer>> getByQuestionId(long id){
        if(!this.repository.existsById(id)){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "La respuesta no existe"
            );
        }

        return new CustomResponse<>(
                this.repository.findByQuestionId(id),
                false,
                200,
                "Ok"
        );
    }
    
    //insert que verifica que no existan mas de 3 respuestas por pregunta
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Answer> insert(Answer answer){
        if(this.repository.countByQuestionId(answer.getQuestion().getId()) >= 3){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Ya existen 3 respuestas para esta pregunta"
            );
        }

        return new CustomResponse<>(
                this.repository.save(answer),
                false,
                200,
                "Ok"
        );
    }


    //Update no para cambiar la respuesta sino para corregir errores si se necesita
    //update
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Answer> update(Answer answer){
        if(!this.repository.existsById(answer.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "La respuesta no existe"
            );
        }

        return new CustomResponse<>(
                this.repository.save(answer),
                false,
                200,
                "Ok"
        );
    }

    
}
