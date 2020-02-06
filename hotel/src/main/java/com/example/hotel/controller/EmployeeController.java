package com.example.hotel.controller;


import com.example.hotel.entity.Employee;
import com.example.hotel.service.EmployeeService;
import com.example.hotel.utils.Pages;
import com.github.pagehelper.Page;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(value="职员的增删改查",tags={"职员增删改查"})
@Controller
@RequestMapping("Emp")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @ApiOperation(value = "获取全部职员信息",notes = "获取全部职员信息")
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    @GetMapping("/list")
    public String getAll(Model model,@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "2") int pageSize){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();// 加密
        String encodedPassword = passwordEncoder.encode("1234567".trim());
        System.out.print(encodedPassword);
        List<Employee> empList=employeeService.getAll(pageNum,pageSize);
        model.addAttribute("emp",empList);
        Pages page=new Pages(((Page) empList).getPageNum(),((Page) empList).getPageSize(),((Page) empList).getTotal(),((Page) empList).getPages());
        System.out.println(page.getTotalNum());
        model.addAttribute("page",page);
        model.addAttribute("pageNum",pageNum);
        return "Emp/list";
    }
    @ApiOperation(value = "返回职员添加页面",notes = "返回职员添加页面")
    @GetMapping("/add")
    public String addEmp(){
            return "emp/add";
    }

    @ApiOperation(value = "职员添加",notes = "职员添加")
    @PostMapping("/addEmp")
    public String addUseInfo(Employee emp){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
// 加密
        String encodedPassword = passwordEncoder.encode(emp.getPassword().trim());
        emp.setPassword(encodedPassword);

        Integer  i = employeeService.insertEmployee(emp);

        return "redirect:list";
    }
    @ApiOperation(value = "删除职员",notes = "根据id删除职员")
    @ApiImplicitParam(paramType = "path",name = "id",value = "职员id",required = true,dataType = "Long")
    @GetMapping("/del/{id}")
    public String deleteEmpByID(@PathVariable("id") Integer userid){
        employeeService.deleteEmployeeByID(userid);
        return "redirect:../list";
    }
    @ApiOperation(value = "返回职员更新页面",notes = "根据id获取职员信息")
    @ApiImplicitParam(paramType = "path",name = "id",value = "职员id",required = true,dataType = "Long")
    @GetMapping("/update/{id}")
    public String updateEmpByid(@PathVariable("id") Integer Emp_id,Model model){
        Employee emp= employeeService.getUserByemployeeid(Emp_id);
        model.addAttribute("Employee",emp);
        return "Emp/update";
    }

    @ApiOperation(value = "职员更新",notes = "更新职员")

    @PostMapping("/update")
    public String updateEmp(Employee employee){
        employeeService.updateEmployeeByID(employee);
        return "redirect:/Emp/list";
    }
}
