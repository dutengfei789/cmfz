package com.baizhi.dao;

import com.baizhi.entity.Album;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * InnoDB free: 3072 kB Mapper 接口
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-05
 */
public interface AlbumDao extends BaseMapper<Album> {

    List<Album> getTreeAlbums();
}
