package com.example.hotel.controller;


import com.example.hotel.entity.Floor;
import com.example.hotel.service.FloorService;
import com.example.hotel.utils.Pages;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("Floor")
@Api(value="楼层的增删改查",tags={"楼层增删改查"})
public class FloorController {

    private static final Logger log = (Logger) LoggerFactory.getLogger(FloorController.class);
    @Autowired
    FloorService floorService;
    @ApiOperation(value = "获取全部楼层信息",notes = "获取全部楼层信息")
    @GetMapping("/list")
    public String getAll(Model model,@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "2") int pageSize){
        log.info("集成成功");
        List<Floor> floorList=floorService.getAll(pageNum,pageSize);
        model.addAttribute("floor",floorList);
        // public Pages(Integer pageNum, Integer pageSize, Long totalNum, Integer totalPage)
        Pages page=new Pages(((Page) floorList).getPageNum(),((Page) floorList).getPageSize(),((Page) floorList).getTotal(),((Page) floorList).getPages());
        System.out.println(page.getTotalNum());
        model.addAttribute("page",page);
        model.addAttribute("pageNum",pageNum);
        return "Floor/list";
    }
    @ApiOperation(value = "返回楼层添加页面",notes = "返回楼层添加页面")
    @GetMapping("/add")
    public String addFloor(){
        return "Floor/add";
    }
    @ApiOperation(value = "增加楼层",notes = "根据id增加楼层")
    @PostMapping("/addFloor")
    public String addFloorInfo(Floor floor){
        Integer  i = floorService.insertFloor(floor);

        return "redirect:list";
    }
    @ApiOperation(value = "删除楼层",notes = "根据id删除楼层")
    @ApiImplicitParam(paramType = "path",name = "id",value = "楼层id",required = true,dataType = "Long")
    @GetMapping("/del/{id}")
    public String deleteFloorByID(@PathVariable("id") Integer floorid){
        floorService.deleteFloortByID(floorid);
        return "redirect:../list";
    }
    @ApiOperation(value = "返回更新楼层页面",notes = "根据id获取楼层信息")
    @ApiImplicitParam(paramType = "path",name = "id",value = "职员id",required = true,dataType = "Long")
    @GetMapping("/update/{id}")
    public String updateFloorByid(@PathVariable("id") Integer floorid,Model model){
        Floor floor= floorService.getUserByfloorid(floorid);
        model.addAttribute("Floor",floor);
        return "Floor/update";
    }

    @ApiOperation(value = "更新楼层",notes = "根据id更新楼层")
    @PostMapping("/update")
    public String updateFloor(Floor floor){
        floorService.updateFloorByID(floor);
        return "redirect:/Floor/list";
    }
}
