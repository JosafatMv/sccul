package sccul.com.sccul.services.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sccul.com.sccul.models.comment.Comment;
import sccul.com.sccul.models.comment.CommentRepository;
import sccul.com.sccul.utils.CustomResponse;

import java.util.List;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository repository;

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
        if (this.repository.existsByComment(comment.getComment())){
            return new CustomResponse<Comment>(
                null,
                true,
                400,
                "Comentario ya registrado"
            );
        }
        return new CustomResponse<Comment>(
            this.repository.saveAndFlush(comment),
            false,
            200,
            "El comentario fue registrado correctamente"
        );
    }

    //update
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Comment> update(Long id, Comment comment){
        if(!this.repository.existsById(id)){
            return new CustomResponse<Comment>(
                null,
                true,
                400,
                "El comentario no existe"
            );
        }
        comment.setId(id);
        return new CustomResponse<Comment>(
            this.repository.saveAndFlush(comment),
            false,
            200,
            "El comentario fue actualizado correctamente"
        );
    }
}
