package com.zizou.bulkmail.gui.ui;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 * Created by zizou on 2017-08-21.
 */
public class LabeledTextPanel extends JPanel{
    private JLabel label;
    private JTextComponent textComponent;

    public LabeledTextPanel(String label, JTextComponent textComponent){
        this.label = new JLabel(label + "   ");
        this.textComponent = textComponent;
        init();
    }

    private void init(){
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        this.add(label);
        this.add(textComponent);
        this.textComponent.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void setIsEditable(boolean isEditable){
        this.textComponent.setEditable(isEditable);
        this.textComponent.setDisabledTextColor(Color.BLACK);
        if(isEditable){
            this.textComponent.setBackground(new Color(255, 255, 255));
        }else{
            this.textComponent.setBackground(new Color(238, 238, 238));
        }

    }

    public JTextComponent getTextComponent(){
        return textComponent;
    }

    public String getText(){
        return textComponent.getText();
    }

    public void setText(String text){
        textComponent.setText(text);
    }
}
