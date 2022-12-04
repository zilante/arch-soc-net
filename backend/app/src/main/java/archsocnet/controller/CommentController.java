package archsocnet.controller;

import archsocnet.payload.request.CommentRequest;
import archsocnet.payload.response.CommentResponse;
import archsocnet.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 Controller for handling queries:
  1) get all comments for post
  2) create comment under the post
*/
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private CommentService commentService;

    private final static String CREATION_SUCCESS =
            "Successfully created the comment";

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentResponse>> getComments(
            @RequestParam @NotNull Integer postId
    ) {
        List<CommentResponse> comments = commentService.getComments(postId);

        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createComment(
            @Valid @RequestBody CommentRequest comment
    ) {
        commentService.createComment(comment);

        return new ResponseEntity<>(CREATION_SUCCESS, HttpStatus.OK);
    }
}
