/*
 * ===========================================================================
 *
 * NashornDebug.java
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


/*
 * ===========================================================================
 *
 * NashornDebug.java
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

import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NashornDebugModel {

	static NashornEval nashornEval;
	final static Logger logger = LoggerFactory.getLogger(NashornDebugModel.class);
	
	
	public NashornDebugModel(){

		final String FRAMEWORK_JS = "/HbcCmsDemo/webresources/framework_new.js";
		final String SUPPORT_JS = "/HbcCmsDemo/webresources/nashorn_polyfill.js";

		System.out.println(" NashornDebugModel - Called");		
		// instantiating the Nashorn Eval class by invoking constructor
		nashornEval = new NashornEval(FRAMEWORK_JS, SUPPORT_JS);
	}
	
	public String carouselHtmlSnippet(String COMPONENT_NAME, String finalJSON){
		
		// String constant for component json
				final String DATA_JSON = "/HbcCmsDemo/webresources/carousel_vijay.json";
		System.out.println(" NashornDebugModel - Called 2 : " + finalJSON);		
		//calling eval method of Nashorn Eval class
		Object result = nashornEval.evalNew(finalJSON, COMPONENT_NAME);
	
		logger.info("result for" + COMPONENT_NAME + "and input json"
				+ DATA_JSON + "is :" + result);

		return result.toString();
	}
}
