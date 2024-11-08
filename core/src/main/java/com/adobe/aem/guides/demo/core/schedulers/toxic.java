package com.adobe.aem.guides.demo.core.schedulers;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service=Runnable.class ,immediate=true, name="program of the scheduler")
@Designate(ocd=FunctionHallConfig.class)
public class toxic implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(toxic.class); 
     @Reference
     Scheduler scheduler;
    @Override
    public void run() {
        LOG.info(" program of the schduler method is run() is executed");
       
        
    }
   
    @Activate
    public void activate(FunctionHallConfig configuration){
        updateconfiguration(configuration);

    }
    @Deactivate
    public void Deactivate(FunctionHallConfig configuration){
        updateconfiguration(configuration);
    }

    @Modified
    public void Modified(FunctionHallConfig configuration){
        updateconfiguration(configuration);
    }
    public void updateconfiguration(FunctionHallConfig configuration){
      if(configuration.Enabled()){
        ScheduleOptions  options =scheduler.EXPR(configuration.schedularExpression());
        options.canRunConcurrently(false);
        options.name(configuration.schedulersName());
        scheduler.schedule(this,options);
        LOG.info("update the status here{}",configuration.schedularExpression());
 
      }
      else{
        scheduler.unschedule(configuration.schedulersName());
      }
      
    }


}
