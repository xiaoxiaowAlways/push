package com.yps.shangqi.push.mapper;

import com.yps.shangqi.push.entities.PushSysInfo;

public interface PushSysInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PushSysInfo record);

    int insertSelective(PushSysInfo record);

    PushSysInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PushSysInfo record);

    int updateByPrimaryKey(PushSysInfo record);
}