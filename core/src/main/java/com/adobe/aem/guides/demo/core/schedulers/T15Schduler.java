package com.adobe.aem.guides.demo.core.schedulers;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.aem.guides.demo.core.servlets.UpdatePagePropertiesServlet;

@Component(service = Runnable.class, immediate = true, property = {
        "Scheduler.expression = 0 * * * * ?",
        "Scheduler.coucurrent=false"
})
public class T15Schduler implements Runnable {

    @Reference
    private UpdatePagePropertiesServlet Task15Servlet;

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public void run() {
        SlingHttpServletRequest request = null;
        SlingHttpServletResponse response = null;

        try {
            Task15Servlet.doGet(request, response);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
