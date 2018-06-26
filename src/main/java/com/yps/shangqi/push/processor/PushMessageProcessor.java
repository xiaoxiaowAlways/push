package com.yps.shangqi.push.processor;

import com.yps.shangqi.push.entities.PushMessage;
import com.yps.shangqi.push.repository.PushMessageRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.instrument.async.TraceableScheduledExecutorService;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author Wolf
 * @date 2018/6/23 17:44
 */
@Lazy(false)
@Service
public class PushMessageProcessor {
  private final static Logger logger = LoggerFactory.getLogger(PushMessageProcessor.class);
  private int duration = 11;

  @Resource(name = "taskScheduler")
  private TraceableScheduledExecutorService taskScheduler;

  @Resource(name = "taskExecutor")
  private ThreadPoolTaskExecutor taskExecutor;

  @Autowired
  private PushMessageRepository pushMessageRepository;
  @Autowired
  private MessagePushHandler pushHandler;

  @Value("${push:true}")
  private boolean push;

  @PostConstruct
  public void init() {
    if (!push) {
      return;
    }
    taskScheduler.scheduleAtFixedRate(new Runnable() {
      @Override
      public void run() {
        int res = pushMessageRepository.resetTimeOut();
        logger.info("PushMessageProcessor resetTimeOut size:{}", res);

        List<PushMessage> unPushList = pushMessageRepository.listUnPushMessage();
        logger.info("PushMessageProcessor listUnPushMessage size:{}", unPushList.size());
        for (PushMessage message : unPushList) {
          taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
              pushHandler.pushMessage(message);
            }
          });
        }

      }
    }, duration, duration, TimeUnit.SECONDS);
  }
}
