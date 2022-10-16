package circle.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "_Group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String groupName;

    @ManyToMany
    private List<User> users;

    public void addUser(User user) {
        this.users.add(user);
        user.getGroups().add(this);
    }
}
