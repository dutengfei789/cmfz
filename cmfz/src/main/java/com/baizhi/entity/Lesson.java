package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * InnoDB free: 3072 kB
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-05
 */
@TableName("cmfz_lesson")
public class Lesson implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "lesson_id", type = IdType.AUTO)
    private Integer lessonId;

    private String lessonName;

    private Integer userId;

    private Integer lessonStatus;

    @TableField(exist = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getLessonStatus() {
        return lessonStatus;
    }

    public void setLessonStatus(Integer lessonStatus) {
        this.lessonStatus = lessonStatus;
    }

    @Override
    public String toString() {
        return "Lesson{" +
        "lessonId=" + lessonId +
        ", lessonName=" + lessonName +
        ", userId=" + userId +
        ", lessonStatus=" + lessonStatus +
        "}";
    }
}
