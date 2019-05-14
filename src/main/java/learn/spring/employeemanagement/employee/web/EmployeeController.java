package learn.spring.employeemanagement.employee.web;

import learn.spring.employeemanagement.employee.dto.EmployeeDto;
import learn.spring.employeemanagement.employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(value = "/all", produces = APPLICATION_JSON_VALUE)
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping(value = "/save", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public EmployeeDto save(@RequestBody EmployeeDto employee) {
        return employeeService.saveEmployee(employee);
    }
}
