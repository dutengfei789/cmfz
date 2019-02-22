package com.baizhi.service.impl;

import com.baizhi.entity.Audio;
import com.baizhi.dao.AudioDao;
import com.baizhi.service.AudioService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * InnoDB free: 3072 kB 服务实现类
 * </p>
 *
 * @author 杜腾飞
 * @since 2019-01-05
 */
@Service
public class AudioServiceImpl extends ServiceImpl<AudioDao, Audio> implements AudioService {

    @Autowired
    private AudioDao audioDao;

    @Override
    public Map getAudiosByPage(int page, int rows, String name) {
        Map map = new HashMap();

        if (name != null && !"".equals(name)) {
            name = "%" + name + "%";
        }else {
            name = null;
        }
        Page<Audio> userPage = new Page<>(page, rows);
        List<Audio> list=audioDao.getAudiosByPage(userPage, name);

        Page<Audio> result = userPage.setRecords(list);
        long total = result.getTotal();
        map.put("rows", list);
        map.put("total", total);

        return map;
    }
}
