package com.example.demo;

import java.io.FileNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.example.util.EhcaheUtil;

public class Test {
	private final static Log log = LogFactory.getLog(Test.class);

	public static void main(String[] args) {
		EhcaheUtil ehcaheUtil = null;
		try {
			ehcaheUtil = EhcaheUtil.getInstance();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		log.info("--->noCache<----save--->");
		ehcaheUtil.put("dateCache", "dataCache", "1234");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object object = ehcaheUtil.get("dateCache", "dataCache");
		log.info("result is -->" + object);
	}
}
