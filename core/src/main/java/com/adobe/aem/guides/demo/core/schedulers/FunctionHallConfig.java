package com.adobe.aem.guides.demo.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="FunctionHallConfig")
public @interface FunctionHallConfig {

    @AttributeDefinition(name="schedulerExpresssion")
    String schedularExpression() default "*/0 * * * * * ?";
    /// */0 means the schedular will be update in every sec   like * */0 ---mean execute in every min



    @AttributeDefinition(name=" Concurrent")
    // thread does not run parallely that y we can use concurrent();
    boolean  Concurrent() default false;


    @AttributeDefinition(name="name of the schedular")
    String schedulersName()  default "Article";

    @AttributeDefinition(name="enable the schedular it will be start ")
    boolean Enabled()  default true;



}
