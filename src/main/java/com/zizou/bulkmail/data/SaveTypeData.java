package com.zizou.bulkmail.data;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by zizou on 2017-08-22.
 */
@Getter
@Setter
public class SaveTypeData {
    private int count;

    public SaveTypeData(int count) {
        this.count = count;
    }
}
