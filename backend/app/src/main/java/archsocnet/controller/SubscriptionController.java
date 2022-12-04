package archsocnet.controller;

import archsocnet.payload.response.UserResponse;
import archsocnet.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Controller, which handles queries:
 * 1) create subscription of one user to another
 * 2) get all subscriptions of the user
 * 3) remove subscription of one user to another
 */
@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    private SubscriptionService subscriptionService;

    public static String CREATION_SUCCESS =
            "Successfully created the subscription";
    public static String REMOVAL_SUCCESS =
            "Successfully removed the subscription";

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getSubscriptions(
            @RequestParam @NotBlank @NotNull String username
    ) {
        List<UserResponse> subscriptions =
                subscriptionService.getSubscriptions(username);

        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    /**
     * @param username - username of the user, who is subscribing
     * @param subscribedUsername - username of the user whom the first user
     * is subscribing to
     */
    @PostMapping("/create")
    public ResponseEntity<String> createSubscription(
            @RequestParam @NotBlank @NotNull String username,
            @RequestParam @NotBlank @NotNull String subscribedUsername
    ) {
        subscriptionService.createSubscription(username, subscribedUsername);

        return new ResponseEntity<>(CREATION_SUCCESS, HttpStatus.OK);
    }

    /**
     * @param username - username of the user, who is subscribing
     * @param subscribedUsername - username of the user whom the first user
     * is subscribing to
     */
    @PostMapping("/remove")
    public ResponseEntity<String> removeSubscription(
            @RequestParam @NotBlank @NotNull String username,
            @RequestParam @NotBlank @NotNull String subscribedUsername
    ) {
        subscriptionService.removeSubscription(username, subscribedUsername);

        return new ResponseEntity<>(REMOVAL_SUCCESS, HttpStatus.OK);
    }
}
