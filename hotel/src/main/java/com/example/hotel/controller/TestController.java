package com.example.hotel.controller;


import com.example.hotel.entity.Orders;
import com.example.hotel.entity.Room;
import com.example.hotel.service.OrdersService;
import com.example.hotel.service.RoomService;
import com.example.hotel.service.RoomtypeService;
import com.example.hotel.service.UserService;
import com.example.hotel.utils.LineChart;
import com.example.hotel.utils.MD5Util;
import com.example.hotel.utils.PieChart;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@Api(value="其余",tags={"其余"})
public class TestController {
    @Autowired
    StringEncryptor encryptor;
   @Autowired
    OrdersService ordersService;
   @Autowired
    UserService userService;
    @Autowired
    RoomService roomService;
    @Autowired
    RoomtypeService roomtypeService;
    @ApiOperation(value = "返回职员登录页面",notes = "返回职员登录页面")
   @GetMapping("/login")
    public String login() {

        return "login";
    }
    @ApiOperation(value = "职员登录",notes = "职员登录")
    @PostMapping("/login")
    public String index1() {
        return "redirect:/";
    }
    @ApiOperation(value = "返回主页面",notes = "返回主页面")
    @GetMapping("/")
    public String index(Model model) {
       Integer ordercount=ordersService.getCount();
       model.addAttribute("ordercount",ordercount);
       Integer usercount= userService.getCount();
       model.addAttribute("usercount",usercount);
        java.text.DecimalFormat   df   =   new   java.text.DecimalFormat("#0%")   ;
        String baifenbi=df.format(roomService.getBaifenbi());
        model.addAttribute("baifenbi",baifenbi);
        Integer roomtypecount=roomtypeService.getCount();
        model.addAttribute("roomtypecount",roomtypecount);
        List<Orders> orders=ordersService.getSome();
        model.addAttribute("orders",orders);
        List<Room> rooms=roomService.getSome();
        model.addAttribute("rooms",rooms);
        return "index";
    }
    @ApiOperation(value = "返回营销图页面",notes = "返回营销图页面")
    @GetMapping("/chart")
    public String chart() {


        return "Chart/salechart";
    }

    @ApiOperation(value = "返回每日入住数信息",notes = "返回每日入住数信息")
    @PostMapping("/EcharsShow")
    @ResponseBody
    public List<LineChart> echartsShow(Model model) {
        List<LineChart> list=  ordersService.getChart();
        Collections.reverse(list);

        for (LineChart o:list ) {


            System.out.println(o.name);
        }
        return list;
    }
    @ApiOperation(value = "返回每日进账数信息",notes = "返回每日进账数信息")
    @PostMapping("/EcharsShow1")
    @ResponseBody
    public List<PieChart> echartsShow1(Model model) {
        List<PieChart> list=  ordersService.getDayPrice();

        Collections.reverse(list);
        for (PieChart o:list ) {


            System.out.println(o.name);
        }
        return list;
    }
    @ApiOperation(value = "加密数据库信息",notes = "返回数据库加密后信息")
    @ResponseBody
    @GetMapping("/jiami")
    public void jiami() {
        String url = encryptor.encrypt("jdbc:mysql://127.0.0.1:3306/hotel?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2b8&allowMultiQueries=true");

        String name = encryptor.encrypt("root");
        String password = encryptor.encrypt("1234");
        System.out.println(url+"----------------");
        System.out.println(name+"----------------");
        System.out.println(password+"----------------");

    }
    @ResponseBody
    @GetMapping("/encode")
    public void encode() {

        String password = "123456";
        password=MD5Util.encode((String)password);

        System.out.println(password+"----------------");

    }



}







