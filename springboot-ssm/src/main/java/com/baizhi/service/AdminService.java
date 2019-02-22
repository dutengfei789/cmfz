package com.baizhi.service;

import com.baizhi.entity.Admin;

import java.util.Map;

public interface AdminService {
    Admin selectByUsername(String username);

    String login(String username, String password);

    void register(String username, String password);

    Map findByPage(int page, int rows);

    void delete(int id);

    Admin select(int id);

    void update(Admin admin);
}
