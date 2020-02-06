package com.example.hotel.controller;

import com.example.hotel.entity.User;
import com.example.hotel.service.UserService;
import com.example.hotel.utils.Pages;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user")
@Api(value="用户的增删改查",tags={"用户增删改查"})
public class UserController {
    @Autowired
    UserService userService;
    private String requestpath="/user/list";
    @GetMapping("/list")
    @ApiOperation(value = "返回全部用户信息页面",notes = "返回全部用户信息页面")
    public String getAll(Model model,@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "7") int pageSize){
        List<User> userList=userService.getAll(pageNum,pageSize);

        model.addAttribute("usr",userList);
        model.addAttribute("pageNum",pageNum);
        return "User/list";
    }
    @ApiOperation(value = "返回用户增加页面",notes = "返回用户信增加页面")
    @GetMapping("/add")
    public String addUser(){
        return "User/add";
    }
    @ApiOperation(value = "用户增加",notes = "用户信增加")
    @PostMapping("/addUser")
    public String addUseInfo(User user){
        Integer  i = userService.insertUser(user);

        return "redirect:list";
    }
    @ApiOperation(value = "用户删除",notes = "根据id删除用户")
    @ApiImplicitParam(paramType = "path",name = "id",value = "用户id",required = true,dataType = "Long")
    @GetMapping("/del/{id}")
    public String deleteUserByID(@PathVariable("id") Integer userid){
        userService.deleteUsertByID(userid);
        return "redirect:../list";
    }
    @ApiOperation(value = "返回用户更新页面",notes = "根据id更新用户")
    @ApiImplicitParam(paramType = "path",name = "id",value = "用户id",required = true,dataType = "Long")
    @GetMapping("/update/{id}")
    public String updateUserByid(@PathVariable("id") Integer User_id,Model model){
        User user= userService.getUserByuserid(User_id);
        model.addAttribute("User",user);
        return "user/update";
    }

    @ApiOperation(value = "用户更新",notes = "根据id更新用户")
    @PostMapping("/update")
    public String updateUser(User user){
        userService.updateUserByID(user);
        return "redirect:/user/list";
    }
}
