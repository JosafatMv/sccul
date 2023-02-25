package sccul.com.sccul.services.inscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sccul.com.sccul.models.course.CourseRepository;
import sccul.com.sccul.models.inscription.Inscription;
import sccul.com.sccul.models.inscription.InscriptionRepository;
import sccul.com.sccul.models.user.UserRepository;
import sccul.com.sccul.utils.CustomResponse;

import java.util.List;

@Service
@Transactional
public class InscriptionService {

    @Autowired
    InscriptionRepository repository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Inscription>> getAll() {
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<Inscription> getOne(long id) {
        if (!this.repository.existsById(id)) {
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "La inscripción no existe"
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
    public CustomResponse<Inscription> insert(Inscription inscription) {

        if (!this.courseRepository.existsById(inscription.getCourse().getId())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "No existe el curso seleccionado"
            );
        }

        if (!this.userRepository.existsById(inscription.getUser().getId())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "No existe el usuario seleccionado"
            );
        }

        if (this.repository.existsByUserIdAndCourseIdAndStatus(inscription.getUser().getId(), inscription.getCourse().getId(), "inscrito")) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Ya está inscrito en este curso"
            );
        }

        if (this.repository.existsByUserIdAndCourseIdAndStatus(inscription.getUser().getId(), inscription.getCourse().getId(), "comprado")) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Ya compró este curso"
            );
        }

        return new CustomResponse<>(
                this.repository.save(inscription),
                false,
                200,
                "Ok"
        );
    }


    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Inscription> removeInscription(long id) {
        if (!this.repository.existsById(id)) {
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "La inscripción no existe"
            );
        }

        if (!this.repository.existsByIdAndStatus(id, "inscrito")) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "No se encuentra inscrito al curso"
            );
        }

        if (this.repository.existsByIdAndStatus(id, "comprado")) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "No se puede eliminar la inscripción porque ya compró el curso"
            );
        }

        this.repository.deleteById(id);

        return new CustomResponse<>(
                null,
                false,
                200,
                "Inscripción eliminada con éxito"
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Inscription> changeStatus(long id) {
        if (!this.repository.existsById(id)) {
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "La inscripción no existe"
            );
        }

        if (this.repository.existsByIdAndStatus(id, "comprado")) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "No se puede cambiar de status porque ya compró el curso"
            );
        }

        this.repository.changeStatus(id);

        return new CustomResponse<>(
                null,
                false,
                200,
                "Curso comprado con éxito"
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Inscription> changePercentage(Inscription inscription, double newPercentage) {


        if (!this.repository.existsById(inscription.getId())) {
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "La inscripción no existe"
            );
        }

        inscription = this.repository.findById(inscription.getId()).get();

        if (!this.repository.existsByIdAndStatus(inscription.getId(), "comprado")) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "No se puede cambiar el porcentaje porque no ha comprado el curso"
            );
        }

        if(newPercentage < 0 || newPercentage > 100) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "El porcentaje debe estar entre 0 y 100"
            );
        }

        if (inscription.getFullPercentage() == 100) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "El porcentaje ya está al 100%"
            );
        }

        if (inscription.getFullPercentage() > newPercentage) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "El porcentaje no puede ser menor al actual"
            );
        }

        this.repository.changePercentage(inscription.getId(), newPercentage);

        return new CustomResponse<>(
                null,
                false,
                200,
                "Porcentaje actualizado con éxito"
        );

    }


}
