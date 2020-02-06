package com.example.hotel.controller;



import com.example.hotel.entity.Roomtype;
import com.example.hotel.service.RoomtypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("Roomtype")
@Api(value="房型的增删改查",tags={"房型增删改查"})
public class RoomtypeController {
    @Autowired
    RoomtypeService roomtypeService;
    @ApiOperation(value = "返回全部房型信息页面",notes = "返回全部房型信息页面")
    @GetMapping("/list")
    public String getAll(Model model){
        List<Roomtype> roomtypes=roomtypeService.getAll();
        model.addAttribute("roomtype",roomtypes);
        return "Roomtype/list";
    }
    @ApiOperation(value = "返回添加房型信息页面",notes = "返回添加房型信息页面")
    @GetMapping("/add")
    public String addRoomtype(){
        return "Roomtype/add";
    }
    @ApiOperation(value = "添加房型",notes = "添加房型")
    @PostMapping("/addRoomtype")
    public String addRoomtypeInfo(Roomtype roomtype){
        Integer  i = roomtypeService.insertRoomtype(roomtype);

        return "redirect:list";
    }
    @ApiOperation(value = "房型删除",notes = "房型删除")
    @ApiImplicitParam(paramType = "path",name = "id",value = "房型id",required = true,dataType = "Long")
    @GetMapping("/del/{id}")
    public String deleteRoomtypeByID(@PathVariable("id") Integer roomtypeid){
        roomtypeService.deleteRoomtypeByID(roomtypeid);
        return "redirect:../list";
    }
    @ApiOperation(value = "返回房型更新页面",notes = "根据id房型更新")
    @ApiImplicitParam(paramType = "path",name = "id",value = "房型id",required = true,dataType = "Long")
    @GetMapping("/update/{id}")
    public String updateRoomtypeByid(@PathVariable("id") Integer roomtypeid,Model model){
        Roomtype roomtype= roomtypeService.getRoomtypeByroomtypeid(roomtypeid);
        model.addAttribute("Roomtype",roomtype);
        return "Roomtype/update";
    }

    @ApiOperation(value = "房型更新",notes = "根据id房型更新")
    @PostMapping("/update")
    public String updateRoomtype(Roomtype roomtype){
        roomtypeService.updateRoomtypeByID(roomtype);
        return "redirect:/Roomtype/list";
    }
}
