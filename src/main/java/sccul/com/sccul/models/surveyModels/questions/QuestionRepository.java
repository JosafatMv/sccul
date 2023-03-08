package sccul.com.sccul.models.surveyModels.questions;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long>{
    //query para buscar questions por encuesta
    Optional<Question> findById(Long id);
    Optional<List<Question>> findBySurveyId(Long id);


    //contador de preguntas por encuesta
    @Query(
        value = "SELECT COUNT(*) FROM questions WHERE survey_id = :survey_id",
        nativeQuery = true
    )
    Long countBySurveyId(@Param("survey_id") Long survey_id);

}
