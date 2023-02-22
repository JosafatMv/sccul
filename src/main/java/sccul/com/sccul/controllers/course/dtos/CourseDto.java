package sccul.com.sccul.controllers.course.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sccul.com.sccul.models.category.Category;
import sccul.com.sccul.models.course.Course;

@AllArgsConstructor
@Getter
@Setter
public class CourseDto {
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

    @NotEmpty(message = "Campo obligatorio")
    @DecimalMin(value = "0.0", message = "El precio debe ser mayor o igual a 0.0")
    private String price;

    private Category category;

    @DecimalMin(value = "1.0", message = "El descuento debe ser mayor o igual a 0.0", inclusive = false)
    @Max(value = 100, message = "El descuento debe ser menor o igual a 100")
    private Double discount;

    public Course castToCourse(){
        return new Course(getId(), getName(), getDescription(), getImage(), getPrice(), getDiscount(), getCategory(), null, null, null, null, null);
    }
}
