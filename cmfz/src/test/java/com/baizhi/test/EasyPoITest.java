package com.baizhi.test;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baizhi.CmfzApplicationTests;
import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.GuruDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Guru;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.PlaceUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class EasyPoITest extends CmfzApplicationTests {

    @Autowired
    private GuruDao guruDao;

    @Autowired
    private AlbumDao albumDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Test
    public void export() throws Exception{

        List<Guru> gurus = guruDao.selectList(null);

//        定义导出的相关参数
        ExportParams exportParams = new ExportParams("上师信息","guru", ExcelType.HSSF);

//        创建workbook对象
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Guru.class, gurus);

//        使用流的方式，写出
        workbook.write(new FileOutputStream(new File("easyPoi.xls")));

    }

    @Test
    public void importExcel() {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);
        List<Object> gurus = ExcelImportUtil.importExcel(new File("easyPoi.xls"), Guru.class, importParams);

        gurus.forEach(System.out::println);
    }


    @Test
    public void exportList() throws Exception{

        ExportParams exportParams = new ExportParams("信息专辑","album",ExcelType.HSSF);

        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Album.class, albumDao.getTreeAlbums());
        workbook.write(new FileOutputStream(new File("exportList.xls")));

    }

    @Test
    public void importList(){

        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(2);

        List<Album> objects = ExcelImportUtil.importExcel(new File("exportList.xls"), Album.class, importParams);

//        objects.forEach(System.out::println);
        for (Album album : objects) {
            System.out.println("------------start--------------------- " + album);
            album.getChildren().forEach(System.out::println);
            System.out.println("---------end--------------------------");
        }
    }

    @Test
    public void insertUser() {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(300);
                User user = PlaceUtil.getUser();
                userService.addUser(user);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Test
    public void testDebug(){
        userService.getUsersByPage(1,3,null);
    }


}
