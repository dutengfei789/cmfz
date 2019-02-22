package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-08
 */
@TableName("view_count_user")
public class CountUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sex;

    private Long countSex;


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getCountSex() {
        return countSex;
    }

    public void setCountSex(Long countSex) {
        this.countSex = countSex;
    }

    @Override
    public String toString() {
        return "CountUser{" +
        "sex=" + sex +
        ", countSex=" + countSex +
        "}";
    }
}
