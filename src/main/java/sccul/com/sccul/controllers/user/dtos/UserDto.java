package sccul.com.sccul.controllers.user.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sccul.com.sccul.models.user.User;

@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    public UserDto(){
        this.status = true;
    }

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
    @Email(message = "El correo debe ser válido", regexp="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\\\"(?:[\\\\x01-\\\\x08\\\\x0b\\\\x0c\\\\x0e-\\\\x1f\\\\x21\\\\x23-\\\\x5b\\\\x5d-\\\\x7f]|\\\\\\\\[\\\\x01-\\\\x09\\\\x0b\\\\x0c\\\\x0e-\\\\x7f])*\\\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\\\x01-\\\\x08\\\\x0b\\\\x0c\\\\x0e-\\\\x1f\\\\x21-\\\\x5a\\\\x53-\\\\x7f]|\\\\\\\\[\\\\x01-\\\\x09\\\\x0b\\\\x0c\\\\x0e-\\\\x7f])+)\\\\])")
    private String email;

    @NotEmpty(message = "Campo obligatorio")
    private String password;

    @NotEmpty(message = "Campo obligatorio")
    @Size(min = 10, max = 10, message = "El número de teléfono debe tener exactamente 10 caracteres")
    @Pattern(regexp = "^[0-9]{10}$", message = "El número de teléfono debe contener sólo 10 dígitos")
    private String phoneNumber;

    @NotEmpty(message = "Campo obligatorio")
    private String role;

    private String image;

    private Boolean status;

    public User castToUser(){
        return new User(getId(), getName(), getSurname(), getLastname(), getEmail(), getPassword(), getPhoneNumber(), getRole(), getImage(), getStatus(), null, null, null, null, null);
    }
}
