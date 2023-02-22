package sccul.com.sccul.controllers.section.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sccul.com.sccul.models.course.Course;
import sccul.com.sccul.models.section.Section;

@AllArgsConstructor
@Getter
@Setter
public class SectionDto {
    private Long id;

    @NotEmpty(message = "Campo obligatorio")
    @Size(max = 255, message = "El nombre debe tener máximo 255 caracteres")
    private String name;

    @NotEmpty(message = "Campo obligatorio")
    @Size(max = 255, message = "El nombre debe tener máximo 255 caracteres")
    private String video;

    private Course course;
    public Section castToSection(){
        return new Section(getId(), getName(),getVideo(), getCourse());
    }
}
