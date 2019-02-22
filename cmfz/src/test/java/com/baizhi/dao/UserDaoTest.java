package com.baizhi.dao;

import com.baizhi.CmfzApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfzApplication.class)
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void getUsersByPage() {

    }
}