import com.baizhi.CmfzApplication;
import com.baizhi.dao.BannerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DecimalFormat;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CmfzApplication.class)
public class BannerDaoTest {

    @Autowired
    private BannerDao bannerDao;

    @Test
    public void multiUpdate() {
        Integer[] arry = {3, 2};
        bannerDao.multiUpdate(arry);
    }

    @Test
    public void mathTest() {
        DecimalFormat df = new DecimalFormat("0.00");
        double d=(float)1324/1000;
        String format = df.format(d);
        System.out.println("format = " + format);

//        System.out.println("d = " + d);
    }
}