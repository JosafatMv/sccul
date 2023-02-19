package sccul.com.sccul.controllers.category.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sccul.com.sccul.models.category.Category;

@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {

    public CategoryDto(){
        this.status = true;
    }

    private Long id;

    @NotEmpty(message = "Campo obligatorio")
    @Size(max = 100, message = "El nombre de la categoría debe tener máximo 100 caracteres")
    private String name;

    private Boolean status;


    public Category castToCategory() {
        return new Category(getId(), getName(), getStatus(), null);
    }

}
