package com.yps.shangqi.push.mapper.ext;

import com.yps.shangqi.push.entities.PushMessage;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface PushMessageExtMapper {
  @Select("select * from push_message where user_id = #{userId} and valid_flag = 1")
  List<PushMessage> findByUserId(@Param("userId") String userId);

  //待处理并且有效 一次轮循处理200条
  @Select("select * from push_message where push_status = 0 and valid_flag = 1 and start_time < #{date} limit 200")
  List<PushMessage> listUnPushMessage(@Param("date") Date date);

  // 将处理1分钟未有结果的  重置状态
  @Update("update push_message set push_status = 0 where date_add(start_time, interval 1 minute) < #{date} and push_status = 1")
  int resetTimeOut(@Param("date") Date date);

  @Update("update push_message set push_status = 1 where id = #{id} and push_status = 0 and count = #{count}")
  int lock(@Param("id") Integer id, @Param("count") Byte count);

  @Update("update push_message set push_status = #{status} ,count = count + 1 where id = #{id} and push_status = #{preStatus}")
  int updateStatus(@Param("id") Integer id, @Param("status") Integer status, @Param("preStatus") Integer preStatus);

  @Update("update push_message set push_status = #{status} ,count = count + 1, start_time = #{date} where id = #{id} and push_status = #{preStatus}")
  int release(@Param("date") Date date, @Param("id") Integer id, @Param("status") Integer status, @Param("preStatus") Integer preStatus);

  @Update("update push_message set valid_flag = 2 where id = #{id}")
  int disable(@Param("id") Integer id);

}
