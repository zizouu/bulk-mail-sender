package com.zizou.bulkmail.gui.panel;

import javax.swing.*;

/**
 * Created by zizou on 2017-08-23.
 */
public abstract class AbstractPanel extends JPanel{
    public abstract boolean dataInvalidCheck();
    public void alertInvalidMessage(String invalidType){
        JOptionPane.showMessageDialog(this, getAlertMessage(invalidType));
    }
    private String getAlertMessage(String invalidType){
        return "Please check '" + invalidType + "' field...";
    }
}
