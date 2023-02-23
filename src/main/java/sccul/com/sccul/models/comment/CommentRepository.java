package sccul.com.sccul.models.comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    boolean existsByComment(String comment);

}
