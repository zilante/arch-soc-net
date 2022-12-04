package archsocnet.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        code = HttpStatus.NOT_FOUND,
        reason = PostNotFoundException.message
)
public class PostNotFoundException extends NotFoundException {

    public final static String message = "Post is not found";

    public PostNotFoundException() {
        super(message);
    }
}
