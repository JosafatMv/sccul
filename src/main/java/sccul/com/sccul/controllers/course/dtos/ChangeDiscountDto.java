package sccul.com.sccul.controllers.course.dtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sccul.com.sccul.models.course.Course;

@AllArgsConstructor
@Getter
@Setter
public class ChangeDiscountDto {

    private Long id;

    @DecimalMin(value = "1.0", message = "El descuento debe ser mayor o igual a 0.0", inclusive = false)
    @DecimalMax(value = "100.0", message = "El descuento debe ser menor o igual a 100")
    private Double discount;

    public Course castToCourse(){
        return new Course(getId(), null, null, null, 0.0, getDiscount(), 0, 0.0, 0, null, null, null, null, null, null);
    }
}
