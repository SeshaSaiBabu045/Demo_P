package com.adobe.aem.guides.demo.core.models;

import java.util.Calendar;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Headermulti {
    @ValueMapValue
    private String name;

    @ValueMapValue
    private Calendar date;

    public String getName() {
        return name;
    }

    public Calendar getDate() {
        return date;
    }

}
