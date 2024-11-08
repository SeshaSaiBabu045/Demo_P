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

@Designate(ocd = Artcleschdulerconfig.class)
@Component(service = Runnable.class, immediate = true)
public class ArticleSchedular implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(ArticleSchedular.class);
    @Reference
    Scheduler Scheduler;
    private Artcleschdulerconfig configuration;

    @Override
    public void run() {
        LOG.info("article schedular run() method will be ");
        

    }

    @Activate
    public void activate(Artcleschdulerconfig configuration) {
        updateconfiguration(configuration);

    }

    @Deactivate
    public void deactivate(Artcleschdulerconfig configuration) {
        updateconfiguration(configuration);
    }

    @Modified
    public void modified(Artcleschdulerconfig configuration) {
        updateconfiguration(configuration);
    }

    public void updateconfiguration(Artcleschdulerconfig configuration) {
        if(configuration.enable()){
            ScheduleOptions options = Scheduler.EXPR(configuration.schdulerexpression());
        options.canRunConcurrently(false);
        options.name(configuration.schdulername());
        Scheduler.schedule(this, options);
        LOG.info("update the schedular{}", configuration.schdulerexpression());
        }
        else{
            Scheduler.unschedule(configuration.schdulerexpression());
        }

    }
    
}
