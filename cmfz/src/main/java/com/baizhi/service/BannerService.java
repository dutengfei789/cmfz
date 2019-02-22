package com.baizhi.service;

import com.baizhi.entity.Banner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * InnoDB free: 3072 kB 服务类
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-03
 */
public interface BannerService extends IService<Banner> {

    Map getBannerByPage(int page, int rows, String name);

    void multiUpdate(Integer[] ids);
}
