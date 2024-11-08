package com.adobe.aem.guides.demo.core.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EventOsgiService.class)
public class EventOsgiService {
    private static final Logger Log = LoggerFactory.getLogger(EventOsgiService.class);

    @Reference
    private ResourceResolverFactory factory;

    public ResourceResolver getResourceResolver() {
        ResourceResolver resolver = null;
        try {
            Map<String, Object> props = new HashMap<>();
            props.put(ResourceResolverFactory.SUBSERVICE, "saisubservice"); // Ensure this subservice exists

            resolver = factory.getServiceResourceResolver(props);
            if (resolver == null) {
                Log.error("Failed to obtain ResourceResolver");
            }
        } catch (LoginException e) {
            Log.error("LoginException while getting ResourceResolver: {}", e.getMessage(), e);
        }
        return resolver;
    }
}
