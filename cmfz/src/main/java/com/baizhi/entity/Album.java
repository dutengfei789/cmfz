package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.baizhi.annotation.ExcelName;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * InnoDB free: 3072 kB
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-05
 */
@TableName("cmfz_album")
public class Album implements Serializable {

    @ExcelIgnore
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    @TableId(value = "album_id", type = IdType.AUTO)
    @Excel(name = "专辑编号")
    private Integer albumId;

    @Excel(name = "专辑名称")
    @JsonProperty("name")
    private String albumName;

    @Excel(name = "专辑作者")
    private String albumAuthor;

    @Excel(name = "播音员")
    private String albumTeller;

    @Excel(name = "集数")
    @TableField("album_Episodes")
    private Integer albumEpisodes;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间" ,exportFormat = "yyyy-MM-dd HH:mm:ss")
    private Date albumDate;

    @Excel(name = "简介")
    private String albumContent;

    @Excel(name = "图片地址")
    private String albumImage;

    @Excel(name = "星级")
    private Integer albumStar;

    @TableField(exist = false)
    @ExcelIgnore
    private Audio audio;

    @TableField(exist = false)
    @ExcelCollection(name = "音频信息")
    private List<Audio> children;

    public List<Audio> getChildren() {
        return children;
    }

    public void setChildren(List<Audio> children) {
        this.children = children;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    @JsonGetter("id")
    public Integer getAlbumId() {
        return albumId;
    }

    @JsonSetter("id")
    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    @JsonGetter(value = "name")
    public String getAlbumName() {
        return albumName;
    }

    @JsonSetter(value = "name")
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumAuthor() {
        return albumAuthor;
    }

    public void setAlbumAuthor(String albumAuthor) {
        this.albumAuthor = albumAuthor;
    }

    public String getAlbumTeller() {
        return albumTeller;
    }

    public void setAlbumTeller(String albumTeller) {
        this.albumTeller = albumTeller;
    }

    public Integer getAlbumEpisodes() {
        return albumEpisodes;
    }

    public void setAlbumEpisodes(Integer albumEpisodes) {
        this.albumEpisodes = albumEpisodes;
    }

    public Date getAlbumDate() {
        return albumDate;
    }

    public void setAlbumDate(Date albumDate) {
        this.albumDate = albumDate;
    }

    public String getAlbumContent() {
        return albumContent;
    }

    public void setAlbumContent(String albumContent) {
        this.albumContent = albumContent;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    public Integer getAlbumStar() {
        return albumStar;
    }

    public void setAlbumStar(Integer albumStar) {
        this.albumStar = albumStar;
    }

    @Override
    public String toString() {
        return "Album{" +
        "albumId=" + albumId +
        ", albumName=" + albumName +
        ", albumAuthor=" + albumAuthor +
        ", albumTeller=" + albumTeller +
        ", albumEpisodes=" + albumEpisodes +
        ", albumDate=" + albumDate +
        ", albumContent=" + albumContent +
        ", albumImage=" + albumImage +
        ", albumStar=" + albumStar +
        "}";
    }
}
