package com.example.hotel.service;

import com.example.hotel.entity.Employee;
import com.example.hotel.entity.User;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll(int pageNum,int pageSize);
    Integer insertEmployee(Employee employee);
    Integer deleteEmployeeByID(Integer employeeid);
    Employee getUserByemployeeid(Integer employeeid);
    Integer updateEmployeeByID(Employee employee);
    Employee loadUserByUsername(String username);
}
