package sccul.com.sccul.controllers.course.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sccul.com.sccul.models.category.Category;
import sccul.com.sccul.models.course.Course;
import sccul.com.sccul.models.section.Section;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseRegisterDto {
    private Long id;

    @NotEmpty(message = "Campo obligatorio")
    @Size(max = 255, message = "El nombre debe tener máximo 255 caracteres")
    private String name;

    @NotEmpty(message = "Campo obligatorio")
    @Size(max = 255, message = "La descripción debe tener máximo 255 caracteres")
    private String description;

    @NotEmpty(message = "Campo obligatorio")
    @Size(max = 255, message = "La imagen debe tener máximo 255 caracteres")
    private String image;

    @NotNull(message = "Campo obligatorio")
    @DecimalMin(value = "0.0", message = "El precio debe ser mayor o igual a 0.0")
    private Double price;

    @NotNull(message = "Campo obligatorio")
    private Category category;

    @DecimalMin(value = "1.0", message = "El descuento debe ser mayor o igual a 0.0")
    @DecimalMax(value = "100.0", message = "El descuento debe ser menor o igual a 100")
    private Double discount;

    private Integer status;

    @NotNull(message = "Campo obligatorio")
    private Set<Section> sections;

    public Course castToCourse(){
        return new Course(getId(), getName(), getDescription(), getImage(), getPrice(), getDiscount(), getStatus(),0,0.0,0, getCategory(), getSections(), null, null, null, null);
    }
}
