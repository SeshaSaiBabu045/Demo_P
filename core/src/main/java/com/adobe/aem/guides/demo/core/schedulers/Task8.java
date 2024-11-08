package com.adobe.aem.guides.demo.core.schedulers;

import com.day.cq.replication.Replicator;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import java.util.HashMap;
import java.util.Map;

@Component(service = Runnable.class, immediate = true)
@Designate(ocd = Task8.Config.class)
public class Task8 implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(Task8.class);

    @Reference
    private Scheduler scheduler;

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Reference
    private Replicator replicator;

    private String pagePath;
    private String cronExpression;

    @Activate
    protected void activate(Config config) {
        pagePath = config.pagePath();
        cronExpression = config.scheduler_expression();

        // Schedule the job using the provided cron expression
        ScheduleOptions options = scheduler.EXPR(cronExpression);
        options.name("PagePublisherJob");
        options.canRunConcurrently(false);
        scheduler.schedule(this, options);

        LOG.info("Scheduler activated with Cron expression: {} and Page Path: {}", cronExpression, pagePath);
    }

    @Deactivate
    protected void deactivate() {
        scheduler.unschedule("PagePublisherJob");
        LOG.info("Scheduler deactivated.");
    }

    @Override
    public void run() {
        try (ResourceResolver resourceResolver = getServiceResourceResolver()) {
            if (resourceResolver != null) {
                PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
                Page rootPage = pageManager.getPage(pagePath);

                if (rootPage != null) {
                    publishPagesUnderPath(rootPage, resourceResolver);
                } else {
                    LOG.error("Page path '{}' does not exist.", pagePath);
                }
            } else {
                LOG.error("Failed to obtain service resource resolver.");
            }
        } catch (Exception e) {
            LOG.error("Error during scheduler execution: ", e);
        }
    }

    private void publishPagesUnderPath(Page rootPage, ResourceResolver resourceResolver) {
        rootPage.listChildren().forEachRemaining(page -> {
            try {
                Session session = resourceResolver.adaptTo(Session.class);
                replicator.replicate(session, ReplicationActionType.ACTIVATE, page.getPath());
                LOG.info("Published page: {}", page.getPath());
            } catch (Exception e) {
                LOG.error("Failed to publish page: {}", page.getPath(), e);
            }
        });
    }

    private ResourceResolver getServiceResourceResolver() {
        Map<String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE, "welcome"); // Assuming 'welcome' is the sub-service
        try {
            return resourceResolverFactory.getServiceResourceResolver(param);
        } catch (Exception e) {
            LOG.error("Failed to get service resource resolver: ", e);
        }
        return null;
    }

    @ObjectClassDefinition(name = "Page Publisher Scheduler Configuration")
    public @interface Config {

        @AttributeDefinition(name = "Page Path", description = "Path of the page under which all pages will be published")
        String pagePath() default "/content/Demo/us/en";

        @AttributeDefinition(name = "Cron Expression", description = "Cron expression for scheduling the job")
        String scheduler_expression() default "*/1 * * * * ?"; // Default to execute at 12 PM daily
    }
}
