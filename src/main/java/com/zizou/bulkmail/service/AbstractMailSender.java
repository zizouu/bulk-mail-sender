package com.zizou.bulkmail.service;

import com.zizou.bulkmail.data.EmlData;
import com.zizou.bulkmail.data.LocalSaveTypeData;
import com.zizou.bulkmail.data.SaveTypeData;
import com.zizou.bulkmail.data.ScpSaveTypeData;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by zizou on 2017-08-21.
 */
@Setter
public abstract class AbstractMailSender {
    private static final Logger log = LoggerFactory.getLogger(AbstractMailSender.class);

    protected SaveTypeData saveTypeData;
    protected EmlData emlData;
    protected MimeMessage message;
    protected int result = 0;

    public abstract int send() throws Exception;

    public boolean initializeData() throws MessagingException{
        if(saveTypeData == null || emlData == null){
            return false;
        }
        this.message = makeMimeMessage();
        return true;
    }

    private MimeMessage makeMimeMessage() throws MessagingException{
        Properties props = System.getProperties();
        Session session = Session.getDefaultInstance(props);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(this.emlData.getFrom());
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.emlData.getTo()));
        message.setSubject((this.emlData.isRandomSubject() ? getRandomString(10) : this.emlData.getSubject()));
        message.setContent((this.emlData.isRandomContent() ? getRandomString(30) : this.emlData.getContent()), "text/html; charset=utf-8");

        return message;
    }

    protected String getEmlFileName(){
        return String.valueOf(System.currentTimeMillis());
    }

    private String getRandomString(int count){
        return RandomStringUtils.randomAlphanumeric(count);
    }
}
