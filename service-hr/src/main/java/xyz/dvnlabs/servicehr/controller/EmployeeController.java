package xyz.dvnlabs.servicehr.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import xyz.dvnlabs.corelibrary.exception.ResourceExistException;
import xyz.dvnlabs.servicehr.dto.EmployeeUser;
import xyz.dvnlabs.servicehr.entity.Employee;
import xyz.dvnlabs.servicehr.service.EmployeeService;

import java.util.List;

@RequestMapping("/employee")
@RestController
@Api(tags = "Employee Controller")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    @ApiOperation("Find by ID")
    Employee findById(
            @PathVariable String id
    ) {
        return employeeService.findById(id);
    }

    @GetMapping("/list")
    @ApiOperation("Find list employee")
    List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employee-user/{id}")
    @ApiOperation("Get employee user")
    EmployeeUser getEmployeeUser(
            @PathVariable String id
    ) {
        return employeeService.getEmployeeUser(id);
    }

    @PostMapping
    @ApiOperation("Insert Employee")
    Employee insert(
            @RequestBody Employee employee
    ) throws ResourceExistException {
        return employeeService.create(employee);
    }
}
