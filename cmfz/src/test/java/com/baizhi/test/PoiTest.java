package com.baizhi.test;

import com.baizhi.CmfzApplicationTests;
import com.baizhi.dao.GuruDao;
import com.baizhi.entity.Guru;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

public class PoiTest extends CmfzApplicationTests {

    @Autowired
    private GuruDao guruDao;

    @Test
    public void testPoi() throws Exception {
        //创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();

        //创建工作表
        HSSFSheet guruSheet = workbook.createSheet("guru");

        //创建一个行对象，从下标0开始
        HSSFRow row = guruSheet.createRow(0);

        //创建一下单元格对象，并给出列坐标
        HSSFCell cell = row.createCell(0);

        //设置单元格内容
        cell.setCellValue("编号");

        //保存到本地
        workbook.write(new FileOutputStream(new File("D:\\服务器\\guru.xls")));

    }

    @Test
    public void poiListTest() throws Exception {
        List<String> list= Arrays.asList("编号", "姓名", "法号", "图片路径", "帐号状态");

        //创建工作表
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet guruSheet =hssfWorkbook.createSheet("guru");

        HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();

        HSSFFont font = hssfWorkbook.createFont();
        font.setColor((short)11);
        font.setFontName("微软雅黑");
        //字体加数
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//        应用字体
        cellStyle.setFont(font);
//        设置左右剧中
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
//        设置上下居中
        cellStyle.setFillBackgroundColor(HSSFColor.ORANGE.index);
//        设置背景色
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);



//        创建第一行
        HSSFRow guruTitle = guruSheet.createRow(0);

        //创建第一行内的单元 格
        for (int i = 0; i < list.size(); i++) {
            HSSFCell cell = guruTitle.createCell(i);
            cell.setCellValue(list.get(i));

//            设置样式
            cell.setCellStyle(cellStyle);
        }

        //从数据库中查询数据
        List<Guru> guruList = guruDao.selectList(null);

        for (int i = 0; i < guruList.size(); i++) {
            //创建数据的行
            HSSFRow rowData = guruSheet.createRow(i + 1);

//            创建列
            rowData.createCell(0).setCellValue(guruList.get(i).getGuruId());
            rowData.createCell(1).setCellValue(guruList.get(i).getGuruName());
            rowData.createCell(2).setCellValue(guruList.get(i).getGuruNickname());
            rowData.createCell(3).setCellValue(guruList.get(i).getGuruImage());
            rowData.createCell(4).setCellValue(guruList.get(i).getGuruStatus());
        }

        hssfWorkbook.write(new FileOutputStream(new File("D:\\服务器\\guruTitle.xls")));
    }
}
