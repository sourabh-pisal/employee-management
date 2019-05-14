package learn.spring.employeemanagement.security.config;

public interface SecurityConstants {
    String SECRET = "SecretKeyToGenJWTs";
    Long EXPIRATION_TIME = 864_000_000L; // 10 days
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
    String SIGN_UP_URL = "/users/create";
}
