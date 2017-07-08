package com.hbc.cms.demo;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;

import info.magnolia.rendering.model.*;
import info.magnolia.rendering.template.configured.ConfiguredTemplateDefinition;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import java.lang.String;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hbc.cms.demo.nashorn.*;

public class heroSlideComponentModel<RD extends ConfiguredTemplateDefinition> extends RenderingModelImpl<ConfiguredTemplateDefinition> {
 
    public heroSlideComponentModel(Node content, ConfiguredTemplateDefinition definition, RenderingModel<?> parent) throws PathNotFoundException, RepositoryException {
        super(content, definition, parent);
        System.out.println("HERE INSIDE THE JAVA CODE - HERO SLIDE" + content.getPath());
        PropertyIterator it = content.getProperties("mgnl:template");
        Map<String, Object> theDataMap = new HashMap<String, Object>();

        System.out.println("HeroSlide - The NAME : " + it.toString());
        theDataMap = createDataMap(content);
    }
    
    public static Map<String, Object> createDataMap (Node content){
    	
        Map<String, Object> finalDataMap = new HashMap<String, Object>();
        Map<String, Object> slideDataMap = new HashMap<String, Object>();
    
        System.out.println("HeroSlide - Here in CreateDataMap Method");
        
        try {
			PropertyIterator pi = content.getProperties();
			
			while (pi.hasNext()){
			//	Object key = pi.next();
			//	System.out.println("the Key is : " +key.toString());
				javax.jcr.Property prop = (javax.jcr.Property) pi.next();
				if(!prop.getName().toString().contains("mgnl:") || !prop.getName().toString().contains("jcr:")){				
					if(prop.getName().toString().equals("background_image") || prop.getName().toString().equals("foreground_image")){
						// append the DAM path here *******
						String back_imgPath = "http://localhost:8080/magnoliaAuthor/dam";
						back_imgPath = back_imgPath.concat(prop.getValue().getString());
						slideDataMap.put(prop.getName(), back_imgPath);
						System.out.println("HeroSlide - Background-Image - the Key is : "+ prop.getName() + " Value is : " + back_imgPath);
					}
					else {
						slideDataMap.put(prop.getName(), prop.getValue().getString());
						System.out.println("HeroSlide - the Key is : "+ prop.getName() + " Value is : " + prop.getValue().getString());
					}
				}	
			}
			
			 Gson gson = new Gson();
		        String json = gson.toJson(slideDataMap);
			    System.out.printf( "\nHeroSlide - JSON: %s", json.toString() );
			    
			   // NashornDebug nd = new NashornDebug();
			    
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		return slideDataMap;
    }

    
    
	public String getData(){
		return "Hello World Data";
		
	}
		
}