package cn.okcoming.areacode;

import cn.okcoming.areacode.config.DataConfiguration;
import cn.okcoming.areacode.dto.Area;
import cn.okcoming.areacode.service.AreaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataConfiguration.class})
public class AreaserviceTests {

    @Resource
    private AreaService service;

    @Test
    public void test(){
        List<Area> list = service.listAll();
        Area area1 = service.getByCode("110000");
        Area area2 = service.getByName("北京市");
        Area area3 = service.getByName("北京市1");
        System.out.println(list.size());

    }
}

