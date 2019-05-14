package learn.spring.employeemanagement.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import learn.spring.employeemanagement.security.dto.UserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static learn.spring.employeemanagement.security.config.SecurityConstants.*;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(HEADER_STRING);

        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            SecurityContextHolder.getContext()
                    .setAuthentication(getUsernamePasswordAuthenticationToken(token));
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(String token) throws IOException {
        String userJson = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
        if (userJson != null) {
            UserDto userDto = new ObjectMapper().readValue(userJson, UserDto.class);
            return new UsernamePasswordAuthenticationToken(userDto.getUserName(), null, getGrantedAuthority(userDto));
        }
        return null;
    }

    private List<SimpleGrantedAuthority> getGrantedAuthority(UserDto userDto) {
        return userDto.getRoles().stream().map(SimpleGrantedAuthority::new).collect(toList());
    }
}
