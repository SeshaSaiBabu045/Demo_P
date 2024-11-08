// package com.adobe.aem.guides.demo.core.servlets;

// import org.apache.jackrabbit.oak.spi.security.authentication.external.basic.DefaultSyncConfig.Authorizable;
// import org.apache.sling.api.SlingHttpServletRequest;
// import org.apache.sling.api.SlingHttpServletResponse;
// import org.apache.sling.api.servlets.SlingAllMethodsServlet;
// import org.osgi.service.component.annotations.Component;
// import org.osgi.service.component.annotations.Reference;

// import javax.jcr.Session;
// import javax.servlet.Servlet;
// import java.io.IOException;
// import java.util.Iterator;

// import com.day.cq.security.User;
// import com.day.cq.security.UserManager;
// import com.day.cq.security.Group;
// import org.apache.sling.api.resource.ResourceResolver;
// import org.apache.sling.api.resource.ResourceResolverFactory;
// import org.apache.sling.api.servlets.HttpConstants;
// import org.apache.sling.api.servlets.ServletResolverConstants;

// @SuppressWarnings("deprecation")
// @Component(service = { Servlet.class }, property = {
//         ServletResolverConstants.SLING_SERVLET_PATHS + "=/bin/checkAccess",
//         ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET
// })
// public class CheckAccessServlet extends SlingAllMethodsServlet {

//     @Reference
//     private ResourceResolverFactory resourceResolverFactory;

//     @Override
//     protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response)
//             throws IOException {
//         ResourceResolver resourceResolver = null;
//         try {
//             resourceResolver = request.getResourceResolver();
//             final Session session = resourceResolver.adaptTo(Session.class);

//             // Get the user ID from the session
//             final String userId = session.getUserID();
//             final UserManager userManager = resourceResolver.adaptTo(UserManager.class);
//            // final Authorizable authorizable = userManager.getAuthorizable(userId);

//             boolean hasAccess = false;
//             // Check if the user is part of the "Access" group
//             if (userId != null) {
//               //  final Iterator<Group> groupIterator = userId.memberOf();
//              // if ("Access".equals(group.getID())) {
//                         hasAccess = true;
//             //             break;
//             //         }
//             //     }
//             // }

// //             try {
// //                 // Send the response based on access
// //                 if (hasAccess) {
// //                     response.getWriter().write("The user " + userId + " has access.");
// //                 } else {
// //                     response.getWriter().write("The user " + userId + " does not have access.");
// //                 }
// //             } catch (final IOException e) {
// //                 response.getWriter().write("Error writing response: " + e.getMessage());
// //             } catch (final Exception e) {
// //                 response.getWriter().write("Error: " + e.getMessage());
// //             }
// //         } finally {
// //             if (resourceResolver != null && resourceResolver.isLive()) {
// //                 resourceResolver.close();
// //             }
// //         }
// //     }
// // }
