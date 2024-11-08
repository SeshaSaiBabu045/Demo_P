package com.adobe.aem.guides.demo.core.models;

import javax.inject.Inject;

//import java.util.Calendar;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
//import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Lastnested {
    @Inject
    private String dob;

    public String getDob() {
        return dob;
    }

}
