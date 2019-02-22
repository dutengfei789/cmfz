package com.baizhi.service;

import com.baizhi.entity.Guru;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * InnoDB free: 3072 kB 服务类
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-04
 */
public interface GuruService extends IService<Guru> {

    Map getGurusByPage(int page, int rows, String name);
}
