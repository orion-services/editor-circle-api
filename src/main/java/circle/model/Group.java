package circle.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String groupName;

    @ManyToMany
    private List<User> users;

    @OneToMany(mappedBy = "group")
    @JsonIgnore
    private List<Activity> activities;

}
