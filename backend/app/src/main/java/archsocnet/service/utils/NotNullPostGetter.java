package archsocnet.service.utils;

import archsocnet.model.Post;
import archsocnet.repository.PostRepository;
import archsocnet.service.exception.PostNotFoundException;

import java.util.Optional;

public class NotNullPostGetter {

    public static Post get(PostRepository postRepository, Integer postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new PostNotFoundException();
        }

        return post.get();
    }

    public static void throwUnlessPostExists(
            PostRepository postRepository,
            Integer postId
    ) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new PostNotFoundException();
        }
    }
}
