package com.example.hotel.mapper;




import com.example.hotel.utils.PieChart;
import com.example.hotel.entity.Room;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoomMapper {
    //@Select("SELECT   room.*,roomtype.roomtypename,floorname FROM hotel.room,roomtype,floor where room.roomtypeid=roomtype.roomtypeid and room.floorid=floor.floorid order by state ")
    @Select(" SELECT   r.*,(select roomtypename from roomtype where roomtypeid = r.roomtypeid)roomtypename,(select floorname from floor where floorid = r.floorid)floorname FROM room r left join roomtype e on e.roomtypeid=r.roomtypeid left join floor f on f.floorid=r.floorid order by state")
    List<Room> getAll();
    @Insert("insert into Room(pic, number, roomtypeid, floorid, state, message) \n" +
            "values(#{pic},#{number},#{roomtypeid},#{floorid},#{state},#{message})")
    Integer insertRoom(Room room);
    @Delete("delete from Room where roomid=#{roomid}")
    Integer deleteRoomByID(Integer roomid);
    @Select("SELECT   room.*,roomtype.roomtypename,floorname FROM hotel.room,roomtype,floor where room.roomtypeid=roomtype.roomtypeid and room.floorid=floor.floorid and roomid=#{roomid}")
    Room getRoomByroomid(Integer roomid);

    @Update("update Room set pic=#{pic},number=#{number},roomtypeid=#{roomtypeid},floorid=#{floorid},state=#{state},message=#{message} where roomid=#{roomid};")
    Integer updateRoomByID(Room room);
    @Select("SELECT   * FROM hotel.room,roomtype;")
    List<Room> getnumber();
    @Select("SELECT * from room where number=#{number};")
    Room getRoomBynumber(String number);
    @Update("update Room set state=#{state}where number=#{number};")
    Integer updatestateBynumber(Room room);
    @Select("SELECT cast((select count(roomid) from room where state='空闲')/count(roomid) as decimal(18,2)) as baifenbi FROM hotel.room ;")
    Float getBaifenbi();
    @Select("SELECT state,count(roomid) count FROM hotel.room group by state;")
    List<PieChart> getstate();
    @Select(" SELECT   r.*,(select roomtypename from roomtype where roomtypeid = r.roomtypeid)roomtypename,(select floorname from floor where floorid = r.floorid)floorname FROM ( select * from room where state='空闲' ) r left join roomtype e on e.roomtypeid=r.roomtypeid left join floor f on f.floorid=r.floorid limit 5")
    List<Room> getSome();


}