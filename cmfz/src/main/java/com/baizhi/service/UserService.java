package com.baizhi.service;

import com.baizhi.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 3072 kB 服务类
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-04
 */
public interface UserService extends IService<User> {

    Map getUsersByPage(int page, int rows, String name);

    List<Map> getChinaByMap();

    Integer[] getCountByWeek();

    Map getEcharts();

    Map concurrentGetEcharts() throws InterruptedException;

    void addUser(User user);


}
