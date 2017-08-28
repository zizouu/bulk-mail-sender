package com.zizou.bulkmail.gui.panel.save;

import com.zizou.bulkmail.data.CheckBoxPanelData;
import com.zizou.bulkmail.data.SaveTypeData;
import com.zizou.bulkmail.data.SmtpSaveTypeData;
import com.zizou.bulkmail.gui.ui.LabeledTextPanel;
import com.zizou.bulkmail.gui.ui.TextCheckBoxPanel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zizou on 2017-08-24.
 */
@Component
public class SmtpSaveTypePanel extends AbstractSaveTypePanel{
    private LabeledTextPanel hostPanel = new LabeledTextPanel("Host", new JTextField(20));
    private LabeledTextPanel portPanel = new LabeledTextPanel("Port", new JTextField(5));
    private LabeledTextPanel mailFromPanel = new LabeledTextPanel("Mail From", new JTextField(30));
    //private LabeledTextPanel rcptToPanel = new LabeledTextPanel("Rcpt To", new JTextArea(5, 30));
    private LabeledTextPanel rcptToPanel = new LabeledTextPanel("Rcpt To", new JTextField(30));
    private TextCheckBoxPanel limitTimePanel;
    private TextCheckBoxPanel limitCountPanel;


    private LabeledTextPanel countPanel = new LabeledTextPanel("Count", new JTextField("1",10));

    @PostConstruct
    private void init(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel connectionPanel = new JPanel(new FlowLayout());
        connectionPanel.add(this.hostPanel);
        connectionPanel.add(this.portPanel);

        this.add(connectionPanel);
        this.add(this.mailFromPanel);
        this.add(this.rcptToPanel);
        this.add(this.countPanel);

        LabeledTextPanel limitTimeTextPanel = new LabeledTextPanel("Limit per second", new JTextField(10));
        LabeledTextPanel limitCountTextPanel = new LabeledTextPanel("Number of limit", new JTextField(10));
        this.limitTimePanel = new TextCheckBoxPanel("use", limitTimeTextPanel, true);
        this.limitCountPanel = new TextCheckBoxPanel("use", limitCountTextPanel, true);

        ActionListener limitTimeCheckBoxListener = this.createCheckLimitTimeListener();
        this.limitTimePanel.addCheckboxActionLister(limitTimeCheckBoxListener);
        this.limitCountPanel.setPanelEnable(false);

        this.add(this.limitTimePanel);
        this.add(this.limitCountPanel);
    }

    private ActionListener createCheckLimitTimeListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox limitTimeCheckbox = (JCheckBox)e.getSource();
                SmtpSaveTypePanel.this.limitCountPanel.setPanelEnable(limitTimeCheckbox.isSelected());
            }
        };
    }

    @Override
    public SaveTypeData getSaveTypeData() {
        CheckBoxPanelData limitTimeData = this.limitTimePanel.getCheckBoxPanelData();
        CheckBoxPanelData limitCountData = this.limitCountPanel.getCheckBoxPanelData();

        return new SmtpSaveTypeData(Integer.parseInt(this.countPanel.getText()), this.hostPanel.getText(), this.portPanel.getText(),
                                    this.mailFromPanel.getText(), this.rcptToPanel.getText(),
                                    getLimitData(limitTimeData), getLimitData(limitCountData),
                                    limitTimeData.isChecked(), limitCountData.isChecked());
    }

    private int getLimitData(CheckBoxPanelData data){
        String limitText;
        if(data.isChecked()){
            limitText = data.getText();
        }else{
            limitText = "1";
        }
        return Integer.parseInt(limitText);
    }

    @Override
    public boolean dataInvalidCheck() {
        boolean result = true;
        CheckBoxPanelData limitTimeData = this.limitTimePanel.getCheckBoxPanelData();
        CheckBoxPanelData limitCountData = this.limitCountPanel.getCheckBoxPanelData();

        if(StringUtils.isBlank(this.hostPanel.getText())){
            this.alertInvalidMessage("Host");
            result = false;
        }else if(StringUtils.isBlank(this.portPanel.getText())){
            this.alertInvalidMessage("Port");
            result = false;
        }else if(StringUtils.isBlank(this.rcptToPanel.getText())){
            this.alertInvalidMessage("Rcpt To");
            result = false;
        }else if(limitTimeData.isChecked() && StringUtils.isBlank(limitTimeData.getText())){
            this.alertInvalidMessage("Limit per second");
            result = false;
        }else if(limitCountData.isChecked() && StringUtils.isBlank(limitCountData.getText())){
            this.alertInvalidMessage("Number of limit");
            result = false;
        }
        return result;
    }
}
