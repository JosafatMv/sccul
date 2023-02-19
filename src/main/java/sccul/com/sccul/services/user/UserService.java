package sccul.com.sccul.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sccul.com.sccul.models.user.User;
import sccul.com.sccul.models.user.UserRepository;
import sccul.com.sccul.utils.CustomResponse;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<User>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<User> getOne(long id){
        if(!this.repository.existsById(id)){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "El usuario no existe"
            );
        }

        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<User> insert(User user){
        if(this.repository.existsByEmail(user.getEmail())){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Ya existe un usuario registrado con ese email"
            );
        }

        if(this.repository.existsByPhoneNumber(user.getPhoneNumber())){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Ya existe un usuario registrado con ese número de teléfono"
            );
        }

        return new CustomResponse<>(
                this.repository.saveAndFlush(user),
                false,
                200,
                "Usuario registrado correctamente"
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<User> update(User user){
        if(!this.repository.existsById(user.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "El usuario no existe"
            );
        }

        if(this.repository.existsByEmailAndIdNot(user.getEmail(), user.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Ya existe un usuario registrado con ese email"
            );
        }

        if(this.repository.existsByPhoneNumberAndIdNot(user.getPhoneNumber(), user.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Ya existe un usuario registrado con ese número de teléfono"
            );
        }

        return new CustomResponse<>(
                this.repository.saveAndFlush(user),
                false,
                200,
                "Usuario actualizado correctamente"
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Integer> changeStatus(User user){
        if(!this.repository.existsById(user.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "El usuario no existe"
            );
        }

        return new CustomResponse<>(
                this.repository.updateStatusById(user.getStatus(), user.getId()),
                false,
                200,
                "Status del usuario actualizado correctamente"
        );

    }



}
