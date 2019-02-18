package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("getAll")
    @ResponseBody
    public List<Admin> getAll(){
        return adminService.getAll();
    }
}
