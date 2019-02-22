package com.baizhi.service.impl;

import com.baizhi.dao.CountUserDao;
import com.baizhi.entity.CountUser;
import com.baizhi.entity.User;
import com.baizhi.dao.UserDao;
import com.baizhi.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import io.goeasy.GoEasy;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * <p>
 * InnoDB free: 3072 kB 服务实现类
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    private final static Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);



    /**
     * IO密集型任务  （常出现于线程中：数据库数据交互、文件上传下载、网络数据传输等等 能够体现多核处理器的优势）
     * CPU密集型任务 (常出现于线程中：复杂算法 能体现CPU版本的优势）
     */
    private static int corePoolSize = Runtime.getRuntime().availableProcessors();
    /**
     * public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,
     * TimeUnit unit,BlockingQueue<Runnable> workQueue)
     * corePoolSize用于指定核心线程数量
     * maximumPoolSize指定最大线程数
     * keepAliveTime和TimeUnit指定线程空闲后的最大存活时间
     * workQueue则是线程池的缓冲队列,还未执行的线程会在队列中等待
     * 监控队列长度，确保队列有界
     * 不当的线程池大小会使得处理速度变慢，稳定性下降，并且导致内存泄露。如果配置的线程过少，则队列会持续变大，消耗过多内存。
     * 而过多的线程又会 由于频繁的上下文切换导致整个系统的速度变缓——殊途而同归。队列的长度至关重要，它必须得是有界的，这样如果线程池不堪重负了它可以暂时拒绝掉新的请求。
     * ExecutorService 默认的实现是一个无界的 LinkedBlockingQueue。
     */
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, corePoolSize + 1, 10l, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(1000));

    @Autowired
    private UserDao userDao;
    @Autowired
    private CountUserDao countUserDao;

    @Override
    public Map getUsersByPage(int page, int rows, String name) {
        Map map = new HashMap();

        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        } else {
            name = null;
        }
        Page<User> userPage = new Page<>(page, rows);
        List<User> list = userDao.getUsersByPage(userPage, name);

        Page<User> result = userPage.setRecords(list);
        long total = result.getTotal();
        map.put("rows", list);
        map.put("total", total);
        logger.info(map.toString());
        return map;
    }

    @Override
    public List<Map> getChinaByMap() {
        return userDao.getChinaByMap();
    }

    @Override
    public Integer[] getCountByWeek() {
        return userDao.getCountByWeek();
    }

    @Override
    public Map getEcharts() {

        HashMap map = new HashMap<>();

        Map userMap = new HashMap<>();
        List<CountUser> countUsers = countUserDao.selectList(null);
        for (CountUser countUser : countUsers) {
            if ("男".equals(countUser.getSex())) {
                userMap.put("nan", countUser.getCountSex());
            } else {
                userMap.put("nv", countUser.getCountSex());
            }
        }

        List<Map> chinaByMap = userDao.getChinaByMap();
        Integer[] countByWeek = userDao.getCountByWeek();
        map.put("userMap", userMap);
        map.put("chinaByMap", chinaByMap);
        map.put("countByWeek", countByWeek);
        return map;
    }

    @Override
    public Map concurrentGetEcharts() throws InterruptedException {

        final Map map = new HashMap();

//        创建线程计数器
        final CountDownLatch countDownLatch = new CountDownLatch(3);

//        查询性别
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<CountUser> countSex = countUserDao.selectList(null);
                    HashMap<Object, Object> userMap = new HashMap<>();
                    for (CountUser sex : countSex) {
                        if ("男".equals(sex.getSex())) {
                            userMap.put("nan", sex.getCountSex());
                        } else {
                            userMap.put("nv", sex.getCountSex());
                        }
                    }

                    map.put("userMap", userMap);
                    //单次任务结束，计数器减一
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("执行查询性别任务执行失败！");
                    countDownLatch.countDown();

                }
            }
        });

//        查询最近三周用户注册量
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer[] countByWeek = userDao.getCountByWeek();
                    map.put("countByWeek", countByWeek);
                    countDownLatch.countDown();
                } catch (Exception e) {
                    System.out.println("执行查询最近三周用户注册量趋势失败");
                    e.printStackTrace();
                    countDownLatch.countDown();
                }
            }
        });

//        查询注册用户地区分布
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Map> chinaByMap = userDao.getChinaByMap();
                    map.put("chinaByMap", chinaByMap);
                    countDownLatch.countDown();
                } catch (Exception e) {
                    System.out.println("执行查询用户省份任务失败!");
                    e.printStackTrace();
                    countDownLatch.countDown();
                }

            }
        });

        //等待所有线程任务结束
        countDownLatch.await();

        //创建线程计数器
        return map;
    }

    @Override
    public void addUser(User user) {
        try {
            userDao.insert(user);
            GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-41e53053a75548679f43286bb8b27635");
            Map map = concurrentGetEcharts();
            String json = new Gson().toJson(map);
            goEasy.publish("cmfzChannel", json);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
