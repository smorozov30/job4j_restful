package ru.job4j.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.auth.domain.Employee;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static final String PERSON_API = "http://localhost:8080/person/";
    private static final String PERSON_API_ID = "http://localhost:8080/person/{id}";

    private final EmployeeRepository employees;

    @Autowired
    private RestTemplate rest;

    public EmployeeController(EmployeeRepository employees) {
        this.employees = employees;
    }

    @GetMapping("/")
    public List<Employee> findAll() {
        return StreamSupport.stream(
                employees.findAll().spliterator(), false
        ).collect(Collectors.toList());
    }

    @PostMapping("/{employeeId}/")
    public ResponseEntity<Employee> create(@PathVariable int employeeId,
                                       @RequestBody Person person) {
        Employee employee = employees.findById(employeeId).orElse(new Employee());
        if (employee.getId() != 0) {
            Person rsl = rest.postForObject(PERSON_API, person, Person.class);
            employee.addAccount(rsl);
            return new ResponseEntity<>(
                    employees.save(employee),
                    HttpStatus.CREATED);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{employeeId}/")
    public ResponseEntity<Void> update(@PathVariable int employeeId,
                                       @RequestBody Person person) {
        Employee employee = employees.findById(employeeId).orElse(new Employee());
        if (employee.getId() != 0) {
            rest.put(PERSON_API, person);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{employeeId}/{id}")
    public ResponseEntity<Void> delete(@PathVariable int employeeId,
                                       @PathVariable int id) {
        Employee employee = employees.findById(employeeId).orElse(new Employee());
        if (employee.getId() != 0) {
            employee.deleteAccountById(id);
            employees.save(employee);
            rest.delete(PERSON_API_ID, id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
