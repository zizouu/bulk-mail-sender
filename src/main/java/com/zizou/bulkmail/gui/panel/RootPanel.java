package com.zizou.bulkmail.gui.panel;

import com.zizou.bulkmail.service.EmlCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
      log.info("initial start");
      this.setLayout(new BoxLayout(this, 1));
      this.add(emlPanel);
      this.add(savePanel);
      this.createButton.addActionListener(this.createGetTextEvent());

      log.info("initial finish");
   }

   private ActionListener createGetTextEvent(){
      return new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            try{
               EmlCreator eml = new EmlCreator();
               eml.setParentDir(RootPanel.this.chooseEmlPath());
               //eml.setHeader(RootPanel.this.getHeaderData());
               eml.create();
            }catch (Exception ex){
               log.info(ex.getMessage());
            }

         }
      };
   }

   public File chooseEmlPath(){
      JFileChooser chooser = new JFileChooser();
      chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      chooser.showDialog(RootPanel.this, "Select");
      File dir = chooser.getSelectedFile();
      //this.savePanel.setAreaText(dir.getAbsolutePath());

      return dir;
   }
}
