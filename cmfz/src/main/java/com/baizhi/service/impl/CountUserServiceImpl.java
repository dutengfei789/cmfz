package com.baizhi.service.impl;

import com.baizhi.entity.CountUser;
import com.baizhi.dao.CountUserDao;
import com.baizhi.service.CountUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-08
 */
@Service
public class CountUserServiceImpl extends ServiceImpl<CountUserDao, CountUser> implements CountUserService {

}
