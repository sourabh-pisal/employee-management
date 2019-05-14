package learn.spring.employeemanagement.security.service;

import learn.spring.employeemanagement.security.Repository.ApplicationUserRepository;
import learn.spring.employeemanagement.security.domain.ApplicationUser;
import learn.spring.employeemanagement.security.dto.UserDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Collections.singletonList;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        SimpleGrantedAuthority s = new SimpleGrantedAuthority("ADMIN");
        return new User(applicationUser.getUserName(), applicationUser.getPassword(), singletonList(s));
    }

    public void saveUser(UserDto userDto) {
        ApplicationUser user = mapper.map(userDto, ApplicationUser.class);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }
}
