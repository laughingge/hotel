package com.example.hotel.config;

import com.example.hotel.entity.Employee;
import com.example.hotel.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // TODO 根据用户名，查找到对应的密码，与权限
        Employee employee = employeeMapper.loadUserByUsername(username);
        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限

        //123456   "$2a$10$rE5.RvkHaB06t.9GjGeaW.jNHysRQpBXObl3ZSahzBesfq7tAkX56"


        return employee;
    }
}
