package archsocnet.model;

import javax.persistence.*;

/**
 * Entity class representing subscription table from db.
 */
@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "subscribed_id")
    private User subscribed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user1) {
        this.user = user1;
    }

    public User getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(User user2) {
        this.subscribed = user2;
    }
}
