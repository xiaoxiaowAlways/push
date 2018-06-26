package com.yps.shangqi.push.processor;

import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSONObject;
import com.yps.shangqi.push.constants.JPushError;
import com.yps.shangqi.push.entities.PushMessage;
import com.yps.shangqi.push.model.inner.PushResult;
import com.yps.shangqi.push.repository.PushMessageRepository;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.utils.StringUtils;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * @author Wolf
 * @date 2018/6/24 0:18
 */
@Component
public class MessagePushHandler {
  private final static Logger logger = LoggerFactory.getLogger(MessagePushHandler.class);

  @Autowired
  private PushMessageRepository pushMessageRepository;
  @Value("${push.appKey}")
  String appKey;
  @Value("${push.masterSecret}")
  String masterSecret;
  private static Map<Integer, Integer> map = Maps.newHashMap();

  //1h通知8次
  static {
    map.put(0, 15);
    map.put(1, 15);
    map.put(2, 30);
    map.put(3, 60);
    map.put(4, 120);
    map.put(5, 120);
    map.put(6, 1800);
    map.put(7, 1800);
  }

  public void pushMessage(PushMessage message) {
    //lock
    if (!pushMessageRepository.lock(message)) {
      logger.warn("message lock fail,id:{}", message.getId());
      return;
    }

    PushResult result = executePush(message);
    //如果成功
    if (result.isSuccess()) {
      pushMessageRepository.pushSuccess(message.getId());
      return;
    }
    //如果需要重试
    if (result.isRetry() && map.get(message.getCount().intValue()) != null) {
      pushMessageRepository.release(message.getId(), new DateTime().plusSeconds(map.get(message.getCount().intValue())).toDate());
    } else {
      pushMessageRepository.disable(message.getId());
    }
  }

  public PushResult executePush(PushMessage message) {

    try {
      PushPayload payload = buildPushMessage(message.getDeviceType(), message.getDeviceToken(), message.getTitle(), message.getContent(), message.getExtras());
      JPushClient jpushClient = new JPushClient(masterSecret, appKey);
      cn.jpush.api.push.PushResult result = jpushClient.sendPush(payload);
      logger.info("executePush pushResult statusCode:{}", result.statusCode);
      if (result.isResultOK()) {
        //200 成功
        return PushResult.createSuccess();
      } else {
        //其他重试
        return PushResult.createByRetry(true);
      }
    } catch (APIConnectionException e) {
      logger.error("executePush APIConnectionException", e);
      //重试
      return PushResult.createByRetry(true);
    } catch (APIRequestException e) {
      logger.error("executePush APIRequestException code:{}, detail:{}", e.getErrorCode(), e.getErrorMessage());
      String code = "J" + e.getErrorCode();
      JPushError error = JPushError.valueOf(code);
      //根据返回码决定是否重试
      return PushResult.createByJError(error);
    } catch (Exception e) {
      logger.error("executePush Exception", e);
      //重试
      return PushResult.createByRetry(true);
    }
  }


  public PushPayload buildPushMessage(int deviceType, String deviceToken, String title, String content, String extrasStr) {
    Map<String, String> extras = Maps.newHashMap();

    if (StringUtils.isNotEmpty(extrasStr)) {
      JSONObject json = JSONObject.parseObject(extrasStr);
      for (Map.Entry<String, Object> entry : json.entrySet()) {
        extras.put(entry.getKey(), entry.getValue().toString());
      }
    }
    Platform platform = null;
    Notification notification = null;
    switch (deviceType) {
      case 1:
        platform = Platform.android();
        notification = Notification.newBuilder()
            .setAlert(content)
            .addPlatformNotification(
                AndroidNotification.newBuilder()
                    .setTitle(title)
                    .addExtras(extras).build()
            ).build();
        break;
      case 2:
        platform = Platform.ios();
        notification = Notification.newBuilder()
            .setAlert(title)
            .addPlatformNotification(
                IosNotification.newBuilder()
                    .setAlert(content)
                    .addExtras(extras)
                    .build()
            ).build();
        break;
    }
    if (platform == null) {
      throw new RuntimeException("platform" + deviceType + " 未知");
    }
    //推送所有或者推送注册人
    Audience audience = deviceToken == null ? Audience.all() : Audience.registrationId(deviceToken);
    return PushPayload.newBuilder()
        .setPlatform(platform)
        .setAudience(audience)
        .setNotification(notification)
        .build();

  }

}
