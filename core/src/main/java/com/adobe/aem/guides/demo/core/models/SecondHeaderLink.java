package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SecondHeaderLink {
    @ValueMapValue
    private String fristname;

    public String getFristname() {
        return fristname;
    }

    public String getImage() {
        return image;
    }

    @ValueMapValue
    private String image;
    
}
