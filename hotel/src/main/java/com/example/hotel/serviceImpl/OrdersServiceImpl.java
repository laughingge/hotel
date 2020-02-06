package com.example.hotel.serviceImpl;


import com.example.hotel.utils.LineChart;
import com.example.hotel.utils.PieChart;
import com.example.hotel.entity.Orders;
import com.example.hotel.mapper.OrdersMapper;
import com.example.hotel.service.OrdersService;

import com.example.hotel.utils.RedisUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;
    private static int ExpireTime = 60;   // redis中存储的过期时间60s
    private static final Logger log = (Logger) LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private RedisUtil redisUtil;
    @Override
    public List<Orders> getAllOnlyByMysql(int pageNum, int pageSize) {
        List<Orders> ordersList= ordersMapper.getAll();

        return ordersList;
    }

    @Override
    public List<Orders> getAll() {
        return ordersMapper.getAll();
    }

    @Override
    //0 5 6 11
    public List<Orders> getAll(int pageNum, int pageSize) {
        log.info("查询数据");
        if(!redisUtil.hasKey("order"))
        {
            List<Orders> ordersList= ordersMapper.getAll();
            for (Orders  u: ordersList) {
                //这里循环order 把每个对象存到 redis中list中
                redisUtil.addList("order",u,ExpireTime);
                log.info(String.valueOf(u));
            }


            log.info("存进缓存成功");

        }

        List<Orders> ordersList= (List) redisUtil.getListPage("order",(pageNum-1)*pageSize,pageSize*pageNum-1);
        log.info(String.valueOf(redisUtil.lGetListSize("order")));

        log.info("获取缓存成功");
        return ordersList;
    }



    @Override
    public Integer insertOrders(Orders orders) {

    int i= ordersMapper.insertOrders(orders);

        updateredis(i);
        return i ;
    }

    @Override
    public Integer deleteOrdersByID(Integer orderid) {

        //先删除数据库再删除缓存
        Integer i=ordersMapper.deleteOrdersByID(orderid);

        updateredis(i);
        return i ;
    }

    @Override
    public Orders getOrdersByorderid(Integer orderid) {
        return ordersMapper.getOrdersByorderid(orderid);
    }

    @Override
    public Integer updateOrdersByID(Orders orders) {
        Integer i=ordersMapper.updateOrdersByID(orders);
        updateredis(i);
        return i ;
    }

    @Override
    public Integer updatecheckdateByID(Orders orders) {
        log.info("更新数据");
        Integer i=ordersMapper.updatecheckdateByID(orders);
        updateredis(i);
        return i ;
    }

    @Override
    public Integer updateleavedateByID(Orders orders) {
        Integer i=ordersMapper.updateleavedateByID(orders);
       updateredis(i);

        return i ;
    }

    @Override
    public Integer getCount() {
        return ordersMapper.getCount();
    }

    @Override
    public Integer updatestateByID(Orders orders) {

        Integer i=ordersMapper.updatestateByID(orders);
        updateredis(i);

        return i ;
    }

    @Override
    public  List<LineChart>getChart() {
        return ordersMapper.getChart();
    }

    @Override
    public List<PieChart> getDayPrice() {
        return ordersMapper.getDayPrice();
    }

    @Override
    public List<Orders> getSome() {
        return ordersMapper.getSome();
    }

    public void updateredis(Integer i){
        if(i==1) if(redisUtil.hasKey("order")) {

            redisUtil.expire("order",1);
            log.info("删除缓存成功");
        }

    }

}





