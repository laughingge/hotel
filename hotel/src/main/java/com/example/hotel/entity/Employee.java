package com.example.hotel.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Employee  implements UserDetails {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.employeeid
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    private Integer employeeid;
    private  String username;


    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.password
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.realname
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    private String realname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.phone
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.idcard
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    private String idcard;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.address
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.state
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    private String state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column employee.message
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    private String message;
    private String roles;
    public Employee() {

    }
    public Employee(String username, String password, String realname, String phone, String idcard, String address, String state, String message, String roles) {
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.phone = phone;
        this.idcard = idcard;
        this.address = address;
        this.state = state;
        this.message = message;
        this.roles = roles;
    }

    public Employee(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }



    public Integer getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      String[] roleArray = roles.split(",");
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        for (String role : roleArray) {
            authorityList.add(new SimpleGrantedAuthority(role));
        }
        System.out.print(authorityList);
        return authorityList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeid=" + employeeid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", phone='" + phone + '\'' +
                ", idcard='" + idcard + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", message='" + message + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}