package com.baizhi.service;

import com.baizhi.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * InnoDB free: 3072 kB 服务类
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-03
 */
public interface MenuService extends IService<Menu> {

    List<Menu> findAllMenu();

}
