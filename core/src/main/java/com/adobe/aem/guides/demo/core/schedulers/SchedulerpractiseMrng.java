package com.adobe.aem.guides.demo.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "my scheduler", description = "scheduler is ccreated")
public @interface SchedulerpractiseMrng {

    @AttributeDefinition(name = "service name", type = AttributeType.STRING, description = "enter the service name")
    public String getservice_name() default "practise";

    @AttributeDefinition(name = "can run concurrently", type = AttributeType.BOOLEAN, description = "can run concurrently")
    public boolean canrunconcurrently() default false;

    @AttributeDefinition(name = "Enabled scheduler", type = AttributeType.BOOLEAN, description = "Enable the scheduler")
    public boolean Enabledscheduler() default true;

    @AttributeDefinition(name = "Expression", type = AttributeType.STRING, description = "enter the Expression"

    )
    public String getExpressi();
}
