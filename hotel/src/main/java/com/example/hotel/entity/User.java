package com.example.hotel.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class User implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.userid
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    @Excel(name = "用户编号",width = 20)
    private Integer userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.uname
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */

    private String uname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.password
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */

    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.realname
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    @Excel(name = "客户名字",width = 20)
    private String realname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.phone
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    @Excel(name = "电话",width = 20)
    private String phone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.idcard
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    @Excel(name = "身份证",width = 20)
    private String idcard;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.address
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    @Excel(name = "地址",width = 20)
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.state
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */

    private String state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.message
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */

    private String message;
    public User() {
    }

    public User(Integer userid, String uname, String password, String realname, String phone, String idcard, String address, String state, String message) {
        this.userid = userid;
        this.uname = uname;
        this.password = password;
        this.realname = realname;
        this.phone = phone;
        this.idcard = idcard;
        this.address = address;
        this.state = state;
        this.message = message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.userid
     *
     * @return the value of user.userid
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.userid
     *
     * @param userid the value for user.userid
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.uname
     *
     * @return the value of user.uname
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public String getUname() {
        return uname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.uname
     *
     * @param uname the value for user.uname
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.password
     *
     * @return the value of user.password
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.password
     *
     * @param password the value for user.password
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.realname
     *
     * @return the value of user.realname
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public String getRealname() {
        return realname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.realname
     *
     * @param realname the value for user.realname
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.phone
     *
     * @return the value of user.phone
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.phone
     *
     * @param phone the value for user.phone
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.idcard
     *
     * @return the value of user.idcard
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public String getIdcard() {
        return idcard;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.idcard
     *
     * @param idcard the value for user.idcard
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.address
     *
     * @return the value of user.address
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.address
     *
     * @param address the value for user.address
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.state
     *
     * @return the value of user.state
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.state
     *
     * @param state the value for user.state
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.message
     *
     * @return the value of user.message
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.message
     *
     * @param message the value for user.message
     *
     * @mbggenerated Mon Jan 13 10:05:47 CST 2020
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", uname='" + uname + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", phone='" + phone + '\'' +
                ", idcard='" + idcard + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}