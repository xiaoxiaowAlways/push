package com.yps.shangqi.push;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.yps.shangqi.push.mapper")
@PropertySource({"classpath:/config/${env}/server.properties"})
public class PushApplication {

  public static void main(String[] args) {
    SpringApplication.run(PushApplication.class, args);
  }
}
