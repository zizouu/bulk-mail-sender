package com.zizou.bulkmail.data;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by zizou on 2017-08-23.
 */
@Getter
@Setter
public class LocalSaveTypeData extends SaveTypeData{
    private String path;

    public LocalSaveTypeData(String path, int count) {
        super(count);
        this.path = path;
    }
}
