package com.baizhi.dao;

import com.baizhi.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 3072 kB Mapper 接口
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-04
 */
public interface UserDao extends BaseMapper<User> {

    List<Map> getChinaByMap();

    List<User> getUsersByPage(Page<User> userPage, @Param("name") String name);

    Integer[] getCountByWeek();
}
