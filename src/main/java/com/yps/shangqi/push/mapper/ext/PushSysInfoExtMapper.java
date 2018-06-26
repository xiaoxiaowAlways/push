package com.yps.shangqi.push.mapper.ext;

import com.yps.shangqi.push.entities.PushSysInfo;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface PushSysInfoExtMapper {

  @Select("select * from push_sys_info where sys_id = #{sysId}")
  PushSysInfo selectBySysId(@Param("sysId") String sysId);
}
