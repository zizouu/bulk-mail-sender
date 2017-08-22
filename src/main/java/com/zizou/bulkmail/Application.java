package com.zizou.bulkmail;

import com.zizou.bulkmail.gui.MainFrame;
import com.zizou.bulkmail.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by zizou on 2017-08-16.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        ConfigurableApplicationContext context = null;
        try{
            context = (new SpringApplicationBuilder(new Object[]{Application.class})).headless(false).run(args);
            MainFrame mainFrame = ModuleService.getBean(MainFrame.class);
            mainFrame.showWindow();

            do{
                Thread.sleep(1000L);
            }while(mainFrame.isVisible());
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            if(context != null){
                context.close();
            }
        }

    }

    @Autowired
    private void init(ApplicationContext context){
        ModuleService.setContext(context);
    }
}
