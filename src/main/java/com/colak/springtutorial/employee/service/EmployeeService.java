package com.colak.springtutorial.employee.service;

import com.colak.springtutorial.employee.jpa.Employee;
import com.colak.springtutorial.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public void save(Employee emp) {
        employeeRepository.save(emp);
    }

    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        employeeRepository.deleteAll();
    }
}
