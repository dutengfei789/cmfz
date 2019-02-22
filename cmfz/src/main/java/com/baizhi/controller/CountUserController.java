package com.baizhi.controller;


import com.baizhi.entity.CountUser;
import com.baizhi.service.CountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * VIEW 前端控制器
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-08
 */
@Controller
@RequestMapping("/countUser")
public class CountUserController {

    @Autowired
    private CountUserService countUserService;

    @RequestMapping("getCountBySex")
    @ResponseBody
    public Map getCountBySex() {

        Map map = new HashMap();

        List<CountUser> list = countUserService.list();
        for (CountUser countUser : list) {
            if ("男".equals(countUser.getSex())) {
                map.put("nan", countUser.getCountSex());
            }else {
                map.put("nv", countUser.getCountSex());
            }

        }

        return map;
    }


}

