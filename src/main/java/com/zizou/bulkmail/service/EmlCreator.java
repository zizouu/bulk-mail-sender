package com.zizou.bulkmail.service;

import lombok.Setter;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * Created by zizou on 2017-08-21.
 */
@Setter
public class EmlCreator {
    private File parentDir;
    //private EmlData header;

    public void create() throws Exception{
        /*Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "172.22.1.103");

        Session session = Session.getDefaultInstance(props);

        System.out.println(header.toString());

        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(header.getFrom());
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(header.getTo()));
        msg.setSubject(header.getSubject());
        msg.setContent(header.getContentText(), "text/html; charset=utf-8");

        File emlFile = new File(parentDir, getEmlFileName() + ".eml");
        emlFile.createNewFile();
        msg.writeTo(new FileOutputStream(emlFile));*/
    }

    public String getEmlFileName(){
        return String.valueOf(System.currentTimeMillis());
    }
}
