package sccul.com.sccul.models.surveyModels.answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    //get by question
    @Query(
        value = "SELECT * FROM answers WHERE question_id = :question_id",
        nativeQuery = true
    )
    Answer findByQuestionId(@Param("question_id") Long question_id);

    //Contador de respuestas por pregunta
    @Query(
        value = "SELECT COUNT(*) FROM answers WHERE question_id = :question_id",
        nativeQuery = true
    )
    Long countByQuestionId(@Param("question_id") Long question_id);

}
