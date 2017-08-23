package com.zizou.bulkmail.service;

import com.zizou.bulkmail.data.EmlData;
import com.zizou.bulkmail.data.LocalSaveTypeData;
import com.zizou.bulkmail.data.SaveTypeData;
import com.zizou.bulkmail.data.ScpSaveTypeData;
import com.zizou.bulkmail.gui.panel.RootPanel;
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
public class EmlCreator {
    private static final Logger log = LoggerFactory.getLogger(EmlCreator.class);

    private SaveTypeData saveTypeData;
    private EmlData emlData;

    public int create() throws Exception{
        int result = 0;
        MimeMessage message = getMimeMessage();

        if(saveTypeData instanceof LocalSaveTypeData){
            result = createToLocal(message);
        }else if(saveTypeData instanceof ScpSaveTypeData){
            result = createToScp(message);
        }
        return result;
    }

    private MimeMessage getMimeMessage() throws MessagingException{
        Properties props = System.getProperties();
        Session session = Session.getDefaultInstance(props);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(this.emlData.getFrom());
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.emlData.getTo()));
        message.setSubject((this.emlData.isRandomSubject() ? getRandomString(10) : this.emlData.getSubject()));
        message.setContent((this.emlData.isRandomContent() ? getRandomString(30) : this.emlData.getContent()), "text/html; charset=utf-8");

        return message;
    }

    private int createToLocal(MimeMessage message){
        int count = this.saveTypeData.getCount();
        int createCount = 0;

        for(int i = 0; i < count; i++){
            File emlFile = new File(this.saveTypeData.getPath(), getEmlFileName() + ".eml");
            boolean success;
            FileOutputStream fos = null;
            try{
                fos = new FileOutputStream(emlFile);
                success = emlFile.createNewFile();
                if(success){
                    message.writeTo(fos);
                    createCount++;
                }
            }catch (IOException e){
                log.info(e.getMessage());
            }catch (MessagingException e){
                log.info(e.getMessage());
            } finally{
                if(fos != null) {this.close(fos);}
            }
        }
        return createCount;
    }

    private int createToScp(MimeMessage message){
        int createCount = 0;
        //TODO: scp logic
        return createCount;
    }

    private String getEmlFileName(){
        return String.valueOf(System.currentTimeMillis());
    }

    private String getRandomString(int count){
        return RandomStringUtils.randomAlphanumeric(count);
    }

    private void close(Closeable o){
        try{
            o.close();
        }catch (IOException e){
            log.info(e.getMessage());
        }
    }
}
