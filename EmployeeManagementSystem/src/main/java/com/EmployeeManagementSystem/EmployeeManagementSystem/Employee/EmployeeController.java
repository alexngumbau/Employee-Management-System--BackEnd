package com.EmployeeManagementSystem.EmployeeManagementSystem.Employee;


import Utilies.EntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;




    // Get all employees
    @GetMapping("/employees/all")
    public List<Employee> getAllEmployees () {
        return employeeService.findAll();
    }

    // Create employee
    @PostMapping("/employees/create")
    public EntityResponse createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/employees/{id}")
    public EntityResponse getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/employees/update")
    public EntityResponse updateEmployee (@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/employees/delete")
    public EntityResponse deleteEmployee (@RequestParam Long id) {
        return employeeService.deleteEmployee(id);
    }



    

}
