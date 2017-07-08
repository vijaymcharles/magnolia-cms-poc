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

public class NashornDebug {

	static NashornEval nashornEval;

	final static Logger logger = LoggerFactory.getLogger(NashornDebug.class);

	public static void main(String[] args) {

		// String constant for framework js
		//final String FRAMEWORK_JS = "framework.js";
		final String FRAMEWORK_JS = "framework_new.js";

		//final String FRAMEWORK_JS = "http://content.hbc.com/content/frontend/4-3-0/scripts/framework.js"; 
		// String constant for support js
		//final String SUPPORT_JS = "support.js";
		final String SUPPORT_JS = "nashorn_polyfill.js";
		
		// String constant for component json
		final String DATA_JSON = "carousel_vijay.json";

		// String constant for component name to be passed
		final String COMPONENT_NAME = "EditorialTabbedHeroCarousel";

		// instantiating the Nashorn Eval class by invoking constructor
		nashornEval = new NashornEval(FRAMEWORK_JS, SUPPORT_JS);

		//calling eval method of Nashorn Eval class
		Object result = nashornEval.eval(DATA_JSON, COMPONENT_NAME);
		logger.info("result for" + COMPONENT_NAME + "and input json"
				+ DATA_JSON + "is :" + result);

	}

}
