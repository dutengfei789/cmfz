package com.baizhi.dao;

import com.baizhi.entity.Banner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * InnoDB free: 3072 kB Mapper 接口
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-03
 */
public interface BannerDao extends BaseMapper<Banner> {

    void multiUpdate(Integer[] ids);
}
