package com.example.util;



import java.io.FileNotFoundException;
import java.net.URL;

import org.springframework.util.ResourceUtils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


public class EhcaheUtil {
	private static final String path = "classpath:ehcache.xml";
	private URL url ;
	private CacheManager manager;
	private static EhcaheUtil ehcahe;
	private EhcaheUtil(String path) throws FileNotFoundException{
		url =ResourceUtils.getURL(path);
		System.out.println("url is-->"+url);
		manager = CacheManager.create(url);
	}
	public static EhcaheUtil getInstance() throws FileNotFoundException{
		if (ehcahe ==null) {
			ehcahe= new EhcaheUtil(path);
		}
		return ehcahe;
	}
	public void put (String cacheName,String key,Object value){
		Cache cache = manager.getCache(cacheName);
		Element element = new Element(key, value);
		cache.put(element);
	}
	public Object get(String cacheName,String key){
		Cache cache = manager.getCache(cacheName);
		Element element = cache.get(key);
		return element==null?null:element.getObjectValue();
	}
	public Cache get(String cacheName){
		return manager.getCache(cacheName);
	}
	public void remove(String cacheName,String key){
		Cache cache = manager.getCache(cacheName);
		cache.remove(key);
	}
}
