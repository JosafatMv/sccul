package sccul.com.sccul.controllers.inscription.dtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChangePercentageDto {

    @NotNull(message = "El porcentaje no puede estar vacío")
    @DecimalMax(value = "100.0", message = "El porcentaje no puede ser mayor a 100")
    @DecimalMin(value = "0.0", message = "El porcentaje no puede ser menor a 0")
    private Double percentage;

}
