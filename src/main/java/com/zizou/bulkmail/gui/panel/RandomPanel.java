package com.zizou.bulkmail.gui.panel;

import com.zizou.bulkmail.data.RandomPanelData;
import com.zizou.bulkmail.gui.ui.LabeledTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zizou on 2017-08-22.
 */
public class RandomPanel extends JPanel{
    private LabeledTextPanel textPanel;
    private JCheckBox randomCheckBox = new JCheckBox("Random");

    public RandomPanel(LabeledTextPanel textPanel){
        this.textPanel = textPanel;
        init();
    }

    private void init(){
        this.setLayout(new FlowLayout());
        this.randomCheckBox.addActionListener(createRandomCheckListener());
        this.add(textPanel);
        this.add(randomCheckBox);
    }

    private ActionListener createRandomCheckListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(RandomPanel.this.randomCheckBox.isSelected()){
                    RandomPanel.this.textPanel.setIsEditable(false);
                }else{
                    RandomPanel.this.textPanel.setIsEditable(true);
                }
            }
        };
    }

    public RandomPanelData getRandomPanelData(){
        return new RandomPanelData(this.randomCheckBox.isSelected(), this.textPanel.getText());
    }
}
