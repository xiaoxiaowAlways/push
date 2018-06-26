package com.yps.shangqi.push.util;

import com.yps.shangqi.push.entities.PushMessage;
import com.yps.shangqi.push.model.message.MessageVo;

/**
 * @author Wolf
 * @date 2018/6/24 11:54
 */
public class DTOUtils {
  public static MessageVo covert2MessageVo(PushMessage message) {
    MessageVo vo = new MessageVo();
    if (message == null) {
      return vo;
    }
    vo.setSysId(message.getSysId());
    vo.setTitle(message.getTitle());
    vo.setContent(message.getContent());
    vo.setExtras(message.getExtras());
    return vo;
  }
}