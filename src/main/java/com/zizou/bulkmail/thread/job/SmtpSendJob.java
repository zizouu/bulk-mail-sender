package com.zizou.bulkmail.thread.job;

import com.zizou.bulkmail.data.SmtpSaveTypeData;
import com.zizou.bulkmail.gui.panel.EmlPanel;
import com.zizou.bulkmail.service.ModuleService;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;

/**
 * Created by zizou on 2017-08-24.
 */
@Setter
public class SmtpSendJob implements SendJob {
    private static final Logger log = LoggerFactory.getLogger(SmtpSendJob.class);
    private MimeMessage message;
    private SmtpSaveTypeData data;

    @Override
    public void run() {
        this.execute();
    }

    @Override
    public void execute(){
        Writer smtpWriter = null;
        AuthenticatingSMTPClient smtpClient = null;
        try{
            smtpClient = new AuthenticatingSMTPClient("SSL", false, "UTF-8");
            smtpClient.setConnectTimeout(10000);
            smtpClient.connect(this.data.getHost(), Integer.parseInt(this.data.getPort()));
            smtpClient.setSender(this.data.getMailFrom());
            smtpClient.addRecipient(this.data.getRcptTo());
            smtpWriter = smtpClient.sendMessageData();
            this.writeMessage(smtpWriter);
            IOUtils.closeQuietly(smtpWriter);
            smtpClient.completePendingCommand();
            this.checkReply(smtpClient);
        }catch (IOException | MessagingException e){
            log.info(e.getMessage());
            ModuleService.getBean(EmlPanel.class).alertInvalidMessage("Fail to send message... : " + e.getMessage());
        }finally {
            IOUtils.closeQuietly(smtpWriter);
            this.closeClient(smtpClient);
        }
    }

    private void closeClient(SMTPClient smtpClient) {
        if (smtpClient != null && smtpClient.isConnected()) {
            try {
                smtpClient.quit();
            } catch (IOException e) {
                log.debug("smtp quit fail !", e.getMessage());
            }

            try {
                smtpClient.disconnect();
            } catch (IOException e) {
                log.debug("smtp disconnect fail !", e.getMessage());
            }

        }
    }

    private void checkReply(SMTPClient client) throws IOException {
        if(!SMTPReply.isPositiveCompletion(client.getReplyCode())){
            log.info("fail.... message is : {}", client.getReplyString());
        }else{
            log.info("success.... message is : {}", client.getReplyString());
        }
    }

    private void writeMessage(Writer smtpWriter) throws MessagingException, IOException{
        ByteArrayOutputStream inputStream = new ByteArrayOutputStream();
        this.message.writeTo(inputStream);
        ByteArrayInputStream outputStream = new ByteArrayInputStream(inputStream.toByteArray());

        BufferedReader reader = new BufferedReader(new InputStreamReader(outputStream, "UTF-8"));
        BufferedWriter writer = new BufferedWriter(smtpWriter);
        this.writeHeader(reader, writer);
        this.writeBody(reader, writer);
        writer.flush();
    }

    private void writeHeader(BufferedReader reader, BufferedWriter writer) throws IOException{
        String line = null;

        while((line = reader.readLine()) != null && !this.isBodyStart(line)){
            this.writeLine(writer, line);
        }

        this.writeLine(writer, StringUtils.defaultString(line));
    }

    private void writeBody(BufferedReader reader, BufferedWriter writer) throws IOException {
        char[] readBuffer = new char[16384];

        int readSize;
        while((readSize = reader.read(readBuffer)) != -1) {
            writer.write(readBuffer, 0, readSize);
        }

    }

    private void writeLine(BufferedWriter writer, String line) throws IOException {
        writer.write(line);
        writer.newLine();
    }

    private boolean isBodyStart(String line){
        return StringUtils.equals(line, "");
    }
}
