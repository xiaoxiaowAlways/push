package com.yps.shangqi.push.mapper;

import com.yps.shangqi.push.entities.PushMessage;

public interface PushMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PushMessage record);

    int insertSelective(PushMessage record);

    PushMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PushMessage record);

    int updateByPrimaryKey(PushMessage record);
}