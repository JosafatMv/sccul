package sccul.com.sccul.controllers.comment.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sccul.com.sccul.models.comment.Comment;
import sccul.com.sccul.models.course.Course;
import sccul.com.sccul.models.user.User;

@AllArgsConstructor
@Getter
@Setter
public class CommentDto {

    private Long id;
    private String comment;
    private User user;
    private Course course;

    public Comment castToComment() {
        return new Comment(getId(), getComment(), getUser(), getCourse(), null);
    }
}
