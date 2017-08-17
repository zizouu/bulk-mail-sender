package com.zizou.bulkmail.gui.util;

import com.zizou.bulkmail.gui.ui.LabeledTextField;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zizou on 2017-08-17.
 */
public class PanelCreator {

   public static JPanel createPanelWithLabeledTextField(LabeledTextField... uis){
       JPanel panel = new JPanel(new FlowLayout());
       for (LabeledTextField ui : uis){
            panel.add(ui.getLabel());
            panel.add(ui.getField());
       }
       return panel;
   }
}
