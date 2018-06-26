package com.yps.shangqi.push.entities;

import java.util.Date;

public class PushMessage {
    private Integer id;

    private String sysId;

    private String title;

    private String content;

    private String extras;

    private String userId;

    private Byte pushType;

    private Byte deviceType;

    private String deviceToken;

    private Byte pushStatus;

    private Byte count;

    private Byte validFlag;

    private Date startTime;

    private Date cTime;

    private Date mTime;

    public PushMessage(Integer id, String sysId, String title, String content, String extras, String userId, Byte pushType, Byte deviceType, String deviceToken, Byte pushStatus, Byte count, Byte validFlag, Date startTime, Date cTime, Date mTime) {
        this.id = id;
        this.sysId = sysId;
        this.title = title;
        this.content = content;
        this.extras = extras;
        this.userId = userId;
        this.pushType = pushType;
        this.deviceType = deviceType;
        this.deviceToken = deviceToken;
        this.pushStatus = pushStatus;
        this.count = count;
        this.validFlag = validFlag;
        this.startTime = startTime;
        this.cTime = cTime;
        this.mTime = mTime;
    }

    public PushMessage() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId == null ? null : sysId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras == null ? null : extras.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Byte getPushType() {
        return pushType;
    }

    public void setPushType(Byte pushType) {
        this.pushType = pushType;
    }

    public Byte getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Byte deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken == null ? null : deviceToken.trim();
    }

    public Byte getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Byte pushStatus) {
        this.pushStatus = pushStatus;
    }

    public Byte getCount() {
        return count;
    }

    public void setCount(Byte count) {
        this.count = count;
    }

    public Byte getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(Byte validFlag) {
        this.validFlag = validFlag;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public Date getmTime() {
        return mTime;
    }

    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }
}