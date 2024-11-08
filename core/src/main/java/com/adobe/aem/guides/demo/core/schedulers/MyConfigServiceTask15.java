package com.adobe.aem.guides.demo.core.schedulers;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@Component(service = MyConfigServiceTask15.class, immediate = true)
@Designate(ocd = MyConfigServiceTask15.Config.class)
public class MyConfigServiceTask15 {

    @ObjectClassDefinition(name = "My Configuration Service")
    public @interface Config {
        String clientId() default "defaultClientId";

        String apiToken() default "defaultApiToken";

        String pagePath() default "/content/demo/us/en/sling model/";
    }

    private String clientId;
    private String apiToken;
    private String pagePath;

    @Activate
    @Modified
    protected void activate(Config config) {
        this.clientId = config.clientId();
        this.apiToken = config.apiToken();
        this.pagePath = config.pagePath();
    }

    public String getClientId() {
        return clientId;
    }

    public String getApiToken() {
        return apiToken;
    }

    public String getPagePath() {
        return pagePath;
    }
}