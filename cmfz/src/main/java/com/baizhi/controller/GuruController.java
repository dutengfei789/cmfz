package com.baizhi.controller;


import com.baizhi.util.PoiUtil;
import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 3072 kB 前端控制器
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-04
 */
@Controller
@RequestMapping("/guru")
public class GuruController {

    @Autowired
    private GuruService guruService;

    @RequestMapping("getGurusByPage")
    @ResponseBody
    public Map getGurusByPage(int page, int rows, String name) {
        return guruService.getGurusByPage(page, rows, name);
    }

    /**
     * 通过excel导入数据
     */

    @RequestMapping("importData")
    @ResponseBody
    public boolean importData(MultipartFile file) {

//        System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename());
        try {
            ArrayList<Guru> list= new ArrayList<>();
            InputStream inputStream = file.getInputStream();
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            //获取要导入的工作薄
            HSSFSheet guruSheet = workbook.getSheet("guru");
//        从工作薄中取值。注意不是从0开始
            for (int i = 1; i < guruSheet.getLastRowNum(); i++) {
                // 获取行
                HSSFRow row = guruSheet.getRow(i);
                //根据行获取单元格
                Guru guru = new Guru();
                guru.setGuruId((int) row.getCell(0).getNumericCellValue());
                guru.setGuruName(row.getCell(1).getStringCellValue());
                guru.setGuruImage(row.getCell(2).getStringCellValue());
                guru.setGuruNickname(row.getCell(3).getStringCellValue());
                guru.setGuruStatus((int) row.getCell(4).getNumericCellValue());
                list.add(guru);
            }
            guruService.saveBatch(list);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletResponse response) {
        List<Guru> list = guruService.list();
        try {
            PoiUtil.exportExcel(list,"guru",response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

