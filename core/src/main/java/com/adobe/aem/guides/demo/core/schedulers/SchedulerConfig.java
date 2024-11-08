package com.adobe.aem.guides.demo.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Page Publishing Scheduler Configuration", description = "Configuration for the Page Publishing Scheduler")
public @interface SchedulerConfig {

    @AttributeDefinition(name = "Page Path", description = "Path of the page to publish")
    String pagePath() default "/content/Demo/us/en/*";

    @AttributeDefinition(name = "Cron Expression", description = "Cron expression for scheduling the publishing job")
    String cronExpression() default "0 0 0 * * ?";
}
