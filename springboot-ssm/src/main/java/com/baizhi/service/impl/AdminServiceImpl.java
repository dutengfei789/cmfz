package com.baizhi.service.impl;

import com.baizhi.dao.AdminMapper;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    private final static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin selectByUsername(String username) {
        Admin admin = null;
        if (username != null && "".equals(username)) {
            admin=adminMapper.selectByUsername(username);
        }
        return admin;
    }

    @Override
    public String login(String username, String password) {

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            logger.debug("帐号异常！");
            return "redirect:index.jsp";
        } catch (IncorrectCredentialsException e) {
            logger.debug("密码错误！");
            return "redirect:index.jsp";
        }
        return "redirect:emplist.jsp";
    }

    /**
     * 使用盐值加密登陆密码
     * @param username 用户名
     * @param password 未加密登陆密码
     */
    @Override
    public void register(String username, String password) {
        String salt = UUID.randomUUID().toString();
        String pwd = new Md5Hash(password, salt, 1024).toHex();

        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(pwd);
        admin.setSalt(salt);

        adminMapper.insert(admin);
    }

    @Override
    public Map findByPage(int page, int rows) {
        Map map = new HashMap(2);

        int start = (page - 1) * rows;
        int end = page * rows;

        List<Admin> admins=adminMapper.findByPage(start, end);
        int total = adminMapper.countAdmin();

        map.put("rows", admins);
        map.put("total", total);

        return map;
    }

    @Override
    public void delete(int id) {
        adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Admin select(int id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Admin admin) {
        adminMapper.updateByPrimaryKeySelective(admin);
    }
}
