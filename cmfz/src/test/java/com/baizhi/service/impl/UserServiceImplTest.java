package com.baizhi.service.impl;

import com.baizhi.CmfzApplication;
import com.baizhi.dao.UserDao;
import com.baizhi.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfzApplication.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Test
    public void getUser(){
        System.out.println("userDao = " + userDao);
        userDao.getUsersByPage(new Page<>(1, 3), null);

    }

    @Test
    public void getUsersByPage() {
        userService.getUsersByPage(1, 3, null);
    }
}