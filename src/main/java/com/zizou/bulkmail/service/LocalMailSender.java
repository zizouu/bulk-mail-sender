package com.zizou.bulkmail.service;

import com.zizou.bulkmail.data.LocalSaveTypeData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zizou on 2017-08-24.
 */
public class LocalMailSender extends AbstractMailSender{
    private static final Logger log = LoggerFactory.getLogger(LocalMailSender.class);

    @Override
    public int send() throws Exception {
        int count = this.saveTypeData.getCount();

        for(int i = 0; i < count; i++){
            File emlFile = new File(((LocalSaveTypeData)this.saveTypeData).getPath(), getEmlFileName() + ".eml");
            try(FileOutputStream fos = new FileOutputStream(emlFile)){
                if(emlFile.isFile()){
                    this.makeMimeMessage().writeTo(fos);
                    result++;
                }
            }catch (IOException e){
                log.info(e.getMessage());
            }
        }
        return result;
    }
}
