package com.baizhi.controller;


import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * InnoDB free: 3072 kB 前端控制器
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-05
 */
@Controller
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("getAlbumsByPage")
    @ResponseBody
    public Map getAlbumsByPage(int page, int rows, String name) {
        return albumService.getAlbumsByPage(page, rows, name);
    }

    @RequestMapping("getTreeAlbums")
    @ResponseBody
    public List<Album> getTreeAlbums() {
        return albumService.getTreeAlbums();
    }

    @RequestMapping("addAlbum")
    @ResponseBody
    public boolean addAlbum(Album album, MultipartFile file) {
//        System.out.println("album = " + album);

        try {
            String uuid = UUID.randomUUID().toString();
            String newName = uuid + "_" + file.getOriginalFilename();
            File newFile = new File("D:\\服务器\\audioCollection", newName);
            file.transferTo(newFile);
            album.setAlbumDate(new Date());
            album.setAlbumImage("/audioCollection/" + newName);
            albumService.save(album);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("getAlbums")
    @ResponseBody
    public List<Album> getAlbums() {
        return albumService.getAlbums();
    }

}

