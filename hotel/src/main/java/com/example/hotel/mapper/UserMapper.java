package com.example.hotel.mapper;


import java.util.List;

import com.example.hotel.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from User")
    List<User> getAll();
//INSERT INTO `hotel`.`User` (`uname`, `password`, `realname`, `phone`, `idcard`, `address`, `state`, `message`) VALUES ('xiaosan', '123', 'xiaosan', '1212', '12', '2', '1', '12');
    @Insert("insert into User(uname, password, realname, phone, idcard, address, state, message) \n" +
            "values(#{uname},#{password},#{realname},#{phone},#{idcard},#{address},#{state},#{message})")
    Integer insertUser(User user);
    @Delete("delete from User where userid=#{userid}")
    Integer deleteUsertByID(Integer userid);
    @Select("SELECT * FROM  User where userid=#{userid}")
    User getUserByuserid(Integer userid);
    @Select("SELECT * FROM  User where uname=#{uname}")
    User getUserByuname(String uname);
    @Select("SELECT count(userid) FROM  User ")
    Integer getCount();

    @Update("update User set uname=#{uname},password=#{password},realname=#{realname},phone=#{phone},idcard=#{idcard},address=#{address},state=#{state},message=#{message} where userid=#{userid};")
    Integer updateUserByID(User user);

}