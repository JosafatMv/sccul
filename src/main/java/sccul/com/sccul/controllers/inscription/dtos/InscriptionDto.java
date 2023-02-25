package sccul.com.sccul.controllers.inscription.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sccul.com.sccul.models.course.Course;
import sccul.com.sccul.models.inscription.Inscription;
import sccul.com.sccul.models.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InscriptionDto {

    private Long id;
    private User user;
    private Course course;

    @NotNull(message = "El porcentaje no puede estar vacío")
    @DecimalMax(value = "100.0", message = "El porcentaje no puede ser mayor a 100")
    @DecimalMin(value = "0.0", message = "El porcentaje no puede ser menor a 0")
    private Double full_percentage;

    @NotBlank(message = "El estado no puede estar vacío")
    @Size(max = 20, min = 1, message = "El estado no puede tener más de 20 caracteres")
    private String status;

    public Inscription castToInscription() {
        return new Inscription(
                this.id,
                this.user,
                this.course,
                this.full_percentage,
                this.status
        );
    }

}
