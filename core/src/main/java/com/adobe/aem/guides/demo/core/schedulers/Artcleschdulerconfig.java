package com.adobe.aem.guides.demo.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Artcleschdulerconfig")
public @interface Artcleschdulerconfig {
    @AttributeDefinition(name = "schdulerexpression")
    String schdulerexpression() default "0 0/1 * 1/1 * ? *";
    //concurrent means thread does not run parallelly at a time
    @AttributeDefinition(name = "concurrent")
    boolean concurrent() default false;

    @AttributeDefinition(name = "SchedularExpression")
    String schdulername() default "Article";

    @AttributeDefinition(name = "schdulerexpression")
    boolean enable() default true;

}
