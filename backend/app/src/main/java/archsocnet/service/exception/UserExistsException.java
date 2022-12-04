package archsocnet.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception which is thrown if we can't create new user
 * since user with his username already exists
 */
@ResponseStatus(
        code = HttpStatus.BAD_REQUEST,
        reason = UserExistsException.message
)
public class UserExistsException extends RuntimeException {

    public final static String message =
            "Signup failed: user with username already exists";

    public UserExistsException() {
        super(message);
    }
}
