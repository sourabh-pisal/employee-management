package learn.spring.employeemanagement.employee.service;

import learn.spring.employeemanagement.employee.domain.Employee;
import learn.spring.employeemanagement.employee.dto.EmployeeDto;
import learn.spring.employeemanagement.employee.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class EmployeeService {

    private final ModelMapper mapper;
    private final EmployeeRepository employeeRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.getAllEmployees()
                .stream()
                .map(employee -> mapper.map(employee, EmployeeDto.class))
                .collect(toList());
    }

    public EmployeeDto saveEmployee(EmployeeDto employee) {
        Employee employeeToBeSaved = mapper.map(employee, Employee.class);
        Employee savedEmployee = employeeRepository.save(employeeToBeSaved);
        return mapper.map(savedEmployee, EmployeeDto.class);
    }
}
