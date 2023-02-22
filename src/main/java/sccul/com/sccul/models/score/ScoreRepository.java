package sccul.com.sccul.models.score;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sccul.com.sccul.models.course.Course;
import sccul.com.sccul.models.user.User;

public interface ScoreRepository extends JpaRepository<Score, Long>{
    Optional<List<Score>> findByCourse(Course course);
    Optional<Score> findByUserAndCourse(User user, Course course);



   /*  @Query(
        value="SELECT * FROM sccul.scores where course_id=:curso;",nativeQuery = true)
    List<Score> findByCourse(@Param("curso") Long course);
*/
    @Query(
        value="SELECT AVG(score) FROM scores where course_id = :course",nativeQuery = true)
    Double getAverageScore(@Param("course") Long course);

    @Query(
        value="SELECT COUNT(*)  FROM scores  WHERE user_id = :user and course_id= :course ;",nativeQuery = true)
    int getCount(@Param("user") Long user, @Param("course") Long course);
}
