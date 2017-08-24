package com.zizou.bulkmail.gui.panel;

import com.zizou.bulkmail.data.SaveTypeData;
import com.zizou.bulkmail.gui.MainFrame;
import com.zizou.bulkmail.gui.panel.save.AbstractSaveTypePanel;
import com.zizou.bulkmail.gui.panel.save.LocalSaveTypePanel;
import com.zizou.bulkmail.gui.panel.save.ScpSaveTypePanel;
import com.zizou.bulkmail.gui.panel.save.SmtpSaveTypePanel;
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
    private JRadioButton smtp = new JRadioButton("smtp");
    private AbstractSaveTypePanel selectedSaveTypePanel;
    private JPanel saveTypePanel = new JPanel(new BorderLayout());
    private JPanel typeInputPanel = new JPanel(new BorderLayout());

    @PostConstruct
    private void init(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.saveTypePanel.setLayout(new FlowLayout());
        this.saveTypePanel.add(this.local);
        this.saveTypePanel.add(this.scp);
        this.saveTypePanel.add(this.smtp);

        ButtonGroup saveTypeButtonGroup = new ButtonGroup();
        saveTypeButtonGroup.add(this.local);
        saveTypeButtonGroup.add(this.scp);
        saveTypeButtonGroup.add(this.smtp);

        ActionListener saveTypeListener = createSaveTypeButtonListener();
        this.local.addActionListener(saveTypeListener);
        this.scp.addActionListener(saveTypeListener);
        this.smtp.addActionListener(saveTypeListener);

        this.add(saveTypePanel, "Bottom");
        this.add(typeInputPanel);

        this.local.doClick();
    }

    private ActionListener createSaveTypeButtonListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object selectedButton = e.getSource();
                if(selectedButton == SavePathPanel.this.local){
                    SavePathPanel.this.selectedSaveTypePanel = setTypeInputPanel(ModuleService.getBean(LocalSaveTypePanel.class));
                    ModuleService.getBean(MainFrame.class).setSize(800, 700);
                }else if(selectedButton == SavePathPanel.this.scp){
                    SavePathPanel.this.selectedSaveTypePanel = setTypeInputPanel(ModuleService.getBean(ScpSaveTypePanel.class));
                    ModuleService.getBean(MainFrame.class).setSize(800, 800);
                }else if(selectedButton == SavePathPanel.this.smtp){
                    SavePathPanel.this.selectedSaveTypePanel = setTypeInputPanel(ModuleService.getBean(SmtpSaveTypePanel.class));
                    ModuleService.getBean(MainFrame.class).setSize(800, 950);
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
