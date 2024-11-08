// package com.adobe.aem.guides.demo.core.schedulers;

// import java.util.Date;
// import java.util.Iterator;

// import org.apache.sling.api.resource.Resource;
// import org.apache.sling.api.resource.ResourceResolver;
// import org.apache.sling.api.resource.ValueMap;
// import org.osgi.service.component.annotations.Component;
// import org.osgi.service.component.annotations.Reference;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import com.day.cq.replication.Replicator;
// import com.day.cq.wcm.api.Page;
// import com.day.cq.wcm.api.PageManager;

// @Component(service = Runnable.class, immediate = true, property = { "scheduler.expression=*/3 * * * * ?" })
// public class Task11ArticleExpiryScheduler implements Runnable {

//     private static final Logger LOG = LoggerFactory.getLogger(Task11ArticleExpiryScheduler.class);

//     @Reference
//     private Seshasubservices seshaservices; // Ensure this interface has a method getResourceResolver()

//     @Reference
//     private Replicator replicator;

//     @Override
//     public void run() {
//         LOG.info("Inside Run Method");
//         ResourceResolver resolver = seshaservices.getResourceResolver();
//         PageManager pageManager = resolver.adaptTo(PageManager.class);
//         @SuppressWarnings("unused")
//         Page articlePage = pageManager.getPage("/content/Demo/us/en");
//         @SuppressWarnings("unused")
//         Iterator<Page> childpages = articlePage.listChildren();
//         while (childpages.hasNext()) {
//             Page page = (Page) childpages.next();
//             @SuppressWarnings("unused")
//             Resource contentResource = page.getContentResource();
//             ValueMap properties = contentResource.getValueMap();
//             Date pageIsExperity = properties.get("pageIsExperity", Date.class);
//             Date todayDate = new Date();
//         }

//         // if (articlePage != null && pageIsExperity.compareTo(todayDate)) {
//             // Add logic here to check ExpiryDate and perform publish/unpublish actions
//             LOG.info("Article Page found: " + articlePage.getTitle());
//         }

//         replicator.replicate(null, null, null);

//     }
// }
