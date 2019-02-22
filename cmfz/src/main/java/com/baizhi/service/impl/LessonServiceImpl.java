package com.baizhi.service.impl;

import com.baizhi.entity.Lesson;
import com.baizhi.dao.LessonDao;
import com.baizhi.service.LessonService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 3072 kB 服务实现类
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-05
 */
@Service
public class LessonServiceImpl extends ServiceImpl<LessonDao, Lesson> implements LessonService {

    @Autowired
    private LessonDao lessonDao;

    @Override
    public Map getLessonsByPage(int page, int rows, String name) {

        Map map = new HashMap();

        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        }else {
            name = null;
        }
        Page<Lesson> userPage = new Page<>(page, rows);
        List<Lesson> list=lessonDao.getLessonsByPage(userPage, name);

        Page<Lesson> result = userPage.setRecords(list);
        long total = result.getTotal();
        map.put("rows", list);
        map.put("total", total);

        return map;
    }
}
