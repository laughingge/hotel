package com.example.hotel.mapper;



import com.example.hotel.utils.LineChart;
import com.example.hotel.utils.PieChart;
import com.example.hotel.entity.Orders;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface OrdersMapper {

   //@Select("SELECT orders.*,roomtypename,number  FROM hotel.orders,room,roomtype where  orders.roomid=room.roomid and room.roomtypeid=roomtype.roomtypeid order by bookdate desc")
   @Select("SELECT o.*,(select roomtypename from roomtype where roomtypeid = room.roomtypeid)roomtypename,(select number from room where room.roomid = o.roomid)number  FROM orders o left join room on  room.roomid=o.roomid left join roomtype on roomtype.roomtypeid=room.roomtypeid order by bookdate desc")
   @Results(value = {
            @Result(id =true,property = "orderid",column = "orderid"),
            @Result(property = "roomid",column = "roomid"),
            @Result(property = "bookdate",column = "bookdate"),
            @Result(property = "checkindate",column = "checkindate"),
            @Result(property = "leavedate",column = "leavedate"),
            @Result(property = "state",column = "state"),
            @Result(property = "message",column = "message"),
            @Result(property = "roomtypename",column = "roomtypename"),
            @Result(property = "number",column = "number"),
            @Result(property = "userid",column = "userid"),
            @Result(property = "user" ,column = "userid",one = @One(select="com.example.hotel.mapper.UserMapper.getUserByuserid",fetchType = FetchType.LAZY))
                    })
    List<Orders> getAll();
    @Insert("insert into Orders(roomid, bookdate, state, message,userid,price) \n" +
            "values(#{roomid},#{bookdate},#{state},#{message},#{userid},#{price})")
    Integer insertOrders(Orders orders);
    @Delete("delete from Orders where orderid=#{orderid}")
    Integer deleteOrdersByID(Integer orderid);
    @Select("SELECT * FROM  Orders where orderid=#{orderid}")
    Orders getOrdersByorderid(Integer orderid);

    @Update("update Orders set roomtypename=#{roomtypename},bookdate=#{bookdate},checkindate=#{checkindate},leavedate=#{leavedate},state=#{state},message=#{message} where orderid=#{orderid};")
    Integer updateOrdersByID(Orders orders);
    @Update("update Orders set checkindate=#{checkindate} where orderid=#{orderid};")
    Integer updatecheckdateByID(Orders orders);
    @Update("update Orders set leavedate=#{leavedate} where orderid=#{orderid};")
    Integer updateleavedateByID(Orders orders);
    @Select("SELECT count(*) FROM hotel.orders where checkindate is null")
    Integer getCount();

    @Update("update Orders set state=#{state} where orderid=#{orderid};")
    Integer updatestateByID(Orders orders);
    @Select("SELECT COUNT(DISTINCT checkindate) value,\n" +
            "DATE_FORMAT(checkindate, '%Y-%m-%d') name \n" +
            "FROM orders \n" +
            "WHERE DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= DATE(checkindate) \n" +
            "GROUP BY DATE_FORMAT( checkindate, '%Y-%m-%d') order by name desc limit 7")
    List<LineChart> getChart();
    @Select("SELECT sum(price) value,DATE_FORMAT(checkindate, '%Y-%m-%d') name FROM orders WHERE DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= DATE(checkindate) GROUP BY DATE_FORMAT( checkindate, '%Y-%m-%d') order by name desc limit 7")
    List<PieChart> getDayPrice();
 @Results(value = {
         @Result(id =true,property = "orderid",column = "orderid"),
         @Result(property = "roomid",column = "roomid"),
         @Result(property = "bookdate",column = "bookdate"),
         @Result(property = "checkindate",column = "checkindate"),
         @Result(property = "leavedate",column = "leavedate"),
         @Result(property = "state",column = "state"),
         @Result(property = "message",column = "message"),
         @Result(property = "roomtypename",column = "roomtypename"),
         @Result(property = "number",column = "number"),
         @Result(property = "userid",column = "userid"),
         @Result(property = "user" ,column = "userid",one = @One(select="com.example.hotel.mapper.UserMapper.getUserByuserid",fetchType = FetchType.LAZY))
 })
    @Select(" SELECT o.*,(select roomtypename from roomtype where roomtypeid = room.roomtypeid)roomtypename,(select number from room where room.roomid = o.roomid)number  FROM (select * from orders where orders.leavedate is null) o left join room on  room.roomid=o.roomid  left join roomtype on roomtype.roomtypeid=room.roomtypeid limit 5 ")
    List<Orders> getSome();
}