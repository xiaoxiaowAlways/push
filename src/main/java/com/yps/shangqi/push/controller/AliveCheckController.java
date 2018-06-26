package com.yps.shangqi.push.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wolf
 * @date 2018/6/23 18:13
 */
@RestController
@RequestMapping("/push")
public class AliveCheckController {
  @RequestMapping("/aliveCheck")
  public String check() {

    return "OK";
  }


}
