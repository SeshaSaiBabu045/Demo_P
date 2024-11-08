package com.adobe.aem.guides.demo.core.schedulers;

import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service=Runnable.class , immediate=true , name="timicSCHE")
@Designate(ocd=schedulerconfiguration.class )
public class timicSCHE implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(timicSCHE.class);
    @Reference
    Scheduler scheduler;
    @Override
    public void run() {
        
    }

    @Activate
    public void schdulerActivate(){

    }
    @Deactivate
    public void SchedulerDeactivate(){

    }
    @Modified
    public void schdulerModified(){

    }
    public void SchedulerUpdate(){
        if (true) {
            
        }
    }

}
