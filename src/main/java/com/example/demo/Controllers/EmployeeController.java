package com.example.demo.Controllers;

import com.example.demo.Classes.Employee;
import com.example.demo.Services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return employee != null ? new ResponseEntity<>(employee, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/status/{status}")
    public List<Employee> getDoctorsByStatus(@PathVariable String status) {
        return employeeService.getDoctorsByStatus(status);
    }

    @GetMapping("/department/{department}")
    public List<Employee> getDoctorsByDepartment(@PathVariable String department) {
        return employeeService.getDoctorsByDepartment(department);
    }

    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<Employee> changeEmployeeStatus(@PathVariable Long id, @PathVariable String status) {
    Employee updatedEmployee = employeeService.changeEmployeeStatus(id, status);
    return updatedEmployee != null ? new ResponseEntity<>(updatedEmployee, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

    @PutMapping("/{id}/department/{department}")
    public ResponseEntity<Employee> changeEmployeeDepartment(@PathVariable Long id, @PathVariable String department) {
        Employee updatedEmployee = employeeService.changeEmployeeDepartment(id, department);
        return updatedEmployee != null ? new ResponseEntity<>(updatedEmployee, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
