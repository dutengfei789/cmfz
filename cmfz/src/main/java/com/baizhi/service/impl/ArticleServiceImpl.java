package com.baizhi.service.impl;

import com.baizhi.entity.Article;
import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.User;
import com.baizhi.service.ArticleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 3072 kB 服务实现类
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-05
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public Map getArticleByPage(int page, int rows, String name) {
        Map map = new HashMap();

        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        }else {
            name = null;
        }
        Page<Article> userPage = new Page<>(page, rows);
        List<Article> list=articleDao.getArticleByPage(userPage, name);

        Page<Article> result = userPage.setRecords(list);
        long total = result.getTotal();
        map.put("rows", list);
        map.put("total", total);

        return map;
    }
}
