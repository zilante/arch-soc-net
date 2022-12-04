package archsocnet.repository;

import archsocnet.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    List<Subscription> findAllByUserUsername(String username);

    Optional<Subscription> findByUserUsernameAndSubscribedUsername(
            String username,
            String subscribedUsername
    );

    void deleteByUserUsernameAndSubscribedUsername(
            String username,
            String subscribedUsername
    );
}
