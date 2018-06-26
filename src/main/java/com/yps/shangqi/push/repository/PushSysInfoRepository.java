package com.yps.shangqi.push.repository;

import com.yps.shangqi.push.entities.PushSysInfo;
import com.yps.shangqi.push.mapper.PushSysInfoMapper;
import com.yps.shangqi.push.mapper.ext.PushSysInfoExtMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Wolf
 * @date 2018/6/23 22:54
 */
@Repository
public class PushSysInfoRepository {
  @Autowired
  PushSysInfoMapper pushSysInfoMapper;

  @Autowired
  PushSysInfoExtMapper pushSysInfoExtMapper;

  public void saveSysInfo(PushSysInfo info) {
    pushSysInfoMapper.insertSelective(info);
  }

  public PushSysInfo findBySysId(String sysId) {
    return pushSysInfoExtMapper.selectBySysId(sysId);
  }
}
