package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 3072 kB 服务类
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-05
 */
public interface AlbumService extends IService<Album> {

    Map getAlbumsByPage(int page, int rows, String name);

    List<Album> getTreeAlbums();

    List<Album> getAlbums();
}
