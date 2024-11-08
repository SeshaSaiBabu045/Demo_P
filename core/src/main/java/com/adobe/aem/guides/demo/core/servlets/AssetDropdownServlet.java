package com.adobe.aem.guides.demo.core.servlets;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.DamConstants;
import com.day.cq.dam.api.Rendition;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.resource.ResourceUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.framework.Constants;

import javax.servlet.Servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(service = Servlet.class, property = {
        Constants.SERVICE_DESCRIPTION + "=Get Assets from Folder",
        ServletResolverConstants.SLING_SERVLET_PATHS + "=/bin/getAssets",
        ServletResolverConstants.SLING_SERVLET_METHODS + "=GET"
})
public class AssetDropdownServlet extends SlingAllMethodsServlet {

    // private static final String ASSET_PATH = "/content/dam/Demo/music-healing";

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        ResourceResolver resolver = request.getResourceResolver();
        Resource folderResource = resolver.getResource("/content/dam/Demo/music-healing");

        if (folderResource != null && ResourceUtil.isNonExistingResource(folderResource)) {
            List<Map<String, String>> assetsList = new ArrayList<>();

            for (Resource child : folderResource.getChildren()) {
                Asset asset = child.adaptTo(Asset.class);
                if (asset != null) {
                    Map<String, String> assetInfo = new HashMap<>();
                    assetInfo.put("name", asset.getName());
                    Rendition rendition = asset.getRendition(DamConstants.ORIGINAL_FILE);
                    if (rendition != null) {
                        assetInfo.put("path", rendition.getPath());
                    }
                    assetsList.add(assetInfo);
                }
            }

            // Convert asset list to JSON format and return it
        
            response.setContentType("application/json");
            response.getWriter().write(assetsList.toString()); // Simplified JSON serialization, you can use Gson/JSON
                                                               // API for better output
        } else {
            response.setStatus(SlingHttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"error\":\"Assets folder not found!\"}");
        }
    }
}
//
