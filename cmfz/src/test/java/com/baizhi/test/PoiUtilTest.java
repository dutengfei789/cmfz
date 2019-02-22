package com.baizhi.test;


import com.baizhi.CmfzApplicationTests;
import com.baizhi.util.PoiUtil;
import com.baizhi.annotation.ExcelName;
import com.baizhi.dao.GuruDao;
import com.baizhi.entity.Guru;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

public class PoiUtilTest extends CmfzApplicationTests {

    @Autowired
    private GuruDao guruDao;

    @Test
    public void createExcel() throws Exception {

        List<Guru> gurus = guruDao.selectList(null);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet guruSheet = workbook.createSheet("guru");

//        创建单元格的表头。
        HSSFRow firstRow = guruSheet.createRow(0);
//        获取对象
        Class<? extends Guru> guruClass = gurus.get(0).getClass();
        Field[] declaredFields = guruClass.getDeclaredFields();
//        获取所有的注解属性
        for (int i = 0; i < declaredFields.length; i++) {
//            拿到注解对象
            ExcelName annotation = declaredFields[i].getAnnotation(ExcelName.class);
//            如果注解为空时，跳过本次循环。不要第一个属性的注解
            if (annotation==null) continue;
//            拿到注解中的值
            String name = annotation.name();
//            给列赋值
            firstRow.createCell(i).setCellValue(name);
        }

//        给列赋值
        for (int i = 0; i < gurus.size(); i++) {
//            创建行
            HSSFRow row = guruSheet.createRow(i + 1);
//            获取当前对象的属性值，给单元格赋值
            for (int j = 0; j < declaredFields.length; j++) {
//                创建单元格
                HSSFCell cell = row.createCell(j);

                //拿到属性名
                String name = declaredFields[j].getName();
                if(name.equals("serialVersionUID") ) continue;
                String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
                //拿到get方法的返回值
                Object object = guruClass.getDeclaredMethod(methodName,null).invoke(gurus.get(i), null);

//                类型判断
                if (object instanceof Integer) {
                    cell.setCellValue((Integer)object);
                }else if(object instanceof Date){
                    cell.setCellValue((Date)object);
                }else {
                    cell.setCellValue((String) object);
                }

                workbook.write(new FileOutputStream(new File("d:\\服务器\\guruList.xls")));

            }
        }

    }

    @Test
    public void test2() throws Exception {
        List<Guru> gurus = guruDao.selectList(null);

    }

    @Test
    public void test3() throws Exception {
        PoiUtil.importData();

    }
}
