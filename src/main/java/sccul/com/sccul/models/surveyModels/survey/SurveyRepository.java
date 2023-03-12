package sccul.com.sccul.models.surveyModels.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sccul.com.sccul.models.surveyModels.questions.Question;

import java.util.Optional;

public interface SurveyRepository extends JpaRepository<Survey, Long>{

    Optional<Survey> findById(Long id);
    Optional<Survey> findByCourseId(Long id);
    boolean existsById(Long id);

    boolean existsByCourseId(Long id);

    //query que cuenta la cantidad de surveys por curso
    @Query(
        value = "SELECT COUNT(*) FROM surveys WHERE course_id = :course_id",
        nativeQuery = true
    )
    Integer countByCourseId(Long course_id);


    //findnysirvey
}
