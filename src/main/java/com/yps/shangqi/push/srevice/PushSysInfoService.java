package com.yps.shangqi.push.srevice;

import com.yps.shangqi.push.entities.PushSysInfo;
import com.yps.shangqi.push.repository.PushSysInfoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Wolf
 * @date 2018/6/23 18:24
 */
@Service
public class PushSysInfoService {
  @Autowired
  PushSysInfoRepository pushSysInfoRepository;

  /**
   * 保存系统信息
   */
  public void saveSysInfo(PushSysInfo info) {
    pushSysInfoRepository.saveSysInfo(info);
  }

  /**
   * 通过系统ID查询系统信息
   */
  public PushSysInfo findBySysId(String sysId) {
    return pushSysInfoRepository.findBySysId(sysId);
  }
}
