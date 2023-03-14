package sccul.com.sccul.services.surveyServices.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sccul.com.sccul.models.course.CourseRepository;
import sccul.com.sccul.models.surveyModels.survey.Survey;
import sccul.com.sccul.models.surveyModels.survey.SurveyRepository;
import sccul.com.sccul.utils.CustomResponse;

import java.util.List;

@Service
@Transactional
public class SurveyService {

    @Autowired
    private SurveyRepository repository;

    @Autowired
    private CourseRepository courseRepository;



    //get all
    @Transactional(readOnly = true)
    public CustomResponse<List<Survey>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }


    //get one survey by id
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

    //get one survey by name
    @Transactional(readOnly = true)
    public CustomResponse<Survey> getOne(String name){
        if(!this.repository.existsByName(name)){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "La encuesta no existe"
            );
        }

        return new CustomResponse<>(
                this.repository.findByName(name).get(),
                false,
                200,
                "Ok"
        );
    }
    //insert
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Survey> insert(Survey survey){
        if(this.repository.existsByName(survey.getName())){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Ya existe una encuesta con ese nombre"
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
