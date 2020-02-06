package com.example.hotel.service;

import com.example.hotel.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllOnlyByMysql(int pageNum, int pageSize);
    List<User> getAll(int pageNum, int pageSize);
    Integer insertUser(User user);
    Integer deleteUsertByID(Integer userid);
    User getUserByuserid(Integer userid);
    Integer updateUserByID(User user);
    Integer getCount();
    User getUserByuname(String uname);
}
