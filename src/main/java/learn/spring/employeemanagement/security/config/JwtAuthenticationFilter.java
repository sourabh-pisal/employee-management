package learn.spring.employeemanagement.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import learn.spring.employeemanagement.security.dto.UserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static learn.spring.employeemanagement.security.config.SecurityConstants.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            UserDto userDto = new ObjectMapper().readValue(request.getInputStream(), UserDto.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDto.getUserName(),
                            userDto.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws JsonProcessingException {

        final String token = JWT.create()
                .withSubject(getUserJson((User) authResult.getPrincipal()))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }

    private String getUserJson(User user) throws JsonProcessingException {
        UserDto userDto = new UserDto(user.getUsername(), user.getPassword(), getRolesFromAuthority(user));
        return new ObjectMapper().writeValueAsString(userDto);
    }

    private List<String> getRolesFromAuthority(User user) {
        return user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(toList());
    }
}
