package com.adobe.aem.guides.demo.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
@ObjectClassDefinition(name="schedulerconfiguration")
public @interface schedulerconfiguration {


    @AttributeDefinition(name="schedulerRunTime")
    String schedulerRunTime() default "*/0 * * * * * ?";

    @AttributeDefinition(name="concurent")
    boolean concurrent() default false;

    @AttributeDefinition(name="TimicTech")
    String SchedulerName() default"update in every sec";

    @AttributeDefinition(name="Scheduler Will be enable state")
    boolean enable() default true;


}
