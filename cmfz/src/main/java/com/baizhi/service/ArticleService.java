package com.baizhi.service;

import com.baizhi.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * InnoDB free: 3072 kB 服务类
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-05
 */
public interface ArticleService extends IService<Article> {

    Map getArticleByPage(int page, int rows, String name);
}
