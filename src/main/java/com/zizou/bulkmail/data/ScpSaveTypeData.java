package com.zizou.bulkmail.data;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by zizou on 2017-08-22.
 */
@Getter
@Setter
public class ScpSaveTypeData extends SaveTypeData{
    private String host;
    private String port;
    private String id;
    private String pw;

    public ScpSaveTypeData(String path, int count, String host, String port, String id, String pw) {
        super(path, count);
        this.host = host;
        this.port = port;
        this.id = id;
        this.pw = pw;
    }
}
