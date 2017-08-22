package com.zizou.bulkmail.gui.panel;

import com.zizou.bulkmail.data.EmlData;
import com.zizou.bulkmail.data.RandomPanelData;
import com.zizou.bulkmail.gui.ui.LabeledTextPanel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;

/**
 * Created by zizou on 2017-08-21.
 */
@Component
public class EmlPanel extends JPanel{
    private LabeledTextPanel fromPanel = new LabeledTextPanel("From", new JTextField(20));
    private LabeledTextPanel toPanel = new LabeledTextPanel("To", new JTextField(20));
    private RandomPanel randomSubjectPanel;
    private RandomPanel randomContentPanel;

    @PostConstruct
    public void init(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(this.fromPanel);
        this.add(this.toPanel);

        LabeledTextPanel subjectPanel = new LabeledTextPanel("Subject", new JTextField(30));
        LabeledTextPanel contentPanel = new LabeledTextPanel("Content", new JTextArea(10, 50));
        randomSubjectPanel = new RandomPanel(subjectPanel);
        randomContentPanel = new RandomPanel(contentPanel);

        this.add(this.randomSubjectPanel);
        this.add(this.randomContentPanel);
    }

    public EmlData getEmlData(){
        RandomPanelData subjectData = randomSubjectPanel.getRandomPanelData();
        RandomPanelData contentData = randomContentPanel.getRandomPanelData();
        String from = fromPanel.getText();
        String to = toPanel.getText();
        String subject = subjectData.getText();
        String content = contentData.getText();
        boolean isRandomSubject = subjectData.isRandom();
        boolean isRandomContent = contentData.isRandom();

        return new EmlData(from, to, subject, content, isRandomSubject, isRandomContent);
    }
}
