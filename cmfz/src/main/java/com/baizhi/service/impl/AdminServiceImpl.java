package com.baizhi.service.impl;

import com.baizhi.entity.Admin;
import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Guru;
import com.baizhi.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 3072 kB 服务实现类
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-06
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Override
    public Map getAdminsByPage(int page, int rows, String name) {
        Map map = new HashMap();

        QueryWrapper<Admin> qw = new QueryWrapper<>();
        if (name != null && !"".equals(name)) {
            qw.like("guru_name", name);
        }
        Page<Admin> bannerPage = new Page<>(page,rows);

        IPage<Admin> bannerIPage = adminDao.selectPage(bannerPage, qw);

        List<Admin> records = bannerIPage.getRecords();
        long total = bannerIPage.getTotal();
        map.put("rows", records);
        map.put("total", total);
        return map;
    }

    @Override
    public void multiDelete(Integer[] ids) {
        adminDao.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public boolean login(String username, String password) {
        QueryWrapper<Admin> qw = new QueryWrapper<>();
        if (username != null && password != null) {
            qw.eq("username", username).eq("password", password);
            Admin admin = adminDao.selectOne(qw);
            if (admin != null) {
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                requestAttributes.getRequest().getSession().setAttribute("admin", admin);
                return true;
            }
        }
        return false;
    }
}
