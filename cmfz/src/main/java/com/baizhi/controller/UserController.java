package com.baizhi.controller;


import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 3072 kB 前端控制器
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-04
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("getUsersByPage")
    @ResponseBody
    public Map getUsersByPage(int page, int rows, String name) {


        return userService.getUsersByPage(page, rows, name);
    }

    @RequestMapping("getChinaByMap")
    @ResponseBody
    public List<Map> getChinaByMap() {

        return userService.getChinaByMap();
    }

    @RequestMapping("getCountByWeek")
    @ResponseBody
    public Integer[] getCountByWeek(){
        return userService.getCountByWeek();
    }

    @RequestMapping("getEcharts")
    @ResponseBody
    public Map getEcharts() {
        return userService.getEcharts();
    }

    @RequestMapping("concurrentGetEcharts")
    @ResponseBody
    public Map concurrentGetEcharts() throws InterruptedException {
        long start = System.currentTimeMillis();

        Map map = userService.concurrentGetEcharts();
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        return map;
    }
}

