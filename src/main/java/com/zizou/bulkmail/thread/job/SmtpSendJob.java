package com.zizou.bulkmail.thread.job;

import com.zizou.bulkmail.data.SaveTypeData;
import com.zizou.bulkmail.data.SmtpSaveTypeData;
import com.zizou.bulkmail.gui.MainFrame;
import com.zizou.bulkmail.gui.panel.EmlPanel;
import com.zizou.bulkmail.gui.panel.RootPanel;
import com.zizou.bulkmail.service.AbstractMailSender;
import com.zizou.bulkmail.service.ModuleService;
import lombok.Setter;
import org.apache.commons.net.smtp.SMTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.internet.MimeMessage;
import java.io.IOException;

/**
 * Created by zizou on 2017-08-24.
 */
@Setter
public class SmtpSendJob implements SendJob {
    private static final Logger log = LoggerFactory.getLogger(SmtpSendJob.class);
    private MimeMessage message;
    private SaveTypeData data;

    @Override
    public void run() {
        this.execute();
    }

    @Override
    public void execute(){
        try{
            SmtpSaveTypeData smtpData = (SmtpSaveTypeData) data;
            SMTPClient smtpClient = new SMTPClient("UTF-8");
            smtpClient.setConnectTimeout(10000);
            smtpClient.connect(smtpData.getHost(), Integer.parseInt(smtpData.getPort()));
            smtpClient.setSender(smtpData.getMailFrom());
            smtpClient.addRecipient(smtpData.getRcptTo());
        }catch (IOException e){
            log.info(e.getMessage());
            ModuleService.getBean(EmlPanel.class).alertInvalidMessage("Fail to smtp connection... " + e.getMessage());
        }

    }
}
