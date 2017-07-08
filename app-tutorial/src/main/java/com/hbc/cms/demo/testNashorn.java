package com.hbc.cms.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang3.StringEscapeUtils;

public class testNashorn {

	public static void main( String args[]){

		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("nashorn");
		String jsFile = "";
 
		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader("//Users//vcharles2//Desktop//framework_commons.js");
			br = new BufferedReader(fr);

			String sCurrentLine;
			
			br = new BufferedReader(new FileReader("//Users//vcharles2//Desktop//framework_commons.js"));

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				//jsFile = jsFile.concat(sCurrentLine);	
				jsFile.concat(sCurrentLine);
			}
			System.out.println("Printed JS file");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}
		
		
	//	String js = "print('Hello World!');";
		
		try {
			System.out.println("Eval JS file");
			engine.eval(jsFile);
			
	//		engine.eval("var window = this;");     // workaround
	//		engine.eval("load('https://cdnjs.cloudflare.com/ajax/libs/react/0.12.2/react.js')");
			
			String reactBundleUrl = "http://content.hbc.com/content/frontend/4-3-0/scripts/framework.js";
            String nashornPolyfillUrl = "http://content.hbc.com/content/frontend/4-3-0/scripts/nashorn-polyfill.js";			
			String compName = "SubFooter";
			String nashornPolyfillBundle = "";
			String reactJsBundle = "";
			
	//compName is the component name as per framework_commons.js ex: SubFooter
	// the component data in Json format + escaped		
		String	JSONString = testComponentModel.getJson("http://csione-test.us-east-1.elasticbeanstalk.com/navbobulator/subfooter.json");

		String render = null;	 
		String escapedJSON = StringEscapeUtils.escapeEcmaScript(JSONString);	
      render=  "framework.renderComponentToString(\"" + compName + "\", JSON.parse('" + escapedJSON + "')" + ");";
  	System.out.println("got rendere");

		nashornPolyfillBundle = testComponentModel.getJson(nashornPolyfillUrl);
		reactJsBundle = testComponentModel.getJson(reactBundleUrl);

		engine.eval(nashornPolyfillBundle);
	  	System.out.println("eval polyfill");

		engine.eval(reactJsBundle);
	  	System.out.println("eval reactJs");

	  	System.out.println("print render : " + render);

	String evaluatedString = engine.eval(render).toString();
	System.out.println("evaluated String : "+ evaluatedString);
		
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

