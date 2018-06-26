package com.yps.shangqi.push.model;

import com.alibaba.fastjson.JSONObject;
import com.yps.shangqi.push.constants.ErrorCode;

import java.io.Serializable;

import lombok.Data;

@Data
public class PushResponse implements Serializable {
  private Boolean is_success;
  private String error_code;
  private String error_msg;//json
  private Object data;//json

  public static String createMessageByError(ErrorCode code) {
    return JSONObject.toJSONString(createResponseByError(code));
  }

  public static PushResponse createResponseByError(ErrorCode code) {
    PushResponse response = new PushResponse();
    response.is_success = false;
    response.error_code = code.getError_code();
    response.error_msg = code.getMsg();
    return response;
  }

  public static PushResponse createSuccessResponse() {
    PushResponse response = new PushResponse();
    response.is_success = true;
    return response;
  }

  public static PushResponse createSuccessResponse(Object object) {
    PushResponse response = new PushResponse();
    response.is_success = true;
    response.data = object;
    return response;
  }
}
