package learn.spring.employeemanagement.security.Repository;

import learn.spring.employeemanagement.security.domain.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {

    Optional<ApplicationUser> findByUserName(String userName);
}
