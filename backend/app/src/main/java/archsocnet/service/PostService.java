package archsocnet.service;

import archsocnet.model.Post;
import archsocnet.model.User;
import archsocnet.payload.request.PostRequest;
import archsocnet.payload.response.PostResponse;
import archsocnet.payload.response.UserResponse;
import archsocnet.repository.PostRepository;
import archsocnet.repository.UserRepository;
import archsocnet.service.utils.NotNullUserGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service which allows getting and creating posts
 */
@Service
public class PostService {

    private PostRepository postRepository;

    private UserRepository userRepository;

    private SubscriptionService subscriptionService;

    @Autowired
    public PostService(
            PostRepository postRepository,
            UserRepository userRepository,
            SubscriptionService subscriptionService
    ) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.subscriptionService = subscriptionService;
    }

    /**
     * @param username - username of the user whose posts will be returned
     * (posted not by him, but by users from his subscription list)
     */
    public List<PostResponse> getPosts(String username) {
        List<String> subscriptions = subscriptionService.getSubscriptions(
                username
        ).stream().map(UserResponse::getUsername).collect(Collectors.toList());

        return postRepository.findAllByUserUsernameIn(subscriptions).stream().map(
                (post) -> {
                    PostResponse postResponse = new PostResponse();

                    UserResponse author = new UserResponse();
                    author.setFirstName(post.getUser().getFirstName());
                    author.setSecondName(post.getUser().getSecondName());
                    author.setUsername(post.getUser().getUsername());

                    postResponse.setAuthor(author);
                    postResponse.setText(post.getText());
                    postResponse.setId(post.getId());

                    return postResponse;
                }
        ).collect(Collectors.toList());
    }

    public void createPost(PostRequest postRequest) {
        Post post = new Post();
        User user = NotNullUserGetter.get(
                userRepository,
                postRequest.getUsername()
        );

        post.setUser(user);
        post.setText(postRequest.getText());

        postRepository.save(post);
    }
}
