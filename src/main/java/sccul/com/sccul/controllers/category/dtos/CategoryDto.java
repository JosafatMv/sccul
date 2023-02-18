package sccul.com.sccul.controllers.category.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sccul.com.sccul.models.category.Category;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Long category_id;

    @NotEmpty(message = "Campo obligatorio")
    @Size(max = 100, message = "El nombre de la categoría debe tener máximo 100 caracteres")
    private String name;

    public Category castToCategory() {
        return new Category(getCategory_id(), getName(), null);
    }

}
