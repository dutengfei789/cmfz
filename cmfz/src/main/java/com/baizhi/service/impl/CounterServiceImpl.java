package com.baizhi.service.impl;

import com.baizhi.entity.Counter;
import com.baizhi.dao.CounterDao;
import com.baizhi.service.CounterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * InnoDB free: 8192 kB 服务实现类
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-15
 */
@Service
public class CounterServiceImpl extends ServiceImpl<CounterDao, Counter> implements CounterService {

}
