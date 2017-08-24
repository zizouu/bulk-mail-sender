package com.zizou.bulkmail.data;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by zizou on 2017-08-22.
 */
@Getter
@Setter
public class CheckBoxPanelData {
    private boolean isChecked;
    private String text;

    public CheckBoxPanelData(boolean isChecked, String text) {
        this.isChecked = isChecked;
        this.text = text;
    }
}
