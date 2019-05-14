package learn.spring.employeemanagement.security.domain;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

//@Entity
//@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<ApplicationUser> applicationUsers;
}
