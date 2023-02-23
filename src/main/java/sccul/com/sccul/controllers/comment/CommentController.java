package sccul.com.sccul.controllers.comment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sccul.com.sccul.controllers.comment.dtos.CommentDto;
import sccul.com.sccul.models.comment.Comment;
import sccul.com.sccul.services.comment.CommentService;
import sccul.com.sccul.utils.CustomResponse;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = {"*"})
public class CommentController {
    @Autowired
    private CommentService service;

    //Get all
    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Comment>>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    //Get one by id
    @GetMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Comment>> getOne(@PathVariable @Positive long id){
        return new ResponseEntity<>(this.service.getOne(id), HttpStatus.OK);
    }

    //Insert
    @PostMapping("/")
    public ResponseEntity<CustomResponse<Comment>> insert(@Valid @RequestBody CommentDto comment) {
        return new ResponseEntity<>(this.service.insert(comment.castToComment()), HttpStatus.CREATED);
    }

    //Update
    @PostMapping("/{id:[0-9]+}")
    public ResponseEntity<CustomResponse<Comment>> update(@PathVariable @Valid long id ,@RequestBody CommentDto comment) {
        return new ResponseEntity<>(this.service.update(id, comment.castToComment()), HttpStatus.OK);
    }
}
