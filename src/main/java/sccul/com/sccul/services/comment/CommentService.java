package sccul.com.sccul.services.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sccul.com.sccul.models.comment.Comment;
import sccul.com.sccul.models.comment.CommentRepository;
import sccul.com.sccul.models.course.CourseRepository;
import sccul.com.sccul.models.user.UserRepository;
import sccul.com.sccul.utils.CustomResponse;

import java.util.List;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository repository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    //get all
    @Transactional(readOnly = true)
    public CustomResponse<List<Comment>> getAll(){
        return new CustomResponse<List<Comment>>(
            this.repository.findAll(),
            false,
            200,
            "Ok"
        );
    }

    //get one by id
    @Transactional(readOnly = true)
    public CustomResponse<Comment> getOne(Long id){
        if(!this.repository.existsById(id)){
            return new CustomResponse<Comment>(
                null,
                true,
                404,
                "El comentario no existe"
            );
        }
        return new CustomResponse<Comment>(
            this.repository.findById(id).get(),
            false,
            200,
            "Ok"
        );
    }

    //insert
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Comment> insert(Comment comment){

        if(this.repository.existsByUserAndCourse(comment.getUser(), comment.getCourse())){
            return new CustomResponse<Comment>(
                null,
                true,
                400,
                "El usuario ya ha comentado este curso"
            );
        }

        if(!this.courseRepository.existsById(comment.getCourse().getId())){
            return new CustomResponse<Comment>(
                null,
                true,
                400,
                "El curso no existe"
            );
        }

        if(!this.userRepository.existsById(comment.getUser().getId())){
            return new CustomResponse<Comment>(
                null,
                true,
                400,
                "El usuario no existe"
            );
        }

        //Puede que necesitemos traer los datos actualizados de la base
        return new CustomResponse<Comment>(
                this.repository.saveAndFlush(comment),
            false,
            200,
            "El comentario fue registrado correctamente"
        );
    }

    //update
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Integer> update(Long id, Comment comment){
        if(!this.repository.existsById(id)){
            return new CustomResponse<>(
                null,
                true,
                400,
                "El comentario no existe"
            );
        }

        comment.setId(id);
        return new CustomResponse<Integer>(
            this.repository.updateCommentById(comment.getComment(), comment.getId()),
            false,
            200,
            "El comentario fue actualizado correctamente"
        );
    }
}
