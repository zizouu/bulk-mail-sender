package com.zizou.bulkmail.service;

import com.zizou.bulkmail.data.SmtpSaveTypeData;
import com.zizou.bulkmail.thread.job.SmtpSendJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.io.IOException;

/**
 * Created by zizou on 2017-08-17.
 */
public class SmtpMailSender extends AbstractMailSender{
    private static final Logger log = LoggerFactory.getLogger(SmtpMailSender.class);

    @Override
    public int send(){
        SmtpSaveTypeData data = (SmtpSaveTypeData)this.saveTypeData;
        int sendCount = data.getCount();
        int limitedCount = data.getLimitCount();
        int limitedTime = data.getLimitTime() * 1000;
        int success = 0;

        // there are possible three cases.
        // time && count, only time, nothing
        // only time case, time is true and limitedCunt is default value(=1)
        // in this case, i % limitedCount always return 0
        try{
            for(int i = 1; i <= sendCount; i++){
                if(data.isUseLimitTime() && (i % limitedCount == 0)){
                    this.waitThread(limitedTime + 1000);
                }
                SmtpSendJob job = new SmtpSendJob();
                job.setData(data);
                job.setMessage(this.makeMimeMessage());
                this.workers.execute(job);
                success++;
            }
            this.waitForAllJobFinish();
        }catch (MessagingException e){
            log.info(e.getMessage());
            this.terminateWorkers();
        }


        return success;
    }

    private void terminateWorkers(){
        try{
            this.workers.terminate();
        }catch (InterruptedException e){
            log.info("terminate fail... " + e.getMessage());
        }
    }

    private void waitForAllJobFinish(){
        while(this.workers.isRun()){
            this.waitThread(1000);
        }
    }

    private void waitThread(int time){
        try{
            Thread.sleep((long)time);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
