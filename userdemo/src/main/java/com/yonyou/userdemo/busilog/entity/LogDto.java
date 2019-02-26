package com.yonyou.userdemo.busilog.entity;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

public class LogDto implements Serializable {

    private static final long serialVersionUID = 1402968878894663669L;

    private String id;

    private String clientIp;

    private String busiCode;

    private String busiName;

    private String busiContent;

    private Date logdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public String getBusiName() {
        return busiName;
    }

    public void setBusiName(String busiName) {
        this.busiName = busiName;
    }

    public String getBusiContent() {
        return busiContent;
    }

    public void setBusiContent(String busiContent) {
        this.busiContent = busiContent;
    }

    public Date getLogdate() {
        if(logdate == null){
            return null;
        }else{
            return (Date) logdate.clone();
        }
    }

    public void setLogdate(Date logdate) {
        if(logdate == null){
            this.logdate=null;
        }else{
            this.logdate=(Date) logdate.clone();
        }
    }
}
