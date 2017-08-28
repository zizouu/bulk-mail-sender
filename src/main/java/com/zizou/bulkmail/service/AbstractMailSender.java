package com.zizou.bulkmail.service;

import com.zizou.bulkmail.data.*;
import com.zizou.bulkmail.thread.Workers;
import com.zizou.bulkmail.util.EncodingTextUtil;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by zizou on 2017-08-21.
 */
@Setter
public abstract class AbstractMailSender {
    private static final Logger log = LoggerFactory.getLogger(AbstractMailSender.class);

    protected Workers workers;
    protected SaveTypeData saveTypeData;
    protected EmlData emlData;
    protected int result = 0;

    public abstract int send() throws Exception;

    public boolean initializeData() throws MessagingException{
        if(saveTypeData == null || emlData == null){
            return false;
        }
        this.workers = new Workers();
        this.workers.initialize();
        return true;
    }

    protected MimeMessage makeMimeMessage() throws MessagingException{
        Properties props = System.getProperties();
        Session session = Session.getDefaultInstance(props);

        MimeMessage message = new MimeMessage(session);
        if(StringUtils.isNotBlank(this.emlData.getFrom())){
            message.setFrom(this.emlData.getFrom());
        }else{
            message.setFrom(((SmtpSaveTypeData)this.saveTypeData).getMailFrom());
        }
        if(StringUtils.isNotBlank(this.emlData.getTo())){
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.emlData.getTo()));
        }else{
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(((SmtpSaveTypeData)this.saveTypeData).getRcptTo()));
        }
        message.setSubject((this.emlData.isRandomSubject() ? getRandomString(10) : this.emlData.getSubject()));
        message.setContent((this.emlData.isRandomContent() ? getRandomString(30) : this.emlData.getContent()), "text/html; charset=utf-8");
        message.setSentDate(new Date());

        return message;
    }

    protected String getEmlFileName(){
        return String.valueOf(System.currentTimeMillis());
    }

    private String getRandomString(int count){
        return RandomStringUtils.randomAlphanumeric(count);
    }



    public MimeBodyPart buildMimeBodyPart(){
        MimeBodyPart part = new MimeBodyPart();
        try{
            part.setText("abc");
            part.setHeader("Content-Transfer-Encoding", "quoted-printable");
        }catch (MessagingException e){
            e.printStackTrace();
        }
        return part;
    }

    public MimeMessage buildMimeHeader() throws UnsupportedEncodingException, MessagingException{
        MimeMessage mimeMessage = new MimeMessage((Session.getInstance(new Properties())));
        mimeMessage.setSubject(EncodingTextUtil.encodeText("123", "", ""));
        mimeMessage.setSentDate(new Date());
        mimeMessage.addFrom(new Address[]{new InternetAddress("thecarlos@naver.com")});
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("gzbale@gmail.com"));
        return mimeMessage;
    }

    public void sendMail() throws Exception{

    }
}
