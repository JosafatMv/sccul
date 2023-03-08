package sccul.com.sccul.controllers.auth.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sccul.com.sccul.models.user.User;

@AllArgsConstructor
@Getter
@Setter
public class AuthRegisterDto {
    private Long id;

    @NotEmpty(message = "Campo obligatorio")
    @Size(max = 50, message = "El nombre debe tener máximo 50 caracteres")
    private String name;

    @NotEmpty(message = "Campo obligatorio")
    @Size(max = 45, message = "El apellido paterno debe tener máximo 45 caracteres")
    private String surname;

    @Size(max = 45, message = "El apellido materno debe tener máximo 45 caracteres")
    private String lastname;

    @NotBlank(message = "El email no puede estar en vacío")
    @Email(message = "El correo debe ser válido")
    private String email;

    @NotEmpty(message = "Campo obligatorio")
    private String password;

    @NotEmpty(message = "Campo obligatorio")
    @Size(min = 10, max = 10, message = "El número de teléfono debe tener exactamente 10 caracteres")
    @Pattern(regexp = "^[0-9]{10}$", message = "El número de teléfono debe contener sólo 10 dígitos")
    private String phoneNumber;


    private String image;


    public User castToUser(){
        return new User(getId(), getName(), getSurname(), getLastname(), getEmail(), getPassword(), getPhoneNumber(), null, getImage(), null, null, null, null, null, null);
    }
}
