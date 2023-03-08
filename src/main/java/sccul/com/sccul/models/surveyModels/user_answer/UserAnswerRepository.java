package sccul.com.sccul.models.surveyModels.user_answer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

    //query para contar cuantos escogieron la misma respuesta
    @Query(
        value = "SELECT COUNT(*) FROM user_answers WHERE question_id = :question_id AND answer = :answer",
        nativeQuery = true
    )
    Long countByAnswerId(@Param("question_id") Long question_id, @Param("answer") int answer);

    //query para buscar por usuario y pregunta
//    @Query(
//        value = "SELECT * FROM user_answers WHERE user_id = :user_id AND answer_id = :answer_id",
//        nativeQuery = true
//    )
//    UserAnswer findByUserIdAndAnswerId(@Param("user_id") Long user_id, @Param("answer_id") Long answer_id);


    //query para buscar por usuario y encuesta
    @Query(
        value = "select from users where id = :user_id and question_id in (select id from questions where survey_id = :survey_id)",
        nativeQuery = true
    )
    List<UserAnswer> findByUserIdAndSurveyId(@Param("user_id") Long user_id, @Param("survey_id") Long survey_id);

    //query para buscar por encuesta
    @Query(
        value = "select * from user_answers where question_id in (select id from questions where survey_id = :survey_id)",
        nativeQuery = true
    )
    List<UserAnswer> findBySurveyId(@Param("survey_id") Long survey_id);

    @Query(
            value = "SELECT COUNT(*) FROM user_answers WHERE question_id = :question_id AND answer = :answer",nativeQuery = true
    )
    int countByQuestionIdAndAnswer(@Param("question_id") Long question_id, @Param("answer") int answer);

    //contador por usuario y pregunta
    @Query(
        value = "SELECT COUNT(*) FROM user_answers WHERE user_id = :user_id AND question_id = :question_id",
        nativeQuery = true
    )
    int countByUserIdAndQuestionId(@Param("user_id") Long user_id, @Param("question_id") Long question_id);
    ;
}

