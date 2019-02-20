package com.baizhi.springbootssm;

import com.baizhi.springbootssm.com.baizhi.entity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootSsmApplicationTests {

    @Test
    public void contextLoads() {
        Admin admin = new Admin();
        admin.getId();
    }

}
