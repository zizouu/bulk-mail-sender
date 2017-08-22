package com.zizou.bulkmail.gui.panel;

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
public class SavePathPanel extends JPanel{
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
                    setTypeInputPanel(ModuleService.getBean(LocalSaveTypePanel.class));
                }else if(selectedButton == SavePathPanel.this.scp){
                    setTypeInputPanel(ModuleService.getBean(ScpSaveTypePanel.class));
                }
            }
        };
    }

    private void setTypeInputPanel(AbstractSaveTypePanel panel){
        this.typeInputPanel.removeAll();
        this.typeInputPanel.add(panel);
        this.typeInputPanel.updateUI();
    }


}
