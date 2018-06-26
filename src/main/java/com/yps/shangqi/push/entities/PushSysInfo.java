package com.yps.shangqi.push.entities;

import java.util.Date;

public class PushSysInfo {
    private Integer id;

    private String sysName;

    private String sysId;

    private String sysDesc;

    private Date cTime;

    private Date mTime;

    public PushSysInfo(Integer id, String sysName, String sysId, String sysDesc, Date cTime, Date mTime) {
        this.id = id;
        this.sysName = sysName;
        this.sysId = sysId;
        this.sysDesc = sysDesc;
        this.cTime = cTime;
        this.mTime = mTime;
    }

    public PushSysInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName == null ? null : sysName.trim();
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId == null ? null : sysId.trim();
    }

    public String getSysDesc() {
        return sysDesc;
    }

    public void setSysDesc(String sysDesc) {
        this.sysDesc = sysDesc == null ? null : sysDesc.trim();
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