package com.baizhi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * InnoDB free: 8192 kB
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-15
 */
@TableName("cmfz_counter")
public class Counter implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "counter_id", type = IdType.AUTO)
    private Integer counterId;

    private String counterName;

    private LocalDateTime counterDate;

    private String lessonId;

    private Integer userId;

    private Integer counterCount;

    private Integer counterStatus;


    public Integer getCounterId() {
        return counterId;
    }

    public void setCounterId(Integer counterId) {
        this.counterId = counterId;
    }

    public String getCounterName() {
        return counterName;
    }

    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    public LocalDateTime getCounterDate() {
        return counterDate;
    }

    public void setCounterDate(LocalDateTime counterDate) {
        this.counterDate = counterDate;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCounterCount() {
        return counterCount;
    }

    public void setCounterCount(Integer counterCount) {
        this.counterCount = counterCount;
    }

    public Integer getCounterStatus() {
        return counterStatus;
    }

    public void setCounterStatus(Integer counterStatus) {
        this.counterStatus = counterStatus;
    }

    @Override
    public String toString() {
        return "Counter{" +
        "counterId=" + counterId +
        ", counterName=" + counterName +
        ", counterDate=" + counterDate +
        ", lessonId=" + lessonId +
        ", userId=" + userId +
        ", counterCount=" + counterCount +
        ", counterStatus=" + counterStatus +
        "}";
    }
}
