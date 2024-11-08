 package com.adobe.aem.guides.demo.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Headercom {

    @ValueMapValue
    private String text;

    @ValueMapValue
    private String image;

    @ValueMapValue
    private String checkbox;

    @ChildResource
    private List<Headermulti> multifield;

    public String getText() {
        return text;
    }

    public String getCheckbox() {
        return checkbox;
    }

    public String getImage() {
        return image;
    }

    public List<Headermulti> getMultifield() {
        return multifield;
    }

}
