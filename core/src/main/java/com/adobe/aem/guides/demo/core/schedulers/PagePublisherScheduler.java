package com.adobe.aem.guides.demo.core.schedulers;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component(service = Runnable.class, immediate = true
// configurationPolicy = ConfigurationPolicy.REQUIRE
)
@Designate(ocd = SchedulerConfig.class)
public class PagePublisherScheduler implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(PagePublisherScheduler.class);

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Reference
    // private ReplicationService replicationService;

    private String pagePath;

    @PostConstruct
    @Modified
    public void activate() {
        SchedulerConfig config = getConfig();
        this.pagePath = config.pagePath();
        LOG.info("Scheduler activated with pagePath: {}", pagePath);
    }

    private SchedulerConfig getConfig() {
        // Implement the logic to retrieve the OSGi configuration
        // This method is a placeholder; replace with actual retrieval logic if needed
        return null;
    }

    @Override
    public void run() {
        ResourceResolver resolver = null;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put(ResourceResolverFactory.SUBSERVICE, "saisunservice");
            // resolver = resolverFactory.createWriter(params); // Ensure correct
            // sub-service setup
            publishPages(resolver, pagePath);
        } catch (Exception e) {
            LOG.error("Error during page publishing", e);
        } finally {
            if (resolver != null && resolver.isLive()) {
                resolver.close();
            }
        }
    }

    private void publishPages(ResourceResolver resolver, String path) {
        Resource resource = resolver.getResource(path);
        if (resource != null) {
            Iterator<Resource> pages = resource.listChildren();
            while (pages.hasNext()) {
                Resource page = pages.next();
                try {
                    // Publish page using ReplicationService
                    // replicationService.replicate(ReplicationActionType.ACTIVATE, page.getPath());
                    LOG.info("Published page: {}", page.getPath());
                } catch (Exception e) {
                    LOG.error("Error publishing page: {}", page.getPath(), e);
                }
            }
            // resolver.commit();
        } else {
            LOG.warn("No resource found at path: {}", path);
        }
    }
}
