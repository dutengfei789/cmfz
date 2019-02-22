package com.baizhi.controller;


import com.baizhi.entity.Audio;
import com.baizhi.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
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
@RequestMapping("/audio")
public class AudioController {

    @Autowired
    private AudioService audioService;

    @RequestMapping("getAudiosByPage")
    @ResponseBody
    public Map getAudiosByPage(int page,int rows,String name) {

        return audioService.getAudiosByPage(page, rows, name);
    }

    @RequestMapping("addAudio")
    @ResponseBody
    public boolean addAudio(Audio audio, MultipartFile file) {

        try {
            String originalFilename = file.getOriginalFilename();
            float size =  file.getSize();
            DecimalFormat df = new DecimalFormat("0.0");
            int i=1;
            while (true) {
                size=  size/1000;
                if (size < 1000) break;
                i++;
            }
            String unit = "byte";
            switch (i) {
                case 1:
                    unit="kb";
                    break;
                case 2:
                    unit="M";
                    break;
                case 3:
                    unit="G";
                    break;

                case 4:
                    unit="T";
                    break;
                default:
                    unit = "未知";
                    break;
            }
            audio.setAudioSize(df.format(size)+unit);

            String uuid = UUID.randomUUID().toString();
            String newName = uuid + "_" + originalFilename;
            File copyFile = new File("D:\\服务器\\audio", newName);
            file.transferTo(copyFile);
            audio.setAudioUrl("/audio/" + newName);
            audioService.save(audio);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }

}

