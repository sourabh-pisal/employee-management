package learn.spring.employeemanagement.security.web;

import learn.spring.employeemanagement.security.dto.UserDto;
import learn.spring.employeemanagement.security.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping(value = "/create", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        userDetailsService.saveUser(userDto);
    }
}
