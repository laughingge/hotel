package com.example.hotel.serviceImpl;

import com.example.hotel.entity.Employee;
import com.example.hotel.entity.User;
import com.example.hotel.mapper.EmployeeMapper;
import com.example.hotel.mapper.UserMapper;
import com.example.hotel.service.EmployeeService;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public List<Employee> getAll(int pageNum,int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        return employeeMapper.getAll();
    }

    @Override
    public Integer insertEmployee(Employee employee) {
        return employeeMapper.insertEmployee(employee);
    }

    @Override
    public Integer deleteEmployeeByID(Integer employeeid) {
        return employeeMapper.deleteEmployeeByID(employeeid);
    }

    @Override
    public Employee getUserByemployeeid(Integer employeeid) {
        return employeeMapper.getUserByemployeeid(employeeid);
    }

    @Override
    public Integer updateEmployeeByID(Employee employee) {
        return employeeMapper.updateEmployeeByID(employee);
    }

    @Override
    public Employee loadUserByUsername(String username) {
        return employeeMapper.loadUserByUsername(username);
    }


}

