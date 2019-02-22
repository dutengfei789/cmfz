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
@TableName("cmfz_admin_log")
public class AdminLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "log_id", type = IdType.AUTO)
    private Integer logId;

    /**
     * 执行的方法
     */
    private String logAction;

    /**
     * 用户名
     */
    private String adminUsername;

    /**
     * 用户的id
     */
    private Integer adminId;

    /**
     * 访问日期

     */
    private LocalDateTime logDate;

    /**
     * 登陆ip
     */
    private String logIp;

    /**
     * 执行方法的结果
     */
    private String logResult;


    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getLogAction() {
        return logAction;
    }

    public void setLogAction(String logAction) {
        this.logAction = logAction;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public LocalDateTime getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDateTime logDate) {
        this.logDate = logDate;
    }

    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }

    public String getLogResult() {
        return logResult;
    }

    public void setLogResult(String logResult) {
        this.logResult = logResult;
    }

    @Override
    public String toString() {
        return "AdminLog{" +
        "logId=" + logId +
        ", logAction=" + logAction +
        ", adminUsername=" + adminUsername +
        ", adminId=" + adminId +
        ", logDate=" + logDate +
        ", logIp=" + logIp +
        ", logResult=" + logResult +
        "}";
    }
}
