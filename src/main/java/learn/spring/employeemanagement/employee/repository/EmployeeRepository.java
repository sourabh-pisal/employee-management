package learn.spring.employeemanagement.employee.repository;

import learn.spring.employeemanagement.employee.domain.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("FROM Employee ORDER BY id ASC")
    List<Employee> getAllEmployees();
}
