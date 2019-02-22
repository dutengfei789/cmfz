package com.baizhi.dao;

import com.baizhi.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * InnoDB free: 3072 kB Mapper 接口
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-05
 */
public interface ArticleDao extends BaseMapper<Article> {
    List<Article> getArticleByPage(Page<Article> userPage, @Param("name") String name);
}
