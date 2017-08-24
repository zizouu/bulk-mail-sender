package com.zizou.bulkmail.thread.job;

import com.zizou.bulkmail.data.SaveTypeData;

import javax.mail.internet.MimeMessage;

/**
 * Created by zizou on 2017-08-24.
 */
public interface SendJob extends Runnable{
    public void execute() throws Throwable;
}
