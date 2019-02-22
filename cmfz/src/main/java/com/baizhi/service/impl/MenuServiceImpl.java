package com.baizhi.service.impl;

import com.baizhi.entity.Menu;
import com.baizhi.dao.MenuDao;
import com.baizhi.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * InnoDB free: 3072 kB 服务实现类
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-03
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> findAllMenu() {
        return menuDao.findAllMenu();
    }
}
