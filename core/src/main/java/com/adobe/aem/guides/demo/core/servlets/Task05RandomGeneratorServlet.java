package com.adobe.aem.guides.demo.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(service = { Servlet.class }, property = {
        "sling.servlet.paths=/bin/randomGenerator",
        "sling.servlet.methods=GET"
})
public class Task05RandomGeneratorServlet extends SlingAllMethodsServlet {

    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int STRING_LENGTH = 6;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // Generate the random values and write to the response
        String randomValues = generateRandomValues();
        try (PrintWriter writer = response.getWriter()) {
            writer.write(randomValues);
        }
    }

    private String generateRandomValues() {
        SecureRandom random = new SecureRandom();
        StringBuilder result = new StringBuilder(STRING_LENGTH);

        for (int i = 0; i < STRING_LENGTH; i++) {
            int index = random.nextInt(LETTERS.length());
            result.append(LETTERS.charAt(index));
        }

        return result.toString();
    }
}
