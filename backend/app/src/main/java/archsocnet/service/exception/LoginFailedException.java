package archsocnet.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        code = HttpStatus.BAD_REQUEST,
        reason = LoginFailedException.message
)
public class LoginFailedException extends RuntimeException {

    public final static String message =
            "Login failed: password or username is incorrect";

    public LoginFailedException() {
        super(message);
    }
}
