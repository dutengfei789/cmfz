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
 * @since 2019-01-03
 */
@TableName("cmfz_banner")
public class Banner implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "banner_id", type = IdType.AUTO)
    private Integer bannerId;

    private String bannerImageUrl;

    /**
     * 原有名称
     */
    private String bannerOldName;

    private Integer bannerState;

    /**
     * 上传时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bannerDate;

    private String bannerDescription;


    public Banner() {
    }



    public Banner(Integer bannerId, String bannerImageUrl, String bannerOldName, Integer bannerState, Date bannerDate, String bannerDescription) {
        this.bannerId = bannerId;
        this.bannerImageUrl = bannerImageUrl;
        this.bannerOldName = bannerOldName;
        this.bannerState = bannerState;
        this.bannerDate = bannerDate;
        this.bannerDescription = bannerDescription;
    }

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerImageUrl() {
        return bannerImageUrl;
    }

    public void setBannerImageUrl(String bannerImageUrl) {
        this.bannerImageUrl = bannerImageUrl;
    }

    public String getBannerOldName() {
        return bannerOldName;
    }

    public void setBannerOldName(String bannerOldName) {
        this.bannerOldName = bannerOldName;
    }

    public Integer getBannerState() {
        return bannerState;
    }

    public void setBannerState(Integer bannerState) {
        this.bannerState = bannerState;
    }

    public Date getBannerDate() {
        return bannerDate;
    }

    public void setBannerDate(Date bannerDate) {
        this.bannerDate = bannerDate;
    }

    public String getBannerDescription() {
        return bannerDescription;
    }

    public void setBannerDescription(String bannerDescription) {
        this.bannerDescription = bannerDescription;
    }

    @Override
    public String toString() {
        return "Banner{" +
        "bannerId=" + bannerId +
        ", bannerImageUrl=" + bannerImageUrl +
        ", bannerOldName=" + bannerOldName +
        ", bannerState=" + bannerState +
        ", bannerDate=" + bannerDate +
        ", bannerDescription=" + bannerDescription +
        "}";
    }
}
