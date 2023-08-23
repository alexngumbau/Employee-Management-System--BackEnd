package com.EmployeeManagementSystem.EmployeeManagementSystem.Employee;


import Utilies.EntityResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public EntityResponse createEmployee(Employee employee) {

        EntityResponse response = new EntityResponse();

        try {

            Employee createdEmployee = employeeRepository.save(employee);

            response.setMessage("Employee created successfully");
            response.setStatusCode(HttpStatus.OK.value());
            response.setEntity(createdEmployee);

        } catch (Exception e) {
            response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

    public EntityResponse getEmployeeById (Long id) {

        EntityResponse response = new EntityResponse();

        try {

            Optional<Employee> employee = employeeRepository.findById(id);

            if (employee.isPresent()) {

                response.setMessage("Employee retrieved successfully");
                response.setStatusCode(HttpStatus.OK.value());
                response.setEntity(employee);
            } else {
                response.setMessage("Employee with ID: " + id + " does not exist");
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
            }


        } catch (Exception e) {
            response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        return response;

    }

    public EntityResponse updateEmployee(Employee employee) {
        EntityResponse response = new EntityResponse();

        try {
            Optional<Employee> ExistingEmployee = employeeRepository.findById(employee.getId());

            if (ExistingEmployee.isEmpty()) {
                response.setMessage("Employee with ID: " + employee.getId() + " does not exist");
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
            } else {
                Employee updatedEmployee = employeeRepository.save(employee);

                response.setMessage("Employee updated successfully");
                response.setStatusCode(HttpStatus.OK.value());
                response.setEntity(updatedEmployee);
            }
        } catch (Exception e) {
            response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }


    public EntityResponse deleteEmployee(Long id) {
        EntityResponse response = new EntityResponse();

        try {
            Optional<Employee> existingEmployee = employeeRepository.findById(id);

            if (existingEmployee.isEmpty()) {
                response.setMessage("The employee does not exist");
                response.setStatusCode(HttpStatus.NOT_FOUND.value());
                return response;
            } else {
                employeeRepository.deleteById(id);
                response.setMessage("Employee deleted successfully");
                response.setStatusCode(HttpStatus.OK.value());
            }

        } catch (Exception e ) {
            response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return response;
    }

}
