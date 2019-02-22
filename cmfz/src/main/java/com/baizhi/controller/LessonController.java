package com.baizhi.controller;


import com.baizhi.entity.Lesson;
import com.baizhi.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 * InnoDB free: 3072 kB 前端控制器
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-05
 */
@Controller
@RequestMapping("/lesson")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @RequestMapping("getLessonsByPage")
    @ResponseBody
    public Map getLessonsByPage(int page, int rows, String name) {
        return lessonService.getLessonsByPage(page, rows, name);
    }

    @RequestMapping("addLession")
    @ResponseBody
    public boolean addLession(Lesson lesson, MultipartFile file) {

        return false;

    }
}

