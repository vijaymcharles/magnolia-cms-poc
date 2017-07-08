/*
 * ===========================================================================
 *
 * ScriptUtils.java
 *
 * Created on Sep 17, 2015
 *
 * Copyright 2015, HBC-Saks, IEA, SapientNitro;  All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * HBC-Saks, IEA, SapientNitro, ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with HBC-Saks, SapientNitro.
 * ===========================================================================
 */
package com.hbc.cms.demo.nashorn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.Reader;
import java.io.FileNotFoundException;

public class ScriptUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(ScriptUtils.class);
	
	/**
	 * This method is used to get the response created by react script
	 * 
	 * @param reader
	 *            -instance of InputStreamReader.
	 * @return -returns contents of resource file as a string
	 */
	public static String getScriptAsString(Reader reader) throws IOException {
	    logger.info(" Entering getScriptAsString ... ");
		BufferedReader breader = new BufferedReader(reader);
		StringBuilder out = new StringBuilder();
		String line;
		while ((line = breader.readLine()) != null) {
			out.append(line);
			//logger.info(line);
		}
		reader.close();
		logger.info(" reader has been closed ... ");
		return out.toString();
	}
	
	/**
	 * This method is used to get the InputStream Reader instance
	 * 
	 * @param filePath
	 *            -local path for resource.
	 * @return -returns an instance of InputStreamReader
	 */
    public static Reader getReader(String filePath) throws FileNotFoundException,IOException {
    
    	System.out.println("before getting reader" + filePath);
    	
    	InputStreamReader isReader = new InputStreamReader(ScriptUtils.class.getClassLoader().getResourceAsStream(filePath));
    	
    	System.out.println("dunno what - " + isReader.toString());
     	return isReader;
    	
    }
    
}

