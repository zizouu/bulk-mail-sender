package com.zizou.bulkmail.gui.panel;

import com.zizou.bulkmail.data.EmlData;
import com.zizou.bulkmail.data.CheckBoxPanelData;
import com.zizou.bulkmail.gui.ui.LabeledTextPanel;
import com.zizou.bulkmail.gui.ui.TextCheckBoxPanel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;

/**
 * Created by zizou on 2017-08-21.
 */
@Component
public class EmlPanel extends AbstractPanel{
    private LabeledTextPanel fromPanel = new LabeledTextPanel("From", new JTextField(20));
    private LabeledTextPanel toPanel = new LabeledTextPanel("To", new JTextField(20));
    private TextCheckBoxPanel randomSubjectPanel;
    private TextCheckBoxPanel randomContentPanel;

    @PostConstruct
    public void init(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(this.fromPanel);
        this.add(this.toPanel);

        LabeledTextPanel subjectPanel = new LabeledTextPanel("Subject", new JTextField(30));
        LabeledTextPanel contentPanel = new LabeledTextPanel("Content", new JTextArea(10, 50));
        randomSubjectPanel = new TextCheckBoxPanel("random", subjectPanel, false);
        randomContentPanel = new TextCheckBoxPanel("random", contentPanel, false);

        this.add(this.randomSubjectPanel);
        this.add(this.randomContentPanel);
    }

    public EmlData getEmlData(){
        CheckBoxPanelData subjectData = randomSubjectPanel.getCheckBoxPanelData();
        CheckBoxPanelData contentData = randomContentPanel.getCheckBoxPanelData();
        String from = fromPanel.getText();
        String to = toPanel.getText();
        String subject = subjectData.getText();
        String content = contentData.getText();
        boolean isRandomSubject = subjectData.isChecked();
        boolean isRandomContent = contentData.isChecked();

        return new EmlData(from, to, subject, content, isRandomSubject, isRandomContent);
    }

    @Override
    public boolean dataInvalidCheck() {
        boolean result = true;

        if(StringUtils.isBlank(fromPanel.getText())){
            this.alertInvalidMessage("From");
            result = false;
        }else if(StringUtils.isBlank(toPanel.getText())){
            this.alertInvalidMessage("To");
            result = false;
        }

        return result;
    }
}
