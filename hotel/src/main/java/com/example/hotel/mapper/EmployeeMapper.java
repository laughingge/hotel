package com.example.hotel.mapper;


import com.example.hotel.entity.Employee;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    @Select("SELECT * from employee")
    List<Employee> getAll();
    @Insert("insert into Employee(username, password, realname, phone, idcard, address, state, message,roles) \n" +
            "values(#{username},#{password},#{realname},#{phone},#{idcard},#{address},#{state},#{message},#{roles})")
    Integer insertEmployee(Employee employee);
    @Delete("delete from Employee where employeeid=#{employeeid}")
    Integer deleteEmployeeByID(Integer employeeid);
    @Select("")
    Employee getUserByemployeeid(Integer employeeid);
    @Select("SELECT * FROM employee where username=#{username} limit 1")
    Employee loadUserByUsername(String username);

    @Update("update Employee set ename=#{ename},password=#{password},realname=#{realname},phone=#{phone},idcard=#{idcard},address=#{address},state=#{state},message=#{message},roles=#{roles} where employeeid=#{employeeid};")
    Integer updateEmployeeByID(Employee employee);

}