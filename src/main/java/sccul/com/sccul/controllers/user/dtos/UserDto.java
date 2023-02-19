package sccul.com.sccul.controllers.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotEmpty(message = "Campo obligatorio")
    @Email
    private String email;

    @NotEmpty(message = "Campo obligatorio")
    private String password;

    @NotEmpty(message = "Campo obligatorio")
    private String phoneNumber;

    @NotEmpty(message = "Campo obligatorio")
    private String role;

    private String image;

    private Boolean status;

    public User castToUser(){
        return new User(getId(), getName(), getSurname(), getLastname(), getEmail(), getPassword(), getPhoneNumber(), getRole(), getImage(), getStatus(), null, null, null, null, null);
    }
}
