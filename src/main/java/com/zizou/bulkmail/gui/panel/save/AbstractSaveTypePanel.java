package com.zizou.bulkmail.gui.panel.save;

import com.zizou.bulkmail.data.SaveTypeData;
import com.zizou.bulkmail.gui.panel.AbstractPanel;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

/**
 * Created by zizou on 2017-08-22.
 */
public abstract class AbstractSaveTypePanel extends AbstractPanel{

    @PostConstruct
    private void init(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public abstract SaveTypeData getSaveTypeData();

}
