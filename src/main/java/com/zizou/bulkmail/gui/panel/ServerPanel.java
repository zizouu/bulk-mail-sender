package com.zizou.bulkmail.gui.panel;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

/**
 * Created by zizou on 2017-08-16.
 */
@Component
public class ServerPanel extends JPanel {
   private final JTextField hostField = new JTextField(50);
   private final JTextField portField = new JTextField("25", 5);

   @PostConstruct
   private void init(){
      this.setLayout(new BoxLayout(this, 1));
      JPanel hostPanel = new JPanel(new FlowLayout());
      hostPanel.add(new JLabel("host"));
      hostPanel.add(this.hostField);
   }
}
