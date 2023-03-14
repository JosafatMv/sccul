package sccul.com.sccul.models.surveyModels.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sccul.com.sccul.models.surveyModels.questions.Question;

import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey, Long>{

    Optional<Survey> findById(Long id);
    Optional<Survey> findByName(String name);
    boolean existsById(Long id);


    boolean existsByName(String name);

    //findnysirvey
}
