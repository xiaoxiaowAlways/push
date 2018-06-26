package com.yps.shangqi.push.constants;

public enum ErrorCode {
  SERVER_ERROR("999", "服务异常"),
  SYS_REPEAT("S001", "系统名称或ID重复"),
  SYS_NOT_EXIT("S001", "系统ID不存在"),
  ADD_PUSH_MESSAGE_FAIL("P001", "保存推送消息失败"),
  PUSH_PARAM_ILL("P002", "消息参数格式不合法"),
  ;
  private String error_code;
  private String msg;

  ErrorCode(String code, String msg) {
    this.error_code = code;
    this.msg = msg;
  }

  public String getError_code() {
    return error_code;
  }

  public String getMsg() {
    return msg;
  }
}
