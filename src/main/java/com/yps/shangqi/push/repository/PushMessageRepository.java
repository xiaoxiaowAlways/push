package com.yps.shangqi.push.repository;

import com.yps.shangqi.push.entities.PushMessage;
import com.yps.shangqi.push.mapper.PushMessageMapper;
import com.yps.shangqi.push.mapper.ext.PushMessageExtMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Wolf
 * @date 2018/6/23 23:31
 */
@Repository
public class PushMessageRepository {
  @Autowired
  PushMessageMapper pushMessageMapper;
  @Autowired
  PushMessageExtMapper pushMessageExtMapper;

  public void saveMessage(PushMessage message) {
    pushMessageMapper.insertSelective(message);
  }

  public PushMessage findById(int id) {
    return pushMessageMapper.selectByPrimaryKey(id);
  }

  public List<PushMessage> findByUserId(String userId) {
    return pushMessageExtMapper.findByUserId(userId);
  }

  /**
   * 将处理超时的状态重置
   */
  public int resetTimeOut() {
    return pushMessageExtMapper.resetTimeOut(new Date());
  }

  /**
   * 查询所有未推送的消息
   */
  public List<PushMessage> listUnPushMessage() {
    return pushMessageExtMapper.listUnPushMessage(new Date());
  }

  /**
   * 锁定消息
   */
  public boolean lock(PushMessage message) {
    return pushMessageExtMapper.lock(message.getId(), message.getCount()) > 0;
  }

  /**
   * 解锁消息
   */
  public boolean release(int id, Date nextStartTime) {
    return pushMessageExtMapper.release(nextStartTime, id, 0, 1) > 0;
  }

  /**
   * 失效消息
   */
  public void disable(int id) {
    pushMessageExtMapper.disable(id);
  }

  /**
   * 推送成功
   */
  public void pushSuccess(int id) {
    pushMessageExtMapper.updateStatus(id, 2, 1);
  }
}
