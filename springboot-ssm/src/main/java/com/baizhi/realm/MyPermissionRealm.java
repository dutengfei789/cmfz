package com.baizhi.realm;

import com.baizhi.dao.AdminMapper;
import com.baizhi.entity.Admin;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MyPermissionRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyPermissionRealm.class);

    @Autowired
    private AdminMapper adminDao;



    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("开始授权");
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        ArrayList<String> roles= new ArrayList<>();
        ArrayList<String> permissions = new ArrayList<>();


        roles.add("admin");
        roles.add("select");

        info.addRoles(roles);
        info.addStringPermissions(permissions);
        logger.info("获取权限信息 封装在info中"+info);
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        logger.info("开始认证-调用自己的realm" + this.getName());

//        查询数据库中的数据，以后调用dao
        String username= (String) authenticationToken.getPrincipal();
        logger.info("获取用户输入的帐号信息-"+username);


        Admin admin = adminDao.selectByUsername(username);

        if (admin != null) {
            logger.info("从数据库中查询到的帐号信息-"+admin);
            //将从数据库中查询到的帐号信息封装到info中
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(admin.getUsername(), admin.getPassword(), ByteSource.Util.bytes(admin.getSalt()), this.getName());
            logger.info("获取到的info-"+info.toString());
            return info;
        }

        logger.info("认证查询完成");
        return null;
    }
}
