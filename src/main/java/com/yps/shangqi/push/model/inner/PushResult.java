package com.yps.shangqi.push.model.inner;

import com.yps.shangqi.push.constants.JPushError;

import lombok.Data;

/**
 * @author Wolf
 * @date 2018/6/24 10:26
 */
@Data
public class PushResult {
  //是否成功
  private boolean success;
  //是否重试
  private boolean retry;

  public static PushResult createByJError(JPushError error) {
    PushResult result = new PushResult();
    result.success = false;
    result.retry = error.isTryFlag();
    return result;
  }

  public static PushResult createSuccess() {
    PushResult result = new PushResult();
    result.success = true;
    return result;
  }

  public static PushResult createByRetry(boolean retry) {
    PushResult result = new PushResult();
    result.success = false;
    result.retry = retry;
    return result;
  }
}
