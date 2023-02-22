package sccul.com.sccul.controllers.score.dtos;




import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sccul.com.sccul.models.course.Course;
import sccul.com.sccul.models.score.Score;
import sccul.com.sccul.models.user.User;

@AllArgsConstructor
@Getter
@Setter
public class ScoreDto {
    private Long id;
    
    private User user;

    private Course course;

    @NotNull(message = "Campo obligatorio")
    @DecimalMin(value = "0", message = "El score debe ser mayor o igual a 0")
    @DecimalMax(value = "5", message = "El score debe ser menor o igual a 5")
    private Long score;  

    public Score castToScore() {
        return new Score(getId(), getUser(), getCourse(), getScore());
    }
}
