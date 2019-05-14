package learn.spring.employeemanagement;

import learn.spring.employeemanagement.security.Repository.ApplicationUserRepository;
import learn.spring.employeemanagement.security.domain.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EmployeeManagementApplication {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            applicationUserRepository.save(new ApplicationUser(
                    null,
                    "admin",
                    bCryptPasswordEncoder.encode("admin")
            ));
        };
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
