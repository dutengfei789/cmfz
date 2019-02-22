package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * InnoDB free: 3072 kB
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-05
 */
@TableName("cmfz_article")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "article_id", type = IdType.AUTO)
    private Integer articleId;

    private String articleName;

    private String articleImage;

    private String articleContent;

    private Integer guruId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date articleDate;

    private Guru guru;

    public Guru getGuru() {
        return guru;
    }

    public void setGuru(Guru guru) {
        this.guru = guru;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Integer getGuruId() {
        return guruId;
    }

    public void setGuruId(Integer guruId) {
        this.guruId = guruId;
    }

    public Date getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(Date articleDate) {
        this.articleDate = articleDate;
    }

    @Override
    public String toString() {
        return "Article{" +
        "articleId=" + articleId +
        ", articleName=" + articleName +
        ", articleImage=" + articleImage +
        ", articleContent=" + articleContent +
        ", guruId=" + guruId +
        ", articleDate=" + articleDate +
        "}";
    }
}
