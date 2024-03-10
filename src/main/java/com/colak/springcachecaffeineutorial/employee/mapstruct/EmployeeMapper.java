package com.colak.springcachecaffeineutorial.employee.mapstruct;


import com.colak.springcachecaffeineutorial.employee.dto.EmployeeDTO;
import com.colak.springcachecaffeineutorial.employee.jpa.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee dtoToEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO employeeToDto(Employee employee);
}
