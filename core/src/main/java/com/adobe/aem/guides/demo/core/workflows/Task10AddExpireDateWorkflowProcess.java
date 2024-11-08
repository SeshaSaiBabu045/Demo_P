package com.adobe.aem.guides.demo.core.workflows;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

@Component(service = WorkflowProcess.class, property = { "process.label=Add Expire Date Property to Page" })
public class Task10AddExpireDateWorkflowProcess implements WorkflowProcess {

    private static final Logger log = LoggerFactory.getLogger(Task10AddExpireDateWorkflowProcess.class);
    private static final String PAGE_RESOURCE_TYPE = "cq:Page";
    private static final String EXPIRED_DATE_PROPERTY = "expireddate";
    private static final int EXPIRATION_PERIOD_DAYS = 365; // 1 year

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap)
            throws WorkflowException {
        String payloadPath = workItem.getWorkflowData().getPayload().toString();

        try (ResourceResolver resourceResolver = getServiceResourceResolver()) {
            Resource resource = resourceResolver.getResource(payloadPath);
            if (resource != null && resource.isResourceType(PAGE_RESOURCE_TYPE)) {
                addExpiredDateProperty(resource);
            }
        } catch (Exception e) {
            log.error("Error while adding expire date property to page {}", payloadPath, e);
            throw new WorkflowException(e.getMessage(), e);
        }
    }

    private void addExpiredDateProperty(Resource resource) throws RepositoryException {
        Node pageNode = resource.adaptTo(Node.class);
        if (pageNode != null && pageNode.hasNode("jcr:content")) {
            Node contentNode = pageNode.getNode("jcr:content");
            Calendar creationDate = contentNode.hasProperty("jcr:created")
                    ? contentNode.getProperty("jcr:created").getDate()
                    : new GregorianCalendar();
            Calendar expirationDate = (Calendar) creationDate.clone();
            expirationDate.add(Calendar.DAY_OF_MONTH, EXPIRATION_PERIOD_DAYS);

            // Add the expireddate property
            contentNode.setProperty(EXPIRED_DATE_PROPERTY, expirationDate);
            pageNode.getSession().save();
            log.info("Added {} property with value {} to page {}", EXPIRED_DATE_PROPERTY, expirationDate.getTime(),
                    resource.getPath());
        }
    }

    private ResourceResolver getServiceResourceResolver() throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put(ResourceResolverFactory.SUBSERVICE, "welcome"); // Ensure you have a service user
                                                                  // configured with the required
                                                                  // permissions
        return resourceResolverFactory.getServiceResourceResolver(param);
    }
}