package com.hbc.cms.demo;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;

import info.magnolia.jcr.util.NodeTypes.Resource;
import info.magnolia.rendering.model.*;
import info.magnolia.rendering.template.configured.ConfiguredTemplateDefinition;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import java.lang.String;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hbc.cms.demo.nashorn.*;

public class heroContainComponentModel<RD extends ConfiguredTemplateDefinition> extends RenderingModelImpl<ConfiguredTemplateDefinition> {
 
	public static String theJson = null;

	public heroContainComponentModel(Node content, ConfiguredTemplateDefinition definition, RenderingModel<?> parent) throws PathNotFoundException, RepositoryException {
        super(content, definition, parent);
        System.out.println("HeroContain 1 - HERE INSIDE THE JAVA CODE- HERO Contain" + content.getPath());
        Map<String, Object> theDataMap = new HashMap<String, Object>();

        theDataMap = createDataMap(content);
        theJson = getAllJsonData(theDataMap);
        System.out.println("\n\n HeroContain - 7 **** The FInal JSON: " + theJson);
        
    }
    
    public Map<String, Object> createDataMap (Node content){
    	    
        System.out.println("HeroContain 2 - Here in CreateDataMap Method");
        Map<String, Object> finalDataMap = new HashMap<String, Object>();
        Map<String, Object> slideDataMap = new HashMap<String, Object>();
        List<Map<String, Object>> fieldsMapList = new ArrayList();

        try {
			PropertyIterator pi = content.getProperties();
			Session session = content.getSession();						
			while (pi.hasNext()){
				javax.jcr.Property prop = (javax.jcr.Property) pi.next();
		        Map<String, Object> slideProperties = new HashMap();
		        
		        System.out.println("HeroContain 3 - Here in CreateDataMap Method - WHILE LOOP");

				if(!prop.getName().toString().startsWith("mgnl:") || !prop.getName().toString().startsWith("jcr:")){
					//slideDataMap.put(prop.getName(), prop.getValue().getString());
					if(prop.getName().toString().startsWith("linkSlide")){
						Node resourcePath = getResource(prop.getValue().getString(), session);
						System.out.println("HeroContain - the Key is : "+ prop.getName() + " Value is : " + prop.getValue().getString());
						slideProperties = getSlideData(resourcePath);
						fieldsMapList.add(slideProperties);
						
					}
					if(prop.getName().toString().equals("disclaimer_text")){
						//slideDataMap.put(prop.getName(), stringToHtmlString(prop.getValue().getString()));
						slideDataMap.put(prop.getName(), prop.getValue().getString());
						//slideDataMap.put(prop.getName(), "Disclaimer Txet is over here");
						System.out.println("HeroContain - the Key is : "+ prop.getName() + " Value is : " + stringToHtmlString(prop.getValue().getString()));
					}

				}
			}
			
			slideDataMap.put("tabs",fieldsMapList);
			finalDataMap.put("EditorialTabbedHeroCarousel", slideDataMap);	
			
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                
		return finalDataMap;
    }

    
    	
	public Node getResource(String resourcePath, Session s){
		
	      Node slideNode = null;
	      String resPath = resourcePath.concat("/body/0");
	      try {
	    	  slideNode = s.getNode(resPath);
	    	  System.out.println("\n HeroContain 4 - Here in get resource " +slideNode.getPath());
	    	  
		} catch (PathNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return slideNode;
	}
		
	public Map<String, Object> getSlideData(Node n) throws PathNotFoundException, RepositoryException{
		
        Map<String, Object> slideDataMap = new HashMap<String, Object>();
		slideDataMap = heroSlideComponentModel.createDataMap(n);
		System.out.println("\nHeroContain 5 - In GET Slide DATA with Params : " + content.getPath());
		return slideDataMap;
		
	}

	
	public String getAllJsonData(Map<String, Object> allSlidesData){
		
		// Gson gson = new Gson();
	        GsonBuilder builder = new GsonBuilder();
	        builder.disableHtmlEscaping();
	      
	        Gson gson = builder.create();
	        
	        String finalJson = gson.toJson(allSlidesData).replace("\\n", "<br>");
	        finalJson = finalJson.replaceAll("\n", "\\n");
		    System.out.printf( "\n HeroContain 6 - JSON: %s ", finalJson.toString() );
		    
		return finalJson;
	}
	
	public String getHtmlSnippet(){
	    System.out.println("\n HeroContain 8 Nashorn getHtmlSnippet - theJSON : " + theJson);
			NashornDebugModel nasDebug = new NashornDebugModel();
		    System.out.println("\n HeroContain 8-2 Nashorn getHtmlSnippet");

	        String carouselHtmlSnippet = nasDebug.carouselHtmlSnippet("EditorialTabbedHeroCarousel", theJson);	
	        try {
	        	
	        	System.out.println("WRITING THE PROPERTY HERE : " + content.getPath());
	        	Property prop = content.setProperty("heroCarouselSnippet", carouselHtmlSnippet );
	        	content.getSession().save();	
	        	System.out.println("READING THE PROPERTY HERE : " + prop.getPath() + " ... " + prop.getName());
				
			} catch (ValueFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (VersionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LockException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ConstraintViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return carouselHtmlSnippet;
		
	}
	
	
	   
		  public static final String stringToHtmlString(String s){
		       StringBuffer sb = new StringBuffer();
		       int n = s.length();
		       for (int i = 0; i < n; i++) {
		          char c = s.charAt(i);
		          switch (c) {
		             case '<': sb.append("&lt;"); break;
		             case '>': sb.append("&gt;"); break;
		             case '&': sb.append("&amp;"); break;
		             case '"': sb.append("&quot;"); break;
		             default:  sb.append(c); break;
		          }
		       }
		       return sb.toString();
		    }

		  
		  // use only in case of PREVIEW MODE in the FTL
		  public String getHtmlSnippetProperty(){
			  String hero_html_snippet ="";
			  try {
				Property prop_html = content.getProperty("heroCarouselSnippet");
				hero_html_snippet = prop_html.getValue().getString();
			} catch (PathNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  
			  if(! hero_html_snippet.isEmpty())
			  {
			  return hero_html_snippet;
			  } else { return "NO VALUE SET IN PROPERTY"; }
		  }
	
	
}