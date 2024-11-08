// package com.adobe.aem.guides.demo.core.servlets;

// import org.apache.sling.api.SlingHttpServletRequest;
// import org.apache.sling.api.SlingHttpServletResponse;
// import org.apache.sling.api.servlets.SlingAllMethodsServlet;
// import org.osgi.service.component.annotations.Component;
// import org.osgi.framework.Constants;
// import org.osgi.service.component.annotations.Reference;

// import javax.jcr.RepositoryException;
// import javax.servlet.Servlet;
// import javax.servlet.http.HttpSession;
// import org.apache.sling.api.resource.ResourceResolver;
// import org.apache.sling.api.resource.ResourceResolverFactory;
// import org.apache.sling.api.resource.LoginException;
// import org.apache.jackrabbit.api.security.user.Authorizable;
// import org.apache.jackrabbit.api.security.user.Group;
// import org.apache.jackrabbit.api.security.user.UserManager;
// import org.json.JSONObject;

// import java.io.IOException;
// import java.util.Iterator;

// @Component(service = { Servlet.class }, property = {
// Constants.SERVICE_DESCRIPTION + "=User Access Check Servlet",
// "sling.servlet.paths=/bin/checkAccess",
// "sling.servlet.methods=GET"
// })
// public class AccessCheckServlet extends SlingAllMethodsServlet {

// @Reference
// private ResourceResolverFactory resolverFactory;

// @Override
// protected void doGet(SlingHttpServletRequest request,
// SlingHttpServletResponse response) throws IOException {
// HttpSession session = request.getSession(false);
// JSONObject jsonResponse = new JSONObject();
// ResourceResolver resourceResolver = null;

// if (session != null) {
// String userId = (String) session.getAttribute("userId");

// if (userId == null) {
// userId = "seshasai"; // default userId if not available in session
// }

// try {
// resourceResolver = resolverFactory.createWriter();
// UserManager userManager = resourceResolver.adaptTo(UserManager.class);

// if (userManager != null) {
// Authorizable user = userManager.getAuthorizable(userId);

// if (user != null) {
// boolean isMemberOfAccessGroup = false;
// Iterator<Group> groups = user.memberOf();

// while (groups.hasNext()) {
// Group group = groups.next();
// if ("access".equals(group.getID())) {
// isMemberOfAccessGroup = true;
// break;
// }
// }

// if (isMemberOfAccessGroup) {
// jsonResponse.put("status", "success");
// jsonResponse.put("message", "User " + userId + " has access.");
// } else {
// jsonResponse.put("status", "failure");
// jsonResponse.put("message", "User " + userId + " doesn't have access.");
// }
// } else {
// jsonResponse.put("status", "error");
// jsonResponse.put("message", "User not found.");
// }
// }
// } catch (LoginException e) {
// jsonResponse.put("status", "error");
// jsonResponse.put("message", "LoginException: " + e.getMessage());
// } catch (RepositoryException e) {
// jsonResponse.put("status", "error");
// jsonResponse.put("message", "RepositoryException: " + e.getMessage());
// } finally {
// if (resourceResolver != null) {
// resourceResolver.close();
// }
// }
// } else {
// jsonResponse.put("status", "error");
// jsonResponse.put("message", "No session found.");
// }

// response.setContentType("application/json");
// response.getWriter().write(jsonResponse.toString());
// }
// }
