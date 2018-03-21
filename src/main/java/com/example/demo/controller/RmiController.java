package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.util.EhcaheUtil;

@RestController
public class RmiController {
	private final Log log = LogFactory.getLog(this.getClass());
	@RequestMapping("getCache1")
	public Object getCache(String data){
		Map<String, String> map = new HashMap<String,String>();
		try {
			EhcaheUtil ehcaheUtil = EhcaheUtil.getInstance();
			Object object = ehcaheUtil.get("userCache", "dataCache");
			if (object==null) {
				log.info("--->noCache<----save--->");
				ehcaheUtil.put("userCache", "dataCache",data);
				map.put("save new", "true");
			}else{
				log.info("--->has Cache<----is--->"+object);
				map.put("has cache is", object.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("It's Wrong!!!");
		}
		return map;
	}
	@RequestMapping("getCache2")
	public Object getCache2(String data){
		Map<String, String> map = new HashMap<String,String>();
		try {
			EhcaheUtil ehcaheUtil = EhcaheUtil.getInstance();
			Object object = ehcaheUtil.get("userCache", "dataCache");
			if (object==null) {
				log.info("--->noCache<----");
				map.put("no cache", "true");
			}else{
				log.info("--->has Cache<----is--->"+object);
				map.put("has cache is", object.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("It's Wrong!!!");
		}
		return map;
	}
}
