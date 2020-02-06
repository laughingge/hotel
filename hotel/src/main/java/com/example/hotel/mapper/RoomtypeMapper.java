package com.example.hotel.mapper;



import com.example.hotel.entity.Roomtype;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoomtypeMapper {
    @Select("select * from RoomType")
    List<Roomtype> getAll();
    @Select("select roomtypename from RoomType")
    List<Roomtype> getroomtypename();

    @Insert("insert into RoomType(roomtypename, price, capacity, bed, roomnumber, booked, checkedin, state,message) \n" +
            "values(#{roomtypename},#{price},#{capacity},#{bed},#{roomnumber},#{booked},#{checkedin},#{state},#{message})")
    Integer insertRoomtype(Roomtype roomtype);
    @Delete("delete from RoomType where roomtypeid=#{roomtypeid}")
    Integer deleteRoomtypeByID(Integer roomtypeid);
    @Select("SELECT * FROM  RoomType where roomtypeid=#{roomtypeid}")
    Roomtype getRoomtypeByroomtypeid(Integer roomtypeid);
    @Select("SELECT * FROM  RoomType where roomtypename=#{roomtypename}")
    Roomtype getRoomtypeByroomtypename(String roomtypename);

    @Update("update RoomType set roomtypename=#{roomtypename},price=#{price},capacity=#{capacity},bed=#{bed},roomnumber=#{roomnumber},booked=#{booked},checkedin=#{checkedin},state=#{state},message=#{message} where roomtypeid=#{roomtypeid};")
    Integer updateRoomtypeByID(Roomtype roomtype);
    @Select("SELECT count(roomtypeid) FROM hotel.roomtype;")
    Integer getCount();

}