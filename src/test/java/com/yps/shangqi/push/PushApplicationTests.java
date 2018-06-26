package com.yps.shangqi.push;

import com.yps.shangqi.push.entities.PushSysInfo;
import com.yps.shangqi.push.mapper.ext.PushMessageExtMapper;
import com.yps.shangqi.push.srevice.PushSysInfoService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@MapperScan("com.yps.shangqi.push.mapper")
@SpringBootTest()
public class PushApplicationTests {

  @Autowired
  PushSysInfoService pushSysInfoService;
  @Autowired
  PushMessageExtMapper pushMessageExtMapper;

  @Test
  public void findBySysIdTest() {
    PushSysInfo info = pushSysInfoService.findBySysId("ususua-sadsadw-sdads-sss");
    System.out.println();
  }

  @Test
  public void updateStatusTest() {
    int res = pushMessageExtMapper.updateStatus(1, 0, 1);
    System.out.println(res);
  }

}
