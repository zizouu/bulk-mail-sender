package com.zizou.bulkmail.data;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by zizou on 2017-08-22.
 */
@Getter
@Setter
public class RandomPanelData {
    private boolean isRandom;
    private String text;

    public RandomPanelData(boolean isRandom, String text) {
        this.isRandom = isRandom;
        this.text = text;
    }
}
