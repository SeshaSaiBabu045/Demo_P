package com.adobe.aem.guides.demo.core.servlets;
import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

@Component(service= Servlet.class, immediate=true)
@SlingServletResourceTypes(resourceTypes= "/bin/brave/www/Dell/Com")

public class Brave extends SlingAllMethodsServlet {
   @Override
    protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        super.doPut(request, response);
        response.getWriter().write("this is put method in resource type");
    }
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        super.doPut(request, response);
        response.getWriter().write("this is Get method in resource type");
    }
    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        super.doPut(request, response);
        response.getWriter().write("this is post methos in resource type");
    }
    @Override
    protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        super.doPut(request, response);
        response.getWriter().write("this is the delete method in resource Type");
    }
}
