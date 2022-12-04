package archsocnet.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        code = HttpStatus.NOT_FOUND,
        reason = UserNotFoundException.message
)
public class UserNotFoundException extends NotFoundException {

    public static final String message = "User is not found";

    public UserNotFoundException() {
        super(message);
    }
}
