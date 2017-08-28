package com.zizou.bulkmail.gui.panel;

import com.zizou.bulkmail.data.LocalSaveTypeData;
import com.zizou.bulkmail.data.SaveTypeData;
import com.zizou.bulkmail.data.ScpSaveTypeData;
import com.zizou.bulkmail.data.SmtpSaveTypeData;
import com.zizou.bulkmail.gui.panel.save.AbstractSaveTypePanel;
import com.zizou.bulkmail.service.AbstractMailSender;
import com.zizou.bulkmail.service.LocalMailSender;
import com.zizou.bulkmail.service.ScpMailSender;
import com.zizou.bulkmail.service.SmtpMailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zizou on 2017-08-16.
 */
@Component
public class RootPanel extends JPanel {
   private static final Logger log = LoggerFactory.getLogger(RootPanel.class);

   @Autowired
   private EmlPanel emlPanel;
   @Autowired
   private SavePathPanel savePanel;

   private final JButton createButton = new JButton("Create!");


   @PostConstruct
   private void init(){
      this.setLayout(new BorderLayout());
      this.add(this.emlPanel, BorderLayout.PAGE_START);
      this.add(this.savePanel, BorderLayout.CENTER);

      JPanel actionPanel = new JPanel(new FlowLayout());
      this.createButton.setSize(60, 40);
      this.createButton.addActionListener(this.createGetTextEvent());
      actionPanel.add(this.createButton);
      this.add(actionPanel, BorderLayout.PAGE_END);
   }

   private ActionListener createGetTextEvent(){
      return new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            try{
               log.info("actionPerformed start!!");
               if(checkInvalidData()){
                  log.info("actionPerformed in!!");
                  SaveTypeData saveTypeData = RootPanel.this.savePanel.getSaveTypeData();

                  AbstractMailSender mailSender = RootPanel.this.getMailSender(saveTypeData);
                  mailSender.setEmlData(RootPanel.this.emlPanel.getEmlData());
                  mailSender.setSaveTypeData(saveTypeData);
                  if(mailSender.initializeData()){
                     int count = mailSender.send();
                     JOptionPane.showMessageDialog(RootPanel.this, count + " Messages send!");
                  }else{
                     JOptionPane.showMessageDialog(RootPanel.this, " Messages send fail");
                  }
               }
               log.info("actionPerformed end!!");
            }catch (Exception ex){
               log.info(ex.getMessage());
               JOptionPane.showMessageDialog(RootPanel.this, ex.getMessage());
            }

         }
      };
   }

   private AbstractMailSender getMailSender(SaveTypeData data){
      if(data instanceof LocalSaveTypeData){
         return new LocalMailSender();
      }else if(data instanceof ScpSaveTypeData){
         return new ScpMailSender();
      }else if(data instanceof SmtpSaveTypeData){
         return new SmtpMailSender();
      }else{
         return new LocalMailSender();
      }
   }

   private boolean checkInvalidData(){
      return this.emlPanel.dataInvalidCheck() && this.savePanel.dataInvalidCheck();
   }
}
