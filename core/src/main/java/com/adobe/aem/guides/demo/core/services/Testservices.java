package com.adobe.aem.guides.demo.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component(immediate = true)
public class Testservices {

    private static final Logger Log = LoggerFactory.getLogger(Testservices.class);

    @Activate
    public void activate() {
        Log.info("Component Activated");
    }

    @Deactivate
    public void deactivate() {
        Log.info("Component Deactivated");
    }

    @Modified
    public void modified() {
        Log.info("Component Modified");
    }
}

      
    

