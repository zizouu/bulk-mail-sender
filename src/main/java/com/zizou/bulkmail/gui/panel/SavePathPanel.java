package com.zizou.bulkmail.gui.panel;

import com.zizou.bulkmail.data.SaveTypeData;
import com.zizou.bulkmail.gui.panel.save.AbstractSaveTypePanel;
import com.zizou.bulkmail.gui.panel.save.LocalSaveTypePanel;
import com.zizou.bulkmail.gui.panel.save.ScpSaveTypePanel;
import com.zizou.bulkmail.service.ModuleService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zizou on 2017-08-21.
 */
@Component
public class SavePathPanel extends AbstractPanel{
    private JRadioButton local = new JRadioButton("local");
    private JRadioButton scp = new JRadioButton("scp");
    private AbstractSaveTypePanel selectedSaveTypePanel;
    private JPanel saveTypePanel = new JPanel(new BorderLayout());
    private JPanel typeInputPanel = new JPanel(new BorderLayout());

    @PostConstruct
    private void init(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.saveTypePanel.setLayout(new FlowLayout());
        this.saveTypePanel.add(this.local);
        this.saveTypePanel.add(this.scp);

        ButtonGroup saveTypeButtonGroup = new ButtonGroup();
        saveTypeButtonGroup.add(this.local);
        saveTypeButtonGroup.add(this.scp);

        ActionListener saveTypeListener = createSaveTypeButtonListener();
        this.local.addActionListener(saveTypeListener);
        this.scp.addActionListener(saveTypeListener);

        this.local.doClick();

        this.add(saveTypePanel, "Bottom");
        this.add(typeInputPanel);
    }

    private ActionListener createSaveTypeButtonListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedButton = e.getSource();
                if(selectedButton == SavePathPanel.this.local){
                    SavePathPanel.this.selectedSaveTypePanel = setTypeInputPanel(ModuleService.getBean(LocalSaveTypePanel.class));
                }else if(selectedButton == SavePathPanel.this.scp){
                    SavePathPanel.this.selectedSaveTypePanel = setTypeInputPanel(ModuleService.getBean(ScpSaveTypePanel.class));
                }
            }
        };
    }

    private AbstractSaveTypePanel setTypeInputPanel(AbstractSaveTypePanel panel){
        this.typeInputPanel.removeAll();
        this.typeInputPanel.add(panel);
        this.typeInputPanel.updateUI();

        return panel;
    }

    public SaveTypeData getSaveTypeData(){
        return this.selectedSaveTypePanel.getSaveTypeData();
    }

    @Override
    public boolean dataInvalidCheck() {
        return this.selectedSaveTypePanel.dataInvalidCheck();
    }
}
