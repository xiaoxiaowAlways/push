package com.yps.shangqi.push.constants;

public enum JPushError {
  J1000("1000", true),
  J1001("1001", false),
  J1002("1002", false),
  J1003("1003", false),
  J1004("1004", false),
  J1005("1005", false),
  J1008("1008", false),
  J1009("1009", false),
  J1011("1011", false),
  J1020("1020", false),
  J1030("1030", true),
  J2002("2002", true),
  J2003("2003", false),
  J2004("2004", false),
  J2005("2005", true),
  ;
  private String code;
  private boolean tryFlag;

  JPushError(String code, boolean tryFlag) {
    this.code = code;
    this.tryFlag = tryFlag;
  }

  public String getCode() {
    return code;
  }

  public boolean isTryFlag() {
    return tryFlag;
  }
}
