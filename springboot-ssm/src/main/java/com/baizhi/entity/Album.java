package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;

public class Album implements Serializable {
    private Integer albumId;

    private String albumName;

    private String albumAuthor;

    private String albumTeller;

    private Integer albumEpisodes;

    private Date albumDate;

    private String albumContent;

    private String albumImage;

    private Integer albumStar;

    private static final long serialVersionUID = 1L;

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName == null ? null : albumName.trim();
    }

    public String getAlbumAuthor() {
        return albumAuthor;
    }

    public void setAlbumAuthor(String albumAuthor) {
        this.albumAuthor = albumAuthor == null ? null : albumAuthor.trim();
    }

    public String getAlbumTeller() {
        return albumTeller;
    }

    public void setAlbumTeller(String albumTeller) {
        this.albumTeller = albumTeller == null ? null : albumTeller.trim();
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
        this.albumContent = albumContent == null ? null : albumContent.trim();
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage == null ? null : albumImage.trim();
    }

    public Integer getAlbumStar() {
        return albumStar;
    }

    public void setAlbumStar(Integer albumStar) {
        this.albumStar = albumStar;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Album other = (Album) that;
        return (this.getAlbumId() == null ? other.getAlbumId() == null : this.getAlbumId().equals(other.getAlbumId()))
            && (this.getAlbumName() == null ? other.getAlbumName() == null : this.getAlbumName().equals(other.getAlbumName()))
            && (this.getAlbumAuthor() == null ? other.getAlbumAuthor() == null : this.getAlbumAuthor().equals(other.getAlbumAuthor()))
            && (this.getAlbumTeller() == null ? other.getAlbumTeller() == null : this.getAlbumTeller().equals(other.getAlbumTeller()))
            && (this.getAlbumEpisodes() == null ? other.getAlbumEpisodes() == null : this.getAlbumEpisodes().equals(other.getAlbumEpisodes()))
            && (this.getAlbumDate() == null ? other.getAlbumDate() == null : this.getAlbumDate().equals(other.getAlbumDate()))
            && (this.getAlbumContent() == null ? other.getAlbumContent() == null : this.getAlbumContent().equals(other.getAlbumContent()))
            && (this.getAlbumImage() == null ? other.getAlbumImage() == null : this.getAlbumImage().equals(other.getAlbumImage()))
            && (this.getAlbumStar() == null ? other.getAlbumStar() == null : this.getAlbumStar().equals(other.getAlbumStar()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAlbumId() == null) ? 0 : getAlbumId().hashCode());
        result = prime * result + ((getAlbumName() == null) ? 0 : getAlbumName().hashCode());
        result = prime * result + ((getAlbumAuthor() == null) ? 0 : getAlbumAuthor().hashCode());
        result = prime * result + ((getAlbumTeller() == null) ? 0 : getAlbumTeller().hashCode());
        result = prime * result + ((getAlbumEpisodes() == null) ? 0 : getAlbumEpisodes().hashCode());
        result = prime * result + ((getAlbumDate() == null) ? 0 : getAlbumDate().hashCode());
        result = prime * result + ((getAlbumContent() == null) ? 0 : getAlbumContent().hashCode());
        result = prime * result + ((getAlbumImage() == null) ? 0 : getAlbumImage().hashCode());
        result = prime * result + ((getAlbumStar() == null) ? 0 : getAlbumStar().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", albumId=").append(albumId);
        sb.append(", albumName=").append(albumName);
        sb.append(", albumAuthor=").append(albumAuthor);
        sb.append(", albumTeller=").append(albumTeller);
        sb.append(", albumEpisodes=").append(albumEpisodes);
        sb.append(", albumDate=").append(albumDate);
        sb.append(", albumContent=").append(albumContent);
        sb.append(", albumImage=").append(albumImage);
        sb.append(", albumStar=").append(albumStar);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}