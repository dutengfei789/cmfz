package com.baizhi.config;

import com.baizhi.realm.MyPermissionRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfigAuthentication {

    //设置过滤器
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //anon设置匿名可以访问
        //authc认证后可访问，登陆后可以访问
        Map map = new HashMap();
        map.put("/index.jsp", "anon");
        map.put("/regist.jsp", "anon");
        map.put("/getImage", "anon");
        map.put("/register", "anon");
        map.put("/login", "anon");
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setSuccessUrl("/emplist.jsp");
        shiroFilterFactoryBean.setUnauthorizedUrl("/index.jsp");
        return shiroFilterFactoryBean;
    }

    /**
     * 2.创建realm
     * @return
     */
//    @Bean
//    public DataSourceRealm getDataSourceRealm() {
//        return new DataSourceRealm();
//    }
    @Bean
    public MyPermissionRealm getDataSourceRealm(HashedCredentialsMatcher hashedCredentialsMatcher){
        MyPermissionRealm dataSourceRealm = new MyPermissionRealm();
        dataSourceRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return dataSourceRealm;
    }

    /**
     * 3.创建安全管理器
     * @param dataSourceRealm
     * @return
     */
    @Bean
    public SecurityManager getSecurityManager(MyPermissionRealm dataSourceRealm) {

        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(dataSourceRealm);
        return defaultWebSecurityManager;
    }

    /**
     * 4.创建凭证匹配器
     * @return
     */
    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }


    /**
     * 开启shiro aop注解支持
     * 使用代理方式；所有需要开启代码支持；否则@RequiresRoles等注解无法生效
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
