package com.adobe.aem.guides.demo.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.Group;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.jcr.RepositoryException;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/checkAccess",
        "sling.servlet.methods=GET"
})
public class Task14 extends SlingAllMethodsServlet {

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        ResourceResolver resourceResolver = null;

        try {
            // Get the current ResourceResolver
            resourceResolver = request.getResourceResolver();

            // Get the UserManager from the ResourceResolver
            UserManager userManager = resourceResolver.adaptTo(UserManager.class);

            if (userManager != null) {
                // Get the current user ID from the session
                String userId = resourceResolver.getUserID();

                // Get the user as an Authorizable object
                Authorizable user = userManager.getAuthorizable(userId);

                // Get the "access" group as an Authorizable object
                Authorizable group = userManager.getAuthorizable("access");

                if (user != null && group != null && group.isGroup()) {
                    // Check if the user belongs to the "access" group
                    boolean hasAccess = ((Group) group).isMember(user);

                    // Respond with the appropriate message
                    if (hasAccess) {
                        response.getWriter().write("User " + userId + " has access.");
                    } else {
                        response.getWriter().write("User " + userId + " doesn't have access.");
                    }
                } else {
                    response.getWriter().write("Error: Group 'access' or User not found.");
                }
            } else {
                response.getWriter().write("Error: Unable to get UserManager.");
            }
        } catch (RepositoryException e) {
            response.getWriter().write("Error: " + e.getMessage());
        } finally {
            if (resourceResolver != null && resourceResolver.isLive()) {
                resourceResolver.close();
            }
        }
    }
}
