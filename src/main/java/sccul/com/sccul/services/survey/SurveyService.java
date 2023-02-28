package sccul.com.sccul.services.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sccul.com.sccul.models.survey.Survey;
import sccul.com.sccul.models.survey.SurveyRepository;
import sccul.com.sccul.utils.CustomResponse;

import java.util.List;

@Service
@Transactional
public class SurveyService {

    @Autowired
    private SurveyRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Survey>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    //get one survey
    @Transactional(readOnly = true)
    public CustomResponse<Survey> getOne(long id){
        if(!this.repository.existsById(id)){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "La encuesta no existe"
            );
        }

        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional
    public CustomResponse<Survey> insert(Survey survey){
        return new CustomResponse<>(
                this.repository.save(survey),
                false,
                200,
                "Ok"
        );
    }

    //Update
    @Transactional
    public CustomResponse<Survey> update(Survey survey){
        if(!this.repository.existsById(survey.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "La encuesta no existe"
            );
        }

        return new CustomResponse<>(
                this.repository.save(survey),
                false,
                200,
                "Ok"
        );
    }
}
