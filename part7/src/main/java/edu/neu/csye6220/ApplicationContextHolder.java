package edu.neu.csye6220;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    ;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }

    public static synchronized ApplicationContext getApplicationContext(){
        if(applicationContext==null){
            applicationContext =  new ClassPathXmlApplicationContext("applicationContext.xml");
        }
        return applicationContext;
    }
}
