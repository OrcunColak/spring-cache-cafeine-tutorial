package com.colak.springcachecaffeineutorial.employee.repository;

import com.colak.springcachecaffeineutorial.employee.jpa.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
