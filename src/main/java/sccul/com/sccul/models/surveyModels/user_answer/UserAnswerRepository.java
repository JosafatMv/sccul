package sccul.com.sccul.models.surveyModels.user_answer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

    //query para contar cuantos escogieron la misma respuesta
    @Query(
        value = "SELECT COUNT(*) FROM user_answers WHERE answer_id = :answer_id",
        nativeQuery = true
    )
    Long countByAnswerId(@Param("answer_id") Long answer_id);

    //query para buscar por usuario y pregunta
    @Query(
        value = "SELECT * FROM user_answers WHERE user_id = :user_id AND answer_id = :answer_id",
        nativeQuery = true
    )
    UserAnswer findByUserIdAndAnswerId(@Param("user_id") Long user_id, @Param("answer_id") Long answer_id);


    //query para buscar por usuario y encuesta
    @Query(
        value = "select * from user_answers where user_id = :user_id AND answer_id in (select id from answers where question_id in (select id from questions where survey_id = :survey_id))",
        nativeQuery = true
    )
    List<UserAnswer> findByUserIdAndSurveyId(@Param("user_id") Long user_id, @Param("survey_id") Long survey_id);

    //query para buscar por encuesta
    @Query(
        value = "select * from user_answers where answer_id in (select id from answers where question_id in (select id from questions where survey_id = :survey_id))",
        nativeQuery = true
    )
    List<UserAnswer> findBySurveyId(@Param("survey_id") Long survey_id);



}
