package archsocnet.service;

import archsocnet.model.Comment;
import archsocnet.model.Post;
import archsocnet.model.User;
import archsocnet.payload.request.CommentRequest;
import archsocnet.payload.response.CommentResponse;
import archsocnet.payload.response.UserResponse;
import archsocnet.repository.CommentRepository;
import archsocnet.repository.PostRepository;
import archsocnet.repository.UserRepository;
import archsocnet.service.utils.NotNullPostGetter;
import archsocnet.service.utils.NotNullUserGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service which allows getting and creating comments
 */
@Service
public class CommentService {
    private CommentRepository commentRepository;

    private UserRepository userRepository;

    private PostRepository postRepository;

    @Autowired
    public CommentService(
            CommentRepository commentRepository,
            UserRepository userRepository,
            PostRepository postRepository
    ) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<CommentResponse> getComments(Integer postId) {
        NotNullPostGetter.throwUnlessPostExists(postRepository, postId);

        return commentRepository.findAllByPostId(postId).stream().map(
                (comment) -> {
                    CommentResponse commentResponse = new CommentResponse();
                    UserResponse author = new UserResponse();

                    User user = comment.getUser();
                    author.setFirstName(user.getFirstName());
                    author.setSecondName(user.getSecondName());
                    author.setUsername(user.getUsername());

                    commentResponse.setAuthor(author);
                    commentResponse.setText(comment.getText());

                    return commentResponse;
                }
        ).collect(Collectors.toList());
    }

    public void createComment(CommentRequest commentRequest) {
        Comment comment = new Comment();

        User user = NotNullUserGetter.get(
                userRepository,
                commentRequest.getUsername()
        );
        Post post = NotNullPostGetter.get(
                postRepository,
                commentRequest.getPostId()
        );

        comment.setUser(user);
        comment.setPost(post);
        comment.setText(commentRequest.getText());

        commentRepository.save(comment);
    }
}
