package com.baizhi.util;

import com.baizhi.annotation.ExcelName;
import com.baizhi.entity.Guru;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public class PoiUtil {


    public static void exportExcel(List list, String sheetName, HttpServletResponse response) throws Exception {

        //创建一个工作表对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);

        //创建表头
        HSSFRow rowTitle = sheet.createRow(0);

//        使用反射获取类对象
        Class<?> aClass = list.get(0).getClass();
        //获取类对象中的属性
        Field[] declaredFields = aClass.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            ExcelName annotation = declaredFields[i].getAnnotation(ExcelName.class);
            //拿到标题名
            String name = annotation.name();
            //单元格设置值
            rowTitle.createCell(i).setCellValue(name);
        }

//        通过遍历集合对表格赋值
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);

            for (int j = 0; j < declaredFields.length; j++) {
//                获取属性名
                String name = declaredFields[j].getName();
//                拼接方法名
                String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
//                执行方法，并得到返回值
                Object object = aClass.getDeclaredMethod(methodName, null).invoke(list.get(i), null);
                //判断类型,将数据写入到单元格中
                if (object instanceof Date) {
                    row.createCell(j).setCellValue((Date) object);
                } else if (object instanceof Integer) {
                    row.createCell(j).setCellValue((Integer) object);
                } else {
                    row.createCell(j).setCellValue((String) object);
                }
            }
        }

        String fileName = sheetName + "List.xls";
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream;charset=UTF-8");
//        解决中文乱码问题
        fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        workbook.write(response.getOutputStream());
    }

    public static void importData () throws Exception{

//        1.读取文件
        FileInputStream fileInputStream = new FileInputStream(new File("d:\\服务器\\guruList.xls"));
//        读取上传的文件
//        InputStream inputStream = file.getInputStream();

//        2.处理流中的数据
        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);

//        3.从工作簿对象中取出工作表对象
        HSSFSheet sheet = workbook.getSheet("guru");
//        sheet.getLastRowNum()获取最后一行的下标
        int lastRowNum = sheet.getLastRowNum();
//        不取标题栏 从1开始
        for (int i = 1; i <= lastRowNum; i++) {
//            4.从工作表对象中取出行对象
            HSSFRow row = sheet.getRow(i);
//           5.从行对象中取出单元格对象 取出单元格中的值
            //        6.封装为上师对象
            Guru cmfzGuru = new Guru();
            cmfzGuru.setGuruId((int) row.getCell(0).getNumericCellValue());
            cmfzGuru.setGuruName(row.getCell(1).getStringCellValue());
            cmfzGuru.setGuruNickname(row.getCell(2).getStringCellValue());
            cmfzGuru.setGuruImage(row.getCell(3).getStringCellValue());
            cmfzGuru.setGuruStatus((int) row.getCell(4).getNumericCellValue());

//        7.插入数据库
            System.out.println("插入一条数据"+cmfzGuru);
        }

    }


}
