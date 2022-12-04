package archsocnet.service.utils;

import archsocnet.model.User;
import archsocnet.repository.UserRepository;
import archsocnet.service.exception.UserNotFoundException;

import java.util.Optional;

public class NotNullUserGetter {

    public static User get(
            UserRepository userRepository,
            String username
    ) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        return user.get();
    }

    public static void throwUnlessUserExists(
            UserRepository userRepository,
            String username
    ) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
    }
}
