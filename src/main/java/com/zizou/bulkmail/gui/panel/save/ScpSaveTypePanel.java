package com.zizou.bulkmail.gui.panel.save;

import com.zizou.bulkmail.data.SaveTypeData;
import com.zizou.bulkmail.data.ScpSaveTypeData;
import com.zizou.bulkmail.gui.ui.LabeledTextPanel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

/**
 * Created by zizou on 2017-08-22.
 */
@Component
public class ScpSaveTypePanel extends AbstractSaveTypePanel {
    private LabeledTextPanel hostPanel = new LabeledTextPanel("Host", new JTextField(20));
    private LabeledTextPanel portPanel = new LabeledTextPanel("Port", new JTextField(10));
    private LabeledTextPanel idPanel = new LabeledTextPanel("ID", new JTextField(10));
    private LabeledTextPanel passwordPanel = new LabeledTextPanel("Password", new JTextField(20));
    private LabeledTextPanel pathPanel = new LabeledTextPanel("Path", new JTextField("/",50));
    private LabeledTextPanel countPanel = new LabeledTextPanel("Count", new JTextField("1",10));

    @PostConstruct
    public void init(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel connectPanel = new JPanel(new FlowLayout());
        connectPanel.add(this.hostPanel);
        connectPanel.add(this.portPanel);

        JPanel userPanel = new JPanel(new FlowLayout());
        userPanel.add(this.idPanel);
        userPanel.add(this.passwordPanel);

        this.add(connectPanel);
        this.add(userPanel);
        this.add(this.pathPanel);
        this.add(this.countPanel);
    }

    @Override
    public SaveTypeData getSaveTypeData() {
        return new ScpSaveTypeData(this.pathPanel.getText(), Integer.parseInt(this.countPanel.getText()), this.hostPanel.getText(),
                                    this.portPanel.getText(), this.idPanel.getText(), this.passwordPanel.getText());
    }

    @Override
    public boolean dataInvalidCheck() {
        if(StringUtils.isBlank(this.hostPanel.getText())){
            this.alertInvalidMessage("Host");
            return false;
        }else if(StringUtils.isBlank(this.portPanel.getText())){
            this.alertInvalidMessage("Port");
            return false;
        }else if(StringUtils.isBlank(this.idPanel.getText())){
            this.alertInvalidMessage("Id");
            return false;
        }else if(StringUtils.isBlank(this.passwordPanel.getText())){
            this.alertInvalidMessage("Password");
            return false;
        }else if(StringUtils.isBlank(this.pathPanel.getText())){
            this.alertInvalidMessage("Path");
            return false;
        }else if(StringUtils.isBlank(this.countPanel.getText())){
            this.alertInvalidMessage("Count");
            return false;
        }
        return true;
    }
}
