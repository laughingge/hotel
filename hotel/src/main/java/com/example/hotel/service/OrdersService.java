package com.example.hotel.service;



import com.example.hotel.utils.LineChart;
import com.example.hotel.utils.PieChart;
import com.example.hotel.entity.Orders;

import java.util.List;

public interface OrdersService {
    List<Orders> getAll();
    List<Orders> getAll(int pageNum, int pageSize);
    List<Orders> getAllOnlyByMysql(int pageNum, int pageSize);
    Integer insertOrders(Orders orders);
    Integer deleteOrdersByID(Integer orderid);
    Orders getOrdersByorderid(Integer orderid);
    Integer updateOrdersByID(Orders orders);
    Integer updatecheckdateByID(Orders orders);
    Integer updateleavedateByID(Orders orders);
    Integer getCount();
    Integer updatestateByID(Orders orders);
    List<LineChart>getChart();
    List<PieChart> getDayPrice();
    List<Orders> getSome();
}
