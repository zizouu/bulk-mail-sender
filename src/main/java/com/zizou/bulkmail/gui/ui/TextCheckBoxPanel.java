package com.zizou.bulkmail.gui.ui;

import com.zizou.bulkmail.data.CheckBoxPanelData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zizou on 2017-08-22.
 */
public class TextCheckBoxPanel extends JPanel{
    private LabeledTextPanel textPanel;
    private JCheckBox checkBox;
    private boolean isEditableCheckboxChecked;

    public TextCheckBoxPanel(String checkText, LabeledTextPanel textPanel, boolean isEditableCheckboxChecked){
        this.checkBox = new JCheckBox(checkText);
        this.textPanel = textPanel;
        this.isEditableCheckboxChecked = isEditableCheckboxChecked;
        init();
    }

    private void init(){
        this.setLayout(new FlowLayout());
        this.checkBox.addActionListener(createRandomCheckListener());
        this.textPanel.setIsEditable(!isEditableCheckboxChecked);
        this.add(textPanel);
        this.add(checkBox);
    }

    private ActionListener createRandomCheckListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(TextCheckBoxPanel.this.checkBox.isSelected()){
                    TextCheckBoxPanel.this.textPanel.setIsEditable(isEditableCheckboxChecked);
                }else{
                    TextCheckBoxPanel.this.textPanel.setIsEditable(!isEditableCheckboxChecked);
                }
            }
        };
    }

    public void addCheckboxActionLister(ActionListener listener){
        this.checkBox.addActionListener(listener);
    }

    public void setPanelEnable(boolean isEnable){
        this.checkBox.setEnabled(isEnable);
        this.checkBox.setSelected(isEnable);
        this.textPanel.setIsEditable(isEnable);
    }

    public CheckBoxPanelData getCheckBoxPanelData(){
        return new CheckBoxPanelData(this.checkBox.isSelected(), this.textPanel.getText());
    }
}
