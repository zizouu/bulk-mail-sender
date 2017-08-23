package com.zizou.bulkmail.service;

import com.zizou.bulkmail.util.EncodingTextUtil;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by zizou on 2017-08-17.
 */
public class SmtpMailSender {
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
        Properties props = System.getProperties();
        //props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.host", "172.22.1.103");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom("thecarlos@daou.co.kr");
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress("thecarlos@naver.com"));
        msg.setSubject("test subject");
        msg.setContent("test content", "text/html; charset=utf-8");

        Transport.send(msg);
    }
}
