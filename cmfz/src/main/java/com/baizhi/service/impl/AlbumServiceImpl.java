package com.baizhi.service.impl;

import com.baizhi.entity.Album;
import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Banner;
import com.baizhi.service.AlbumService;
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
 * @since 2019-01-05
 */
@Service
public class AlbumServiceImpl extends ServiceImpl<AlbumDao, Album> implements AlbumService {

    @Autowired
    private AlbumDao albumDao;

    @Override
    public Map getAlbumsByPage(int page, int rows, String name) {

        Map map = new HashMap();

        QueryWrapper<Album> qw = new QueryWrapper<>();
        if (name != null && !"".equals(name)) {
            qw.like("album_name", name);
        }
        Page<Album> bannerPage = new Page<>(page,rows);

        IPage<Album> bannerIPage = albumDao.selectPage(bannerPage, qw);

        List<Album> records = bannerIPage.getRecords();
        long total = bannerIPage.getTotal();
        map.put("rows", records);
        map.put("total", total);

        return map;

    }

    @Override
    public List<Album> getTreeAlbums() {

        return albumDao.getTreeAlbums();
    }

    @Override
    public List<Album> getAlbums() {
        return albumDao.selectList(null);
    }
}
