package com.zizou.bulkmail.gui.panel;

import com.zizou.bulkmail.data.SaveTypeData;
import com.zizou.bulkmail.gui.ui.LabeledTextPanel;
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
    private LabeledTextPanel pathPanel = new LabeledTextPanel("Path", new JTextField(50));

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
    }

    @Override
    public void setUI(){

    }

    @Override
    public SaveTypeData getSaveTypeData() {
        return null;
    }
}
