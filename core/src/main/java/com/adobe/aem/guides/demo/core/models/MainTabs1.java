package com.adobe.aem.guides.demo.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class MainTabs1 {

    @ValueMapValue
    private String header;
    @ValueMapValue(name = "logo-path")
    private String logopath;
    @ValueMapValue(name = "logo-mobile-image")
    private String logomobileimage;
    @ValueMapValue
    private String link;
    @ValueMapValue
    private String checkbox;
    @ValueMapValue
    private String dropdown;

    public String getDropdown() {
        return dropdown;
    }

    public String getHeader() {
        return header;
    }

    public String getLogopath() {
        return logopath;
    }

    public String getLogomobileimage() {
        return logomobileimage;
    }

    public String getLink() {
        return link;
    }

    public String getCheckbox() {
        return checkbox;
    }

    @ChildResource
    private List<SecondHeaderLink> multifield;

    public List<SecondHeaderLink> getMultifield() {
        return multifield;
    }

    @ChildResource
    private List<SideBarNavigation> sidebar3;

    public List<SideBarNavigation> getSidebar3() {
        return sidebar3;
    }
}
