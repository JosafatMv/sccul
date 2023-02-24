package sccul.com.sccul.models.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sccul.com.sccul.models.course.Course;
import sccul.com.sccul.models.user.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    boolean existsByUserAndCourse(User user, Course course);

    @Modifying
    @Query(
            value = "UPDATE comments SET comment = :comment WHERE id = :id", nativeQuery = true
    )
    Integer updateCommentById(@Param("comment") String comment, @Param("id") Long id);



}
