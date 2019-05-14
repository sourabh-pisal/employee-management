package learn.spring.employeemanagement.security.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityPermissionService {


    public boolean checkUserHasPermission() {
        String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.equalsIgnoreCase("admin");
    }
}
