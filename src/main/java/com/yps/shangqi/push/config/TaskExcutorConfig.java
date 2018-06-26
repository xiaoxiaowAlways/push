package com.yps.shangqi.push.config;

import org.springframework.cloud.sleuth.SpanNamer;
import org.springframework.cloud.sleuth.TraceKeys;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.instrument.async.TraceableScheduledExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class TaskExcutorConfig {

  @Bean("taskScheduler")
  public TraceableScheduledExecutorService taskScheduler(Tracer tracer, TraceKeys traceKeys, SpanNamer spanNamer) {
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
    return new TraceableScheduledExecutorService(executorService, tracer, traceKeys, spanNamer);
  }

  @Bean("taskExecutor")
  public ThreadPoolTaskExecutor sycnPrecreditTaskPool() {
    ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
    poolTaskExecutor.setQueueCapacity(10000);
    poolTaskExecutor.setCorePoolSize(5);
    poolTaskExecutor.setMaxPoolSize(10);
    poolTaskExecutor.setKeepAliveSeconds(5000);
    poolTaskExecutor.initialize();
    return poolTaskExecutor;
  }
}
