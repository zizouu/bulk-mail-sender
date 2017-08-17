package com.zizou.bulkmail.gui.panel;

import com.zizou.bulkmail.gui.ui.LabeledTextField;
import com.zizou.bulkmail.gui.util.PanelCreator;
import com.zizou.bulkmail.service.SmtpMailSender;
import org.apache.catalina.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimePart;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zizou on 2017-08-16.
 */
@Component
public class ServerPanel extends JPanel {
   private static final Logger log = LoggerFactory.getLogger(ServerPanel.class);
   private final LabeledTextField hostField = new LabeledTextField("Host",50);
   private final LabeledTextField portField = new LabeledTextField("Port", "25", 5);
   private final LabeledTextField mailFromFiled = new LabeledTextField("Mail From",50);
   private final LabeledTextField rcptToField = new LabeledTextField("Rcpt To",50);
   private final JButton testButton = new JButton("GetText");
   private final JTextField testField = new JTextField("Here get text result");

   @PostConstruct
   private void init(){
      log.info("initial start");
      this.setLayout(new BoxLayout(this, 1));
      JPanel hostPanel = PanelCreator.createPanelWithLabeledTextField(hostField, portField);
      this.add(hostPanel);
      JPanel mailFromPanel = PanelCreator.createPanelWithLabeledTextField(mailFromFiled);
      this.add(mailFromPanel);
      JPanel rcptToPanel = PanelCreator.createPanelWithLabeledTextField(rcptToField);
      this.add(rcptToPanel);
      this.add(testButton);
      this.add(testField);
      this.testButton.addActionListener(this.createGetTextEvent());
      log.info("initial finish");
   }

   private ActionListener createGetTextEvent(){
      return new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            /*String s = ServerPanel.this.rcptToField.getField().getText();
            ServerPanel.this.testField.setText(s);*/

            SmtpMailSender sender = new SmtpMailSender();
            try{
               sender.sendMail();
            }catch (Exception ex){
               log.info(ex.getMessage());
            }

         }
      };
   }

}
