package com.zizou.bulkmail.gui.panel;

import com.zizou.bulkmail.data.SaveTypeData;
import com.zizou.bulkmail.gui.ui.LabeledTextPanel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by zizou on 2017-08-22.
 */
@Component
public class LocalSaveTypePanel extends AbstractSaveTypePanel {
    private LabeledTextPanel pathPanel = new LabeledTextPanel("Path", new JTextArea(1, 30));
    private LabeledTextPanel countPanel = new LabeledTextPanel("Count", new JTextField("1",10));
    private JButton selectButton = new JButton("Select");

    @PostConstruct
    private void init(){
        JPanel selectPathPanel = new JPanel(new FlowLayout());
        this.pathPanel.setIsEditable(false);
        this.selectButton.addActionListener(createSelectDirButtonListener());
        selectPathPanel.add(this.pathPanel);
        selectPathPanel.add(this.selectButton);
        this.add(selectPathPanel);
        this.add(this.countPanel);
    }

    @Override
    public void setUI(){

    }

    private ActionListener createSelectDirButtonListener(){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser dirChooser = new JFileChooser();
                dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if(dirChooser.showOpenDialog(LocalSaveTypePanel.this) == 0){
                    File file = dirChooser.getSelectedFile();
                    LocalSaveTypePanel.this.pathPanel.setText(file.getAbsolutePath());
                }
            }
        };
    }

    @Override
    public SaveTypeData getSaveTypeData() {
        return new SaveTypeData(pathPanel.getText(), Integer.parseInt(countPanel.getText()));
    }
}
