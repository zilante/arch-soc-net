package archsocnet.service;

import archsocnet.model.User;
import archsocnet.payload.request.LoginRequest;
import archsocnet.payload.request.SignupRequest;
import archsocnet.repository.UserRepository;
import archsocnet.service.exception.LoginFailedException;
import archsocnet.service.exception.UserExistsException;
import archsocnet.service.utils.NotNullUserGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service for authorization operations
 */
@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login(LoginRequest request) {
        User user = NotNullUserGetter.get(userRepository, request.getUsername());

        if (!user.getPassword().equals(request.getPassword())) {
            throw new LoginFailedException();
        }
    }

    public void signup(SignupRequest request) {
        throwIfUserExists(request.getUsername());
        User user = new User();

        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setSecondName(request.getSecondName());
        user.setPassword(request.getPassword());

        userRepository.save(user);
    }

    private void throwIfUserExists(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new UserExistsException();
        }
    }
}
