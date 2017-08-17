package com.zizou.bulkmail.gui;

import com.zizou.bulkmail.gui.panel.ServerPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zizou on 2017-08-16.
 */

@Component
public class MainFrame extends JFrame{
    private static final Logger log = LoggerFactory.getLogger(MainFrame.class);

    @Autowired
    private ServerPanel serverPanel;

    public void showWindow(){
        this.setDefaultCloseOperation(3);
        this.setTitle("Bulk Mail Sender by zizou");
        this.setSize(1000, 1000);
        this.initLayout();
        this.setVisible(true);
    }

    public void initLayout(){
        log.info("init main frame start");
        this.setLayout(new BorderLayout());
        this.add(this.serverPanel, "Center");
        log.info("init main frame stop");
    }
}
