package com.hbc.cms.demo;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;

import info.magnolia.rendering.model.*;
import info.magnolia.rendering.template.configured.ConfiguredTemplateDefinition;

import java.io.*;
import java.net.*;

import org.apache.commons.lang.StringUtils;

import java.lang.String;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class testComponentModel<RD extends ConfiguredTemplateDefinition> extends RenderingModelImpl<ConfiguredTemplateDefinition> {
 
    public testComponentModel(Node content, ConfiguredTemplateDefinition definition, RenderingModel<?> parent) throws PathNotFoundException, RepositoryException {
        super(content, definition, parent);
    }

	public String getData(){
		return "Hello World Data";
		
	}
	
	public static String cleanUpJson(String jsonStr){
		String cleanJsonStr = "";
		
		cleanJsonStr = jsonStr.replaceAll("\\{\"request\":\\{\\},\"response\":\\{\"results\":", " ");
		cleanJsonStr = cleanJsonStr.replaceAll("\\},\"errors\":\\[\\]\\}", " ");

		//	{"request":{},"response":{"results":
		//  },"errors":[]}
	//	System.out.println("REsponse :" + cleanJsonStr);
		return cleanJsonStr;
	}
	
	public static String getJson(String requestUrl)
    {
        String hostResponse = null;
       
        String responseBodyStr = null;
        
        System.out.println("HERE in the getHeaderJson method - URL :" + requestUrl);
        
        if (StringUtils.isNotEmpty(requestUrl))
        {
            try
            {
                URL url = new URL(requestUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    
                if (conn != null){
                    
                    conn.connect();
                    String statusMessage = conn.getResponseMessage();
                    int responseCode = conn.getResponseCode();
                    
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    
                    if (reader != null){
                        StringBuffer responseBody = new StringBuffer();
                        String line = null;
                        
                        while ((line = reader.readLine()) != null)
                            responseBody.append(line + "\n");
                        
                            PrintWriter out = null;

                            if (responseBody != null){
        
                                responseBodyStr = responseBody.toString();
                                
                                StringWriter outputWriter = new StringWriter();
                                
                                outputWriter.write(responseBodyStr);
                                
                                outputWriter.close();
                           
                                hostResponse = new String(outputWriter.toString());    
                           
                            } else {
                                // Return the response code
                                return String.valueOf(responseCode);
                            }
                        }
                    }
                
                }
                catch (Exception ex)
                {
                    System.out.println("Do Request: Exception - url " + requestUrl);
                    ex.printStackTrace();
                    return null;
                }
        }
        String ret = cleanUpJson(hostResponse);
       // return cleanUpJson(hostResponse);
        return ret;
    }
	
	
}