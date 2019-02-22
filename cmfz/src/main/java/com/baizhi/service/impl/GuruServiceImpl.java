package com.baizhi.service.impl;

import com.baizhi.entity.Guru;
import com.baizhi.dao.GuruDao;
import com.baizhi.service.GuruService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
 * @since 2019-01-04
 */
@Service
public class GuruServiceImpl extends ServiceImpl<GuruDao, Guru> implements GuruService {

    @Autowired
    private GuruDao guruDao;


    @Override
    public Map getGurusByPage(int page, int rows, String name) {
        Map map = new HashMap();

        QueryWrapper<Guru> qw = new QueryWrapper<>();
        if (name != null && !"".equals(name)) {
            qw.like("guru_name", name);
        }
        Page<Guru> bannerPage = new Page<>(page,rows);

        IPage<Guru> bannerIPage = guruDao.selectPage(bannerPage, qw);

        List<Guru> records = bannerIPage.getRecords();
        long total = bannerIPage.getTotal();
        map.put("rows", records);
        map.put("total", total);

        return map;
    }
}
