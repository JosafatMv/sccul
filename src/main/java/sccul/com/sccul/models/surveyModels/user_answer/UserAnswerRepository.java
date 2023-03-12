package sccul.com.sccul.models.surveyModels.user_answer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

    @Query(
            value = "SELECT COUNT(*) FROM user_answers WHERE question_id = :question_id AND answer = :answer",nativeQuery = true
    )
    int countByQuestionIdAndAnswer(@Param("question_id") Long question_id, @Param("answer") int answer);

    //countbyquestionidanduser
    @Query(
            value = "SELECT COUNT(*) FROM user_answers WHERE question_id = :question_id AND user_id = :user_id",nativeQuery = true
    )
    int countByQuestionIdAndUser(@Param("question_id") Long question_id, @Param("user_id") Long user_id);
    @Override
    <S extends UserAnswer> List<S> saveAllAndFlush(Iterable<S> entities);

    //useranswers join question, getall useranswers by survey_id and user_id
    @Query(
            value = "SELECT * FROM user_answers WHERE user_id = :user_id and question_id IN (SELECT id FROM questions WHERE survey_id = :survey_id )  ",nativeQuery = true
    )
    Optional<List<UserAnswer>> findAllBySurveyidAndUserid(@Param("survey_id") Long survey_id, @Param("user_id") Long user_id);

}
