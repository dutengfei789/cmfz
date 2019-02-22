package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("register")
    @ResponseBody
    public boolean register(HttpSession session,String number, String username, String password) {

        try {
            if (number != null && !"".equals(number)) {
                String kaptcha = (String) session.getAttribute("kaptcha");
                if (number.equals(kaptcha)) {
                    adminService.register(username, password);
                    adminService.login(username, password);
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("login")
    public String login(String username,String  password) {
        return adminService.login(username, password);
    }

    @RequestMapping("findByPage")
    @ResponseBody
    public Map findByPage(int page, int rows) {

        return adminService.findByPage(page, rows);
    }

    @RequestMapping("addAdmin")
    public String addAdmin(String username,String password) {
        adminService.register(username, password);
        return "redirect:emplist.jsp";
    }

    @RequestMapping("delete")
    public String delete(int id) {
        adminService.delete(id);
        return "redirect:emplist.jsp";
    }

    @RequestMapping("select")
    public String select(int id,Map map) {
        Admin admin=adminService.select(id);
        map.put("admin", admin);
        return "update";
    }

    @RequestMapping("update")
    public String update(Admin admin) {
        adminService.update(admin);
        return "redirect:emplist.jsp";
    }
}
