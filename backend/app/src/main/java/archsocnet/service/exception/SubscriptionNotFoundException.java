package archsocnet.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        code = HttpStatus.NOT_FOUND,
        reason = SubscriptionNotFoundException.message
)
public class SubscriptionNotFoundException extends NotFoundException {

    public final static String message = "Subscription is not found";

    public SubscriptionNotFoundException() {
        super(message);
    }
}
