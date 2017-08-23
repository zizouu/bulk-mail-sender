package com.zizou.bulkmail.gui.panel;

import com.zizou.bulkmail.data.EmlData;
import com.zizou.bulkmail.data.SaveTypeData;
import com.zizou.bulkmail.service.EmlCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.ThreadPoolExecutor;

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
               EmlCreator creator = new EmlCreator();
               if(getData(creator)){
                  int count = creator.create();
                  JOptionPane.showMessageDialog(RootPanel.this, count + " Messages created!");
               }
            }catch (Exception ex){
               log.info(ex.getMessage());
            }

         }
      };
   }

   private boolean getData(EmlCreator creator){
      boolean result = false;
      if(this.emlPanel.dataInvalidCheck() && this.savePanel.dataInvalidCheck()){
         creator.setEmlData(emlPanel.getEmlData());
         creator.setSaveTypeData(savePanel.getSaveTypeData());
         result = true;
      }

      return result;
   }
}
