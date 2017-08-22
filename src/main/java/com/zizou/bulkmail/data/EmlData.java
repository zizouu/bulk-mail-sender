package com.zizou.bulkmail.data;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by zizou on 2017-08-22.
 */
@Getter
@Setter
public class EmlData {
    private String from;
    private String to;
    private String subject;
    private String content;
    private boolean isRandomSubject;
    private boolean isRandomContent;

    public EmlData(String from, String to, String subject, String content, boolean isRandomSubject, boolean isRandomContent) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.isRandomSubject = isRandomSubject;
        this.isRandomContent = isRandomContent;
    }
}
