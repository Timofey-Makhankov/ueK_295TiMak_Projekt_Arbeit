package ch.timofey.sb.domain.user;

import ch.timofey.sb.domain.authority.Authority;
import ch.timofey.sb.domain.role.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch =FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "role_id"))
    private Set<Role> ruleSet;

    public User(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
