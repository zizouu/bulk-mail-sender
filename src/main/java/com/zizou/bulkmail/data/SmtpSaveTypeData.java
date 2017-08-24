package com.zizou.bulkmail.data;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by zizou on 2017-08-24.
 */
@Getter
@Setter
public class SmtpSaveTypeData extends SaveTypeData{
    private String host;
    private String port;
    private String mailFrom;
    private String rcptTo;
    private int limitTime;
    private int limitCount;
    private boolean isUseLimitTime;
    private boolean isUseLimitCount;

    public SmtpSaveTypeData(int count, String host, String port, String mailFrom, String rcptTo, int limitTime, int limitCount, boolean isUseLimitTime, boolean isUseLimitCount) {
        super(count);
        this.host = host;
        this.port = port;
        this.mailFrom = mailFrom;
        this.rcptTo = rcptTo;
        this.limitTime = limitTime;
        this.limitCount = limitCount;
        this.isUseLimitTime = isUseLimitTime;
        this.isUseLimitCount = isUseLimitCount;
    }
}
