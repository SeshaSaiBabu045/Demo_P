package com.adobe.aem.guides.demo.core.servlets;

import com.adobe.aem.guides.demo.core.schedulers.T15MyService;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.jcr.Node;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(service = Servlet.class)
@SlingServletPaths(value = "/bin/updatePageProperties")
public class UpdatePagePropertiesServlet extends SlingAllMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(UpdatePagePropertiesServlet.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Reference
    private T15MyService myService;

    @Override
    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        String pagePath = myService.getPagePath();
        LOG.info("Page path from service: {}", pagePath);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put(ResourceResolverFactory.SUBSERVICE, "welcome");

        try (ResourceResolver resolver = resourceResolverFactory.getServiceResourceResolver(paramMap)) {
            Resource pageResource = resolver.getResource(pagePath);
            if (pageResource != null) {
                Node node = pageResource.adaptTo(Node.class);
                if (node != null && node.hasNode("jcr:content")) {
                    Node contentNode = node.getNode("jcr:content");
                    contentNode.setProperty("clientId", myService.getClientId());
                    contentNode.setProperty("apiToken", myService.getApiToken());
                    resolver.commit();
                    response.getWriter().write("Properties updated successfully");
                    LOG.info("Properties updated successfully at {}", pagePath);
                } else {
                    response.getWriter().write("Content node not found for: " + pagePath);
                    LOG.error("Content node not found for path: {}", pagePath);
                }
            } else {
                response.getWriter().write("Invalid page path: " + pagePath);
                LOG.error("Invalid page path: {}", pagePath);
            }
        } catch (Exception e) {
            LOG.error("Error updating page properties", e);
            response.getWriter().write("Error: " + e.getMessage());
        }
    }
}
