package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;

/**
 * <p>
 * InnoDB free: 3072 kB
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-05
 */
@TableName("cmfz_audio")
//@ExcelTarget("音频信息")
public class Audio implements Serializable {

    @ExcelIgnore
    private static final long serialVersionUID = 1L;

    @TableId(value = "audio_id", type = IdType.AUTO)
    @JsonProperty("id")
    @Excel(name = "音频编号" )
    private Integer audioId;

    @JsonProperty("name")
    @Excel(name = "音频名称")
    private String audioName;

    @JsonIgnore
    @ExcelIgnore
    private Integer albumId;

    @Excel(name = "音频地址")
    private String audioUrl;

    @Excel(name = "音频大小")
    private String audioSize;

    @Excel(name = "顺序")
    private Integer audioOrder;

    @TableField(exist = false)
    @ExcelIgnore
    private Album album;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @JsonGetter("id")
    public Integer getAudioId() {
        return audioId;
    }

    @JsonSetter("id")
    public void setAudioId(Integer audioId) {
        this.audioId = audioId;
    }

    @JsonGetter("name")
    public String getAudioName() {
        return audioName;
    }

    @JsonSetter("name")
    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getAudioSize() {
        return audioSize;
    }

    public void setAudioSize(String audioSize) {
        this.audioSize = audioSize;
    }

    public Integer getAudioOrder() {
        return audioOrder;
    }

    public void setAudioOrder(Integer audioOrder) {
        this.audioOrder = audioOrder;
    }

    @Override
    public String toString() {
        return "Audio{" +
        "audioId=" + audioId +
        ", audioName=" + audioName +
        ", albumId=" + albumId +
        ", audioUrl=" + audioUrl +
        ", audioSize=" + audioSize +
        ", audioOrder=" + audioOrder +
        "}";
    }
}
