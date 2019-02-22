package com.baizhi.controller;


import com.baizhi.annotation.ServiceLog;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 3072 kB 前端控制器
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-06
 */
@Controller
@RequestMapping("/admin")
//@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;


    @RequestMapping("login")
    @ResponseBody
//    @ServiceLog("登陆")
    public boolean login(String username, String password) {
        try {
            adminService.login(username, password);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @RequestMapping("/getAdminsByPage")
    @ResponseBody
    @ServiceLog("分页查询管理员帐号")
    public Map getAdminsByPage(int page, int rows, String name) {
        return adminService.getAdminsByPage(page, rows, name);
    }



    @RequestMapping("addAdmin")
    @ResponseBody
    @ServiceLog("注册管理员")
    public boolean addAdmin(Admin admin) {
        try {
            adminService.save(admin);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("multiDelete")
    @ResponseBody
    public boolean multiDelete(Integer[] ids) {
        try {
            adminService.multiDelete(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @RequestMapping("updateAdmin")
    @ResponseBody
    @ServiceLog("修改管理员")
    public boolean updateAdmin(Admin admin) {
        return adminService.updateById(admin);
    }

    @RequestMapping("invalid")
    public String invalid(HttpSession session) {
        session.removeAttribute("admin");
        return "redirect:/login.jsp";
    }
}

