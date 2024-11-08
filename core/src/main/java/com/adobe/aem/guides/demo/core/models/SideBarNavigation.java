package com.adobe.aem.guides.demo.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SideBarNavigation {
    @ValueMapValue
    private String desktopicon;
    @ValueMapValue
    private String mobileicon;

    public String getDesktopicon() {
        return desktopicon;
    }

    public String getMobileicon() {
        return mobileicon;
    }

    @ChildResource
    private List<Thridsidebar> nestedmultifield;

    public List<Thridsidebar> getNestedmultifield() {
        return nestedmultifield;
    }

}
