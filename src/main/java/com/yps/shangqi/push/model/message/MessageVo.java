package com.yps.shangqi.push.model.message;

import lombok.Data;

/**
 * @author Wolf
 * @date 2018/6/24 11:55
 */
@Data
public class MessageVo {
  private String sysId;
  private String title;
  private String content;
  private String extras;
}
