package com.adobe.aem.guides.demo.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Foundation {
    @ValueMapValue
    private String custmizecur;

    @ValueMapValue
    private String path;

    public String getCustmizecur() {
        return custmizecur;
    }

    public String getPath() {
        return path;
    }

    @ChildResource
    private List<Camp> sesha;

    public List<Camp> getSesha() {
        return sesha;
    }

}
