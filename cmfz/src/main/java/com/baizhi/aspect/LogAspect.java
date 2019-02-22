package com.baizhi.aspect;

import com.baizhi.annotation.ServiceLog;
import com.baizhi.dao.AdminLogDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.AdminLog;
import com.baizhi.util.IPKit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private AdminLogDao adminLogDao;

    //添加日志使用
    private final static Logger logger = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 声明切点
     */
    @Pointcut("@annotation(com.baizhi.annotation.ServiceLog)")
    public void logPointcut() {

    }

    /**
     * 设置增强
     */
    @Around("logPointcut()")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint) {

        AdminLog adminLog = new AdminLog();
        //获取注解对象
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        String value = signature.getMethod().getAnnotation(ServiceLog.class).value();
        logger.debug("日志中value="+value);
        //获得当前请求
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //使用工具类获取注解
        String ip = IPKit.getIpAddrByRequest(request);
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        logger.debug("切面 中的admin"+admin);

        try {
            Object proceed = proceedingJoinPoint.proceed();
            adminLog.setLogIp(ip);
            adminLog.setLogAction(value);
            adminLog.setLogDate(new Date());
            adminLog.setAdminUsername(admin.getUsername());
            adminLog.setAdminId(admin.getId());
            adminLog.setLogResult("成功");
            adminLogDao.insert(adminLog);
            logger.info("操作成功："+adminLog);
            return proceed;
        } catch (Throwable throwable) {

            logger.error("失败");
            return null;
        }

    }

}
