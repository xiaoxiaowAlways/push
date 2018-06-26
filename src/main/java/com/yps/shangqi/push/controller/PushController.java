package com.yps.shangqi.push.controller;

import com.google.common.collect.Lists;

import com.alibaba.fastjson.JSONObject;
import com.yps.shangqi.push.constants.ErrorCode;
import com.yps.shangqi.push.entities.PushMessage;
import com.yps.shangqi.push.entities.PushSysInfo;
import com.yps.shangqi.push.model.PushResponse;
import com.yps.shangqi.push.model.message.MessageVo;
import com.yps.shangqi.push.srevice.PushMessageService;
import com.yps.shangqi.push.srevice.PushSysInfoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import cn.jiguang.common.utils.StringUtils;

/**
 * @author Wolf
 * @date 2018/6/23 18:13
 */
@RestController
@RequestMapping("/api/v1/push")
public class PushController {
  private final static Logger logger = LoggerFactory.getLogger(PushController.class);

  @Autowired
  PushMessageService pushMessageService;
  @Autowired
  PushSysInfoService pushSysInfoService;

  @RequestMapping("/addSys")
  public String addSys(
      @RequestParam("name") String name,
      @RequestParam("id") String id,
      @RequestParam("desc") String desc
  ) {
    try {
      logger.info("addSys start, id:{}, name:{}, desc:{}", id, name, desc);
      PushSysInfo info = new PushSysInfo();
      info.setSysName(name);
      info.setSysId(id);
      info.setSysDesc(desc);
      pushSysInfoService.saveSysInfo(info);
    } catch (Exception e) {
      logger.error("addSys error ", e);
      return PushResponse.createMessageByError(ErrorCode.SYS_REPEAT);
    }
    logger.info("addSys end");
    return JSONObject.toJSONString(PushResponse.createSuccessResponse());
  }

  @RequestMapping(value = "/addPush", method = RequestMethod.POST)
  public String addPush(
      @RequestParam("sysId") String sysId,
      @RequestParam("userId") String userId,
      @RequestParam("deviceType") Byte deviceType,
//      @RequestParam(value = "pushType", defaultValue = "1") Integer pushType,
      @RequestParam("deviceToken") String deviceToken,
      @RequestParam("title") String title,
      @RequestParam(value = "content", defaultValue = "") String content,
      @RequestParam(value = "extras", defaultValue = "") String extras //json
  ) {
    try {
      logger.info("addPush start, userId:{}, deviceToken:{}, title:{}, content:{}, extras:{}",
          userId, deviceToken, title, content, extras);

      if (pushSysInfoService.findBySysId(sysId) == null) {
        logger.warn("addPush warn,sysId:{} not exit ", sysId);
        return PushResponse.createMessageByError(ErrorCode.SYS_NOT_EXIT);
      }

      if (StringUtils.isNotEmpty(extras)) {
        try {
          JSONObject.parseObject(extras, Map.class);
        } catch (Exception e) {
          logger.warn("addPush extras error", e);
          return PushResponse.createMessageByError(ErrorCode.PUSH_PARAM_ILL);
        }
      }

      List<String>userIds = Lists.newArrayList(userId.split(";"));
      for (String id : userIds) {
        PushMessage message = new PushMessage();
        message.setSysId(sysId);
        message.setUserId(id);
        message.setDeviceType(deviceType);
        message.setDeviceToken(deviceToken);
        message.setTitle(title);
        message.setContent(content);
        message.setExtras(extras);
        pushMessageService.saveMessage(message);
      }

    } catch (Exception e) {
      logger.error("addPush error ", e);
      return PushResponse.createMessageByError(ErrorCode.SERVER_ERROR);
    }
    logger.info("addPush end");
    return JSONObject.toJSONString(PushResponse.createSuccessResponse());
  }

  @RequestMapping("/list")
  public String list(
      @RequestParam("userId") String userId
  ) {
    try {
      List<MessageVo> vos = pushMessageService.findByUserId(userId);
      return JSONObject.toJSONString(PushResponse.createSuccessResponse(vos));
    } catch (Exception e) {
      logger.error("list error ", e);
      return PushResponse.createMessageByError(ErrorCode.SERVER_ERROR);
    }
  }

  @RequestMapping("/detail")
  public String detail(
      @RequestParam("id") Integer id
  ) {
    try {
      MessageVo vo = pushMessageService.findById(id);
      return JSONObject.toJSONString(PushResponse.createSuccessResponse(vo));

    } catch (Exception e) {
      logger.error("detail error ", e);
      return PushResponse.createMessageByError(ErrorCode.SERVER_ERROR);
    }
  }


}
