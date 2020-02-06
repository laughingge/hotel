package com.example.hotel.controller;



import com.example.hotel.entity.Floor;
import com.example.hotel.entity.Room;
import com.example.hotel.entity.Roomtype;
import com.example.hotel.service.FloorService;
import com.example.hotel.service.RoomService;
import com.example.hotel.service.RoomtypeService;
import com.example.hotel.utils.FileUtil;
import com.example.hotel.utils.Pages;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("Room")
@Api(value="房间的增删改查",tags={"房间增删改查"})
public class RoomController {
    @Autowired
    RoomService roomService;
    @Autowired
    RoomtypeService roomtypeService;
    @Autowired
    FloorService floorService;
    @ApiOperation(value = "返回全部房间信息页面",notes = "返回全部房间信息页面")
    @GetMapping("/list")
    public String getAll(Model model,@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "7") int pageSize){
        List<Room> roomList=roomService.getAll(pageNum,pageSize);
        model.addAttribute("room",roomList);
        Pages page=new Pages(((Page) roomList).getPageNum(),((Page) roomList).getPageSize(),((Page) roomList).getTotal(),((Page) roomList).getPages());
        System.out.println(page.getTotalNum());
        model.addAttribute("page",page);
        model.addAttribute("pageNum",pageNum);
        return "Room/list";
    }
    @ApiOperation(value = "返回房间添加页面",notes = "返回房间添加页面")
    @GetMapping("/add")
    public String addRoom(Model model){
        List<Roomtype> roomtypes=roomtypeService.getroomtypename();

        model.addAttribute("roomtypes",roomtypes);
        List<Floor> floors=floorService.getfloorname();

        model.addAttribute("floors",floors);
        return "Room/add";
    }
    @Transactional
    @ApiOperation(value = "房间添加",notes = "房间添加")
    @PostMapping("/addRoom")
    public String addRoomInfo(@RequestParam("picture") MultipartFile file, Room room,@RequestParam("roomtypename") String roomtypename,@RequestParam("floorname") String floorname) throws IOException {
       /* FileUtil fileUtil=new FileUtil();
        fileUtil.fileupload(file);
        room.setPic(file.getOriginalFilename());*/
        String fileName = UUID.randomUUID()+file.getOriginalFilename();
        File pfile = new File("D:/BaiduYunDownload/springmvc/SpringMVC-01/代码/hotel/src/main/resources/static/uploads/");
        if (!pfile.exists()) {
            pfile.mkdirs();
        }
        File file1 = new File(pfile,  fileName);
        file.transferTo(file1);
        room.setPic(fileName);
        Floor floor=floorService.getFloorByfloorname(floorname);
        Roomtype roomtype=roomtypeService.getRoomtypeByroomtypename(roomtypename);
        roomtype.setRoomnumber(roomtype.getRoomnumber()+1);
        roomtypeService.updateRoomtypeByID(roomtype);

        room.setFloorid(floor.getFloorid());
        room.setRoomtypeid(roomtype.getRoomtypeid());

        Integer  i =roomService.insertRoom(room);

        return "redirect:list";
    }
    @Transactional
    @ApiOperation(value = "房间删除",notes = "房间删除")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path",name = "id",value = "房间id",required = true,dataType = "Long"),
            @ApiImplicitParam(paramType = "path",name = "roomtypename",value = "房型名字",required = true,dataType = "String")})
    @GetMapping("/del/{id}/{roomtypename}")
    public String deleteRoomByID(@PathVariable("id") Integer roomid,@PathVariable("roomtypename") String roomtypename){
        Roomtype roomtype=roomtypeService.getRoomtypeByroomtypename(roomtypename);
        roomtype.setRoomnumber(roomtype.getRoomnumber()-1);
        roomtypeService.updateRoomtypeByID(roomtype);
        roomService.deleteRoomByID(roomid);
        return "redirect:../list";
    }
    @ApiOperation(value = "返回房间更新页面",notes = "根据id更新房间信息")
    @ApiImplicitParam(paramType = "path",name = "id",value = "房间id",required = true,dataType = "Long")
    @GetMapping("/update/{id}")
    public String updateRoomByid(@PathVariable("id") Integer roomid,Model model){
        List<Roomtype> roomtypes=roomtypeService.getroomtypename();

        model.addAttribute("roomtypes",roomtypes);
        List<Floor> floors=floorService.getfloorname();

        model.addAttribute("floors",floors);
        Room room= roomService.getRoomByroomid(roomid);

        model.addAttribute("Room",room);
        return "Room/update";
    }

    @ApiOperation(value = "房间更新",notes = "根据id更新房间")
    @PostMapping("/update")
    public String updateRoom(@RequestParam("new") MultipartFile file,@RequestParam("old")String old, Room room){
        //若图片更新，删除就图片，下载图片到uploads文件夹下并把路径保存到数据库
        if(file.getSize()!=0) {
       /*     String path = "D:\\BaiduYunDownload\\springmvc\\SpringMVC-01\\代码\\hotel\\src\\main\\resources\\static\\uploads\\" + old;
            File file1 = new File(path);
            file1.delete();*/
            FileUtil fileUtil = new FileUtil();

            String fileName = fileUtil.fileupload(file);
            room.setPic(fileName);
        }else{
            room.setPic(old);
        }


        Floor floor= floorService.getFloorByfloorname(room.getFloorname());
        room.setFloorid(floor.getFloorid());
        Roomtype roomtype= roomtypeService.getRoomtypeByroomtypename(room.getRoomtypename());
        room.setRoomtypeid(roomtype.getRoomtypeid());
        Room oldroom=roomService.getRoomByroomid(room.getRoomid());
        if(oldroom.getRoomtypeid()!=roomtype.getRoomtypeid()){
               Roomtype oldroomtype=roomtypeService.getRoomtypeByroomtypeid(oldroom.getRoomtypeid());
               oldroomtype.setRoomnumber(oldroomtype.getRoomnumber()-1);
               roomtypeService.updateRoomtypeByID(oldroomtype);
               roomtype.setRoomnumber(roomtype.getRoomnumber()+1);
            roomtypeService.updateRoomtypeByID(roomtype);

        }


        roomService.updateRoomByID(room);
        return "redirect:/Room/list";
    }
}
