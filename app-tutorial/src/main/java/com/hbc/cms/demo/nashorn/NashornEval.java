/*
 * ===========================================================================
 *
 * NashornEval.java
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

import java.io.IOException;
import java.io.Reader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class NashornEval {

	// reference to Nashorn script engine
	private ScriptEngine nashorn;

	// constructor to initialize script engine and load required files
	protected NashornEval(String reactString, String supportString) {

		// Initialize Nashorn
		nashorn = new ScriptEngineManager().getEngineByName("nashorn");
		// load the framwork and support js files
		evalScript(nashorn, supportString);
		evalScript(nashorn, reactString);

	}

	/**
	 * This method is used to get the response created by react script
	 * 
	 * @param componentFilePath
	 *            -local path for component json file.
	 * @param compName
	 *            -component Name.
	 * @return -returns a html object created by react script
	 */
	public Object eval(String componentFilePath, String compName) {

		String componentString = "";
		Object finalResult = null;

		try {

			Reader componentReader = ScriptUtils.getReader(componentFilePath);
			componentString = ScriptUtils.getScriptAsString(componentReader);

			// String componentNameList = "framework.getComponentNameList()";
			String componentRender = "framework.renderComponentToString(\""
					+ compName + "\", JSON.parse('" + componentString + "')"
					+ ");";
			finalResult = nashorn.eval(componentRender);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ScriptException ex) {
			ex.printStackTrace();
		} finally {
			return finalResult;
		}

	}

	/**
	 * This is a helper method to load js files into nashorn.
	 * 
	 * @param nashorn
	 *            -instance of script engine
	 * @param filePath
	 *            -String for holding local file resoruce path.
	 */
	private void evalScript(ScriptEngine nashorn, String filePath) {

		try {
			Reader fileReader = ScriptUtils.getReader(filePath);
			String evalString = ScriptUtils.getScriptAsString(fileReader);
			nashorn.eval(evalString);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ScriptException ex) {
			ex.printStackTrace();
		}

	}
	
	
	public Object evalNew(String componentDataStr, String compName) {

	//	String componentString = "";
		Object finalResult = null;

		try {

	//		Reader componentReader = ScriptUtils.getReader(componentFilePath);
	//		componentString = ScriptUtils.getScriptAsString(componentReader);
			// String componentNameList = "framework.getComponentNameList()";
			String componentRender = "framework.renderComponentToString(\""
					+ compName + "\", JSON.parse('" + componentDataStr + "')"
					+ ");";
			finalResult = nashorn.eval(componentRender);
			
		} catch (ScriptException ex) {
			ex.printStackTrace();
		} finally {
			return finalResult;
			
		}

	}
	
	
	
}
