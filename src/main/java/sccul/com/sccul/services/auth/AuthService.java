package sccul.com.sccul.services.auth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sccul.com.sccul.models.user.User;
import sccul.com.sccul.models.user.UserRepository;
import sccul.com.sccul.utils.CustomResponse;

@Service
public class AuthService {
    @Autowired
    private UserRepository repository;

    public CustomResponse<User> register(User user){
        if(this.repository.existsByEmail(user.getEmail())){
            return new CustomResponse<User>(
                null,
                true,
                400,
                "El email ya existe"
            );
        }

        if(this.repository.existsByPhoneNumber(user.getPhoneNumber())){
            return new CustomResponse<User>(
                null,
                true,
                400,
                "El número de teléfono ya existe"
            );
        }

        String passwordEncoded = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(passwordEncoded);

        return new CustomResponse<User>(
            this.repository.saveAndFlush(user),
            false,
            200,
            "Ok"
        );
    }

}
