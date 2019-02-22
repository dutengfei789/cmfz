package com.baizhi.controller;


import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * InnoDB free: 3072 kB 前端控制器
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-03
 */
@Controller
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping("/getBannerByPage")
    @ResponseBody
    public Map getBannerByPage(int page, int rows, String name) {
        return bannerService.getBannerByPage(page, rows, name);
    }

    @RequestMapping("addBanner")
    @ResponseBody
    public boolean addBanner(Banner banner, MultipartFile fileName) throws IOException {

        String originalFilename = fileName.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String newName = uuid + "_" + originalFilename;
        File file = new File("D:\\服务器", newName);
        fileName.transferTo(file);
        try {
            banner.setBannerImageUrl("/shouye/"+newName);
            banner.setBannerDate(new Date());
//            banner.setBannerOldName(originalFilename);
            banner.setBannerState(1);
            bannerService.save(banner);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }

    @RequestMapping("multiDelete")
    @ResponseBody
    public boolean multiDelete(Integer[] ids) {
        try {
            bannerService.multiUpdate(ids);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("updateStatus")
    @ResponseBody
    public boolean updateStatus(Banner banner) {
        try {
            bannerService.updateById(banner);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

