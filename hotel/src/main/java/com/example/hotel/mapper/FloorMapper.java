package com.example.hotel.mapper;



import com.example.hotel.entity.Floor;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FloorMapper {
    @Select("select * from Floor")
    List<Floor> getAll();
    @Select("select floorname from Floor")
    List<Floor> getfloorname();
    @Insert("insert into Floor(floorname, message) \n" +
            "values(#{floorname},#{message})")
    Integer insertFloor(Floor floor);
    @Delete("delete from Floor where floorid=#{floorid}")
    Integer deleteFloorByID(Integer floorid);
    @Select("SELECT * FROM  Floor where floorid=#{floorid}")
    Floor getUserByfloorid(Integer floorid);
    @Select("SELECT * FROM  Floor where floorname=#{floorname}")
    Floor getFloorByfloorname(String floorname);
    @Select("SELECT floorname FROM  Floor where floorid=#{floorid}")
    Floor getfloornameByfloorid(Integer floorid);

    @Update("update Floor set floorname=#{floorname},message=#{message} where floorid=#{floorid};")
    Integer updateFloorByID(Floor floor);

}