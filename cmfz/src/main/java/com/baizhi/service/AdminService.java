package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * InnoDB free: 3072 kB 服务类
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-06
 */
public interface AdminService extends IService<Admin> {

    Map getAdminsByPage(int page, int rows, String name);

    void multiDelete(Integer[] ids);

    boolean login(String username, String password);
}
