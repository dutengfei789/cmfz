package com.baizhi;

import com.baizhi.service.AdminService;
import com.baizhi.springbootssm.SpringbootSsmApplicationTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;



public class AdminServiceImplTest extends SpringbootSsmApplicationTests {

    @Autowired
    private AdminService adminService;
    @Test
    public void login() {
        adminService.login("dutengfei", "123456");
    }

    @Test
    public void register() {

        adminService.register("suns", "123456");

    }
}