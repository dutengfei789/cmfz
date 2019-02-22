package com.baizhi.service.impl;

import com.baizhi.entity.Banner;
import com.baizhi.dao.BannerDao;
import com.baizhi.service.BannerService;
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
 * @since 2019-01-03
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerDao, Banner> implements BannerService {

    @Autowired
    private BannerDao bannerDao;

    @Override
    public Map getBannerByPage(int page, int rows, String name) {
        Map map = new HashMap();

        QueryWrapper<Banner> qw = new QueryWrapper<>();
        if (name != null && !"".equals(name)) {
            qw.like("banner_ole_name", name);
        }
        Page<Banner> bannerPage = new Page<>(page,rows);

        IPage<Banner> bannerIPage = bannerDao.selectPage(bannerPage, qw);

        List<Banner> records = bannerIPage.getRecords();
        long total = bannerIPage.getTotal();
        map.put("rows", records);
        map.put("total", total);


        return map;

    }

    @Override
    public void multiUpdate(Integer[] ids) {
        bannerDao.multiUpdate(ids);
    }
}
