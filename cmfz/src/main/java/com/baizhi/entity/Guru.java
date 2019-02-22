package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baizhi.annotation.ExcelName;
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
 * @since 2019-01-04
 */
@TableName("cmfz_guru")
public class Guru implements Serializable {


    @Excel(name = "编号")
    @TableId(value = "guru_id", type = IdType.AUTO)
    private Integer guruId;


    @Excel(name = "姓名")
    private String guruName;


    @Excel(name = "图片",type = 2,width = 40,height = 20,imageType = 1,savePath = "src\\main\\webapp\\import")
    private String guruImage;

    @Excel(name = "法号")
    private String guruNickname;

    /**
     * 1冻结 冻结的同时需要下架相关的专辑和文章
     */
    @Excel(name = "状态",replace = {"正常_0","冻结_1"})
    private Integer guruStatus;

    public Guru() {
    }

    public Guru(Integer guruId, String guruName, String guruImage, String guruNickname, Integer guruStatus) {
        this.guruId = guruId;
        this.guruName = guruName;
        this.guruImage = guruImage;
        this.guruNickname = guruNickname;
        this.guruStatus = guruStatus;
    }

    public Integer getGuruId() {
        return guruId;
    }

    public void setGuruId(Integer guruId) {
        this.guruId = guruId;
    }

    public String getGuruName() {
        return guruName;
    }

    public void setGuruName(String guruName) {
        this.guruName = guruName;
    }

    public String getGuruImage() {
        return guruImage;
    }

    public void setGuruImage(String guruImage) {
        this.guruImage = guruImage;
    }

    public String getGuruNickname() {
        return guruNickname;
    }

    public void setGuruNickname(String guruNickname) {
        this.guruNickname = guruNickname;
    }

    public Integer getGuruStatus() {
        return guruStatus;
    }

    public void setGuruStatus(Integer guruStatus) {
        this.guruStatus = guruStatus;
    }

    @Override
    public String toString() {
        return "Guru{" +
        "guruId=" + guruId +
        ", guruName=" + guruName +
        ", guruImage=" + guruImage +
        ", guruNickname=" + guruNickname +
        ", guruStatus=" + guruStatus +
        "}";
    }
}
