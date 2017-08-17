package com.zizou.bulkmail.gui.ui;

import lombok.Getter;

import javax.swing.*;

/**
 * Created by zizou on 2017-08-17.
 */
@Getter
public class LabeledTextField {
    private JLabel label;
    private JTextField field;

    public LabeledTextField(String label, String defaultField, int fieldLength){
        this.label = new JLabel(label);
        this.field = new JTextField(defaultField, fieldLength);
    }

    public LabeledTextField(String label, int fieldLength){
        this.label = new JLabel(label);
        this.field = new JTextField(fieldLength);
    }
}
