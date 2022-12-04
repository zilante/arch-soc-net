package archsocnet.service;

import archsocnet.model.Subscription;
import archsocnet.model.User;
import archsocnet.payload.response.UserResponse;
import archsocnet.repository.SubscriptionRepository;
import archsocnet.repository.UserRepository;
import archsocnet.service.utils.NotNullSubscriptionGetter;
import archsocnet.service.utils.NotNullUserGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service which allows getting, creating and removing subscriptions
 */
@Service
public class SubscriptionService {

    private SubscriptionRepository subscriptionRepository;

    private UserRepository userRepository;

    @Autowired
    public SubscriptionService(
            SubscriptionRepository subscriptionRepository,
            UserRepository userRepository
    ) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    public List<UserResponse> getSubscriptions(String username) {
        NotNullUserGetter.throwUnlessUserExists(userRepository, username);

        return subscriptionRepository.findAllByUserUsername(username).stream().map(
                (subscription) -> {
                    UserResponse subscriptionResponse = new UserResponse();
                    User subscribed = subscription.getSubscribed();

                    subscriptionResponse.setFirstName(subscribed.getFirstName());
                    subscriptionResponse.setSecondName(subscribed.getSecondName());
                    subscriptionResponse.setUsername(subscribed.getUsername());

                    return subscriptionResponse;
                }
        ).collect(Collectors.toList());
    }

    public void createSubscription(String username, String subscribedUsername) {
        User user = NotNullUserGetter.get(userRepository, username);
        User subscribed = NotNullUserGetter.get(
                userRepository,
                subscribedUsername
        );

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setSubscribed(subscribed);

        subscriptionRepository.save(subscription);
    }

    @Transactional
    public void removeSubscription(String username, String subscribedUsername) {
        NotNullUserGetter.throwUnlessUserExists(userRepository, username);
        NotNullUserGetter.throwUnlessUserExists(userRepository, subscribedUsername);
        NotNullSubscriptionGetter.throwUnlessSubscriptionExists(
                subscriptionRepository,
                username,
                subscribedUsername
        );

        subscriptionRepository.deleteByUserUsernameAndSubscribedUsername(
                username,
                subscribedUsername
        );
    }
}
