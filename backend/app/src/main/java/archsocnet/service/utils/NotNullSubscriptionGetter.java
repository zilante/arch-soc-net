package archsocnet.service.utils;

import archsocnet.model.Subscription;
import archsocnet.repository.SubscriptionRepository;
import archsocnet.service.exception.SubscriptionNotFoundException;

import java.util.Optional;

public class NotNullSubscriptionGetter {

    public static Subscription get(
            SubscriptionRepository subscriptionRepository,
            String username,
            String subscribedUsername
    ) {
        Optional<Subscription> subscription = subscriptionRepository
                .findByUserUsernameAndSubscribedUsername(
                        username,
                        subscribedUsername
                );

        if (subscription.isEmpty()) {
            throw new SubscriptionNotFoundException();
        }

        return subscription.get();
    }

    public static void throwUnlessSubscriptionExists(
            SubscriptionRepository subscriptionRepository,
            String username,
            String subscribedUsername
    ) {
        Optional<Subscription> subscription = subscriptionRepository
                .findByUserUsernameAndSubscribedUsername(
                        username,
                        subscribedUsername
                );

        if (subscription.isEmpty()) {
            throw new SubscriptionNotFoundException();
        }
    }
}
