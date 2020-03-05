package com.example.hotel.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.example.hotel.entity.Orders;
import com.example.hotel.entity.Room;
import com.example.hotel.entity.Roomtype;
import com.example.hotel.entity.User;
import com.example.hotel.service.OrdersService;
import com.example.hotel.service.RoomService;
import com.example.hotel.service.RoomtypeService;
import com.example.hotel.service.UserService;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

@Controller
@RequestMapping("Order")
@Api(value="入住的增删改查",tags={"入住增删改查"})
public class OrdersController {
    @Autowired
    OrdersService ordersService;
    @Autowired
    UserService userService;
    @Autowired
    RoomService roomService;
    @Autowired
    RoomtypeService roomtypeService;
    private static final Logger log = (Logger) LoggerFactory.getLogger(OrdersController.class);
    private String requestpath="/Order/list";
    @ApiOperation(value = "获取全部入住信息",notes = "获取全部入住信息")
    @GetMapping("/list")
    public String getAll(HttpServletRequest request, Model model, @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "6") int pageSize){
        log.info(request.getRequestURI()+request.getServletPath()+"--------------------------------------------------------");
        if(requestpath=="/Order/list"){
         log.info("获取缓存");
        List<Orders> ordersList=ordersService.getAll(pageNum,pageSize);
            model.addAttribute("pageNum",pageNum);
            model.addAttribute("order",ordersList);
            return "Orders/list";}
        else {
            log.info(requestpath);
            log.info("从数据库拿数据");
            requestpath="/Order/list";
            List<Orders> ordersList=ordersService.getAllOnlyByMysql(pageNum,pageSize);
            model.addAttribute("pageNum",pageNum);
            model.addAttribute("order",ordersList);
            return "Orders/list";}

      /*  for (Orders o:ordersList ) {
            SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           if(o.getCheckindate()!= null) o.setCheckindate(Timestamp.valueOf(simpleDate.format(o.getCheckindate())));
            if(o.getBookdate()!= null) o.setBookdate(Timestamp.valueOf(simpleDate.format(o.getBookdate())));
            if(o.getLeavedate()!= null)  o.setLeavedate(Timestamp.valueOf(simpleDate.format(o.getLeavedate())));
        }*/

    }
    @ApiOperation(value = "返回入住添加页面",notes = "返回入住添加页面")
    @ApiImplicitParam(paramType = "path",name = "id",value = "楼层id",required = true,dataType = "Long")
    @GetMapping("/add/{number}")
    public String addOrders(Model model, @PathVariable("number") String number){
      System.out.print(number);
        List<User> user=userService.getAll(1,5);
        model.addAttribute("user",user);

        model.addAttribute("number",number);

        return "Orders/add";
    }
    @Transactional
    @ApiOperation(value = "入住添加",notes = "入住添加")
    @PostMapping("/addOrder")
    public String addOrdersInfo(HttpServletRequest request,Orders orders,@RequestParam("num") String num1,@RequestParam("uname")String uname){
        //System.out.print(num1);
        Room room=roomService.getRoomBynumber(num1);
        room.setState("被预定");
        roomService.updatestateBynumber(room);
       // System.out.print(num1);
        //根据roomid获取roomtypeid
        Roomtype roomtype=roomtypeService.getRoomtypeByroomtypeid(room.getRoomtypeid());
        roomtype.setBooked(roomtype.getBooked()+1);
        roomtype.setRoomnumber(roomtype.getRoomnumber()-1);
        Integer update=roomtypeService.updateRoomtypeByID(roomtype);
        System.out.print(update);
        orders.setPrice(roomtype.getPrice());
        orders.setRoomid(room.getRoomid());
        User user=userService.getUserByuname(uname);
        orders.setUserid(user.getUserid());
        Date date=new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orders.setBookdate(Timestamp.valueOf(simpleDate.format(date)));


        Integer  i = ordersService.insertOrders(orders);
         requestpath=request.getServletPath();
        return "redirect:list";
    }
    @ApiOperation(value = "入住删除",notes = "根据id删除入住")
    @ApiImplicitParam(paramType = "path",name = "id",value = "入住id",required = true,dataType = "Long")
    @GetMapping("/del/{id}")
    public String deleteOrdersByID(HttpServletRequest request,@PathVariable("id") Integer orderid){
        int i=ordersService.deleteOrdersByID(orderid);
        requestpath=request.getServletPath();
        return "redirect:../list";
    }
    @ApiOperation(value = "返回入住更新页面",notes = "根据id更新入住")
    @ApiImplicitParam(paramType = "path",name = "id",value = "入住id",required = true,dataType = "Long")
    @GetMapping("/update/{id}")
    public String updateOrdersByid(@PathVariable("id") Integer orderid,Model model){
        Orders orders= ordersService.getOrdersByorderid(orderid);
        model.addAttribute("Order",orders);

        return "Orders/update";
    }
    @ApiOperation(value = "入住更新",notes = "根据id更新入住")
    @PostMapping("/update")
    public String updateOrders(HttpServletRequest request,Orders orders){
        ordersService.updateOrdersByID(orders);
        requestpath=request.getServletPath();
        return "redirect:/Order/list";
    }
    @Transactional
    @ApiOperation(value = "入住日期更新",notes = "根据id和房型更新入住日期")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path",name = "id",value = "入住id",required = true,dataType = "Long"),
            @ApiImplicitParam(paramType = "path",name = "roomtypename",value = "房型名字",required = true,dataType = "String")})
    @GetMapping("/updatecheck/{id}/{roomtypename}")
    public String updatecheckdateByID(HttpServletRequest request,@PathVariable("id") Integer orderid,@PathVariable("roomtypename") String roomtypename,Model model){
        Orders orders= ordersService.getOrdersByorderid(orderid);
        //根据房型名字减少预定数和增加入住房间数
        Roomtype roomtype=roomtypeService.getRoomtypeByroomtypename(roomtypename);
        roomtype.setBooked(roomtype.getBooked()-1);
        roomtype.setCheckedin(roomtype.getCheckedin()+1);
        Integer update=roomtypeService.updateRoomtypeByID(roomtype);
        System.out.print(update);
        Room room=roomService.getRoomByroomid(orders.getRoomid());
        room.setState("已入住");
        roomService.updateRoomByID(room);
        Date date=new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orders.setCheckindate((Timestamp.valueOf(simpleDate.format(date))));
       int i=ordersService.updatecheckdateByID(orders);
        requestpath=request.getServletPath();
        return "redirect:../../list";
    }
    @Transactional
    @ApiOperation(value = "离开日期更新",notes = "根据id和房型更新离开日期")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path",name = "id",value = "入住id",required = true,dataType = "Long"),
            @ApiImplicitParam(paramType = "path",name = "roomtypename",value = "房型名字",required = true,dataType = "String")})
    @GetMapping("/updateleave/{id}/{roomtypename}")
    public String updateleavedateByID(HttpServletRequest request,@PathVariable("id") Integer orderid,@PathVariable("roomtypename") String roomtypename,Model model){
        Orders orders= ordersService.getOrdersByorderid(orderid);
        System.out.print(roomtypename);
        Roomtype roomtype=roomtypeService.getRoomtypeByroomtypename(roomtypename);
        roomtype.setCheckedin(roomtype.getCheckedin()-1);
        roomtype.setRoomnumber(roomtype.getRoomnumber()+1);
        Integer update=roomtypeService.updateRoomtypeByID(roomtype);

        Room room=roomService.getRoomByroomid(orders.getRoomid());
        room.setState("空闲");
        roomService.updateRoomByID(room);
        Date date=new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        orders.setLeavedate(Timestamp.valueOf(simpleDate.format(date)));
        int i=ordersService.updateleavedateByID(orders);
        requestpath=request.getServletPath();
        return "redirect:../../list";
    }
    @ApiOperation(value = "入住付费更新",notes = "根据id和房型更新入住付费")
    @ApiImplicitParam(paramType = "path",name = "id",value = "入住id",required = true,dataType = "Long")
    @GetMapping("/updatestate/{id}")
    public String updatestateByID(HttpServletRequest request,@PathVariable("id") Integer orderid,Model model){
        Orders orders= ordersService.getOrdersByorderid(orderid);
        orders.setState("已付款");
        int i=ordersService.updatestateByID(orders);
        requestpath=request.getServletPath();
        return "redirect:../list";
    }

   /* @GetMapping(value = "/exportExcel")
    public void ExportBankCkeckInfo(HttpServletResponse response, HttpServletRequest request){
        //得到所有要导出的数据
        List<Orders> errList = ordersService.getAll();
         for (Orders o:errList){
             log.info(o.toString());
         }
        //定义导出的excel名字
        String excelName = "销售表表";

        //获取需要转出的excel表头的map字段
        LinkedHashMap<String, String> fieldMap = new LinkedHashMap<>();
        fieldMap.put("orderid","订单编号");
        fieldMap.put("roomid","房间编号");
        fieldMap.put("userid","用户编号");
        fieldMap.put("roomtypename","房型名字");
        fieldMap.put("number","房间号码");
        fieldMap.put("bookdate","预定日期");
        fieldMap.put("checkindate","入住日期");
        fieldMap.put("leavedate","离开日期");
        fieldMap.put("state","付款状态");
        fieldMap.put("message","备注");
        //导出用户相关信息
        new ExportExcelUtils().export(excelName,errList,fieldMap,response);
    }
*/
   @ApiOperation(value = "导出excel",notes = "导出excel")
   @GetMapping(value = "/exportExcel")
   public String ExportBankCkeckInfo(HttpServletResponse response) throws Exception {
       List<Orders> list = ordersService.getAll();
       for(Orders o:list){
           log.info("导出excel成功");
       }

       Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), Orders.class, list);
       FileOutputStream fos = new FileOutputStream("D:/upload/销售图.xls");
       workbook.write(fos);
       fos.close();
       return "redirect:../Order/list";
   }

}
