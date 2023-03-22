package ch.timofey.sb.domain.role;

import ch.timofey.sb.domain.authority.Authority;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int id;

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany()
    @JoinTable(
            name = "role_authority",
            joinColumns = @JoinColumn(name = "id_role", referencedColumnName = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "id_authority", referencedColumnName = "authority_id"))
    private Set<Authority> authoritySet;
}
