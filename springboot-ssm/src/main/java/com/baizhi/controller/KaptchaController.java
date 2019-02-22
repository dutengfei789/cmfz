package com.baizhi.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class KaptchaController {
    @Autowired
    private Producer producer;

    @RequestMapping("/getImage")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        //生成验证码
        String text = producer.createText();

        //放入session中
        session.setAttribute("kaptcha",text);
        //通过Google提供的kaptcha工具，生成图片。
        BufferedImage image = producer.createImage(text);
        //将图片响应给浏览器
        ImageIO.write(image, "jpg", response.getOutputStream());

    }
}
