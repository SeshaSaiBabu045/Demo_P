package com.adobe.aem.guides.demo.core.schedulers;

import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component(immediate = true, service = Runnable.class)
@ServiceDescription("My Scheduler")
@Designate(ocd = MySchedulerTask15.Config.class)
public class MySchedulerTask15 implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(MySchedulerTask15.class);

    @ObjectClassDefinition(name = "My Scheduler Configuration")
    public @interface Config {

        @AttributeDefinition(name = "Cron Expression", description = "Cron expression to trigger the scheduler")
        String scheduler_expression() default "0 0/1 * 1/1 * ? *"; // every minute
    }

    @Activate
    protected void activate(Config config) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this, 0, 1, TimeUnit.MINUTES);
        LOG.info("Scheduler activated with expression: {}", config.scheduler_expression());
    }

    @Override
    public void run() {
        LOG.info("Scheduler triggered, calling servlet...");
        // Logic to call the servlet can be placed here
    }
}
