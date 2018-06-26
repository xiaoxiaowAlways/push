package com.yps.shangqi.push.srevice;

import com.google.common.collect.Lists;

import com.yps.shangqi.push.entities.PushMessage;
import com.yps.shangqi.push.model.message.MessageVo;
import com.yps.shangqi.push.repository.PushMessageRepository;
import com.yps.shangqi.push.util.DTOUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Wolf
 * @date 2018/6/23 17:47
 */
@Service
public class PushMessageService {

  @Autowired
  PushMessageRepository pushMessageRepository;

  /**
   * 保存消息
   */
  public void saveMessage(PushMessage message) {
    pushMessageRepository.saveMessage(message);
  }

  /**
   * 通过Id查询消息详情
   */
  public MessageVo findById(int id) {
    return DTOUtils.covert2MessageVo(pushMessageRepository.findById(id));
  }

  /**
   * 查询用户的所有消息
   */
  public List<MessageVo> findByUserId(String userId) {
    List<PushMessage> messages = pushMessageRepository.findByUserId(userId);
    List<MessageVo> vos = Lists.newArrayList();
    for (PushMessage message : messages) {
      vos.add(DTOUtils.covert2MessageVo(message));
    }
    return vos;
  }

}
