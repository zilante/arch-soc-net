package archsocnet.controller;

import archsocnet.payload.request.PostRequest;
import archsocnet.payload.response.PostResponse;
import archsocnet.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Controller, which handles queries:
 * 1) creating post by user
 * 2) get posts of all users from the subscription list
 */
@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostService postService;

    private final static String CREATION_SUCCESS =
            "Successfully created the post";

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getPosts(
            @RequestParam @NotBlank @NotNull String username
    ) {
        List<PostResponse> posts = postService.getPosts(username);

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createPost(
            @Valid @RequestBody PostRequest post
    ) {
        postService.createPost(post);

        return new ResponseEntity<>(CREATION_SUCCESS, HttpStatus.OK);
    }
}
