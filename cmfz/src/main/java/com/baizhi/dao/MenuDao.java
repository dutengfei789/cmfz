package com.baizhi.dao;

import com.baizhi.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * InnoDB free: 3072 kB Mapper 接口
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-03
 */
public interface MenuDao extends BaseMapper<Menu> {
    List<Menu> findAllMenu();

}
