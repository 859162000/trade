/**
 * @Author lukangle
 * @2015年11月17日@上午9:44:43
 */
package com.hbc.api.trade.bdata.common.cache;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class TradeCacheManager {
	@Autowired
	private CacheManager cacheManager;

	public <T> List<T> getCacheList(String cacheName,String ckey){
		Cache guidCache = cacheManager.getCache(cacheName);
		Element valstEle = guidCache.get(ckey);
		
		if(valstEle==null){
			return null;
		}else{
			@SuppressWarnings("unchecked")
			List<T> ts = (List<T>)valstEle.getObjectValue();
			return ts;
		}
	}
	
	
	public <T> void putCacheList(String cacheName,String ckey,List<T> vlist){
		if(vlist!=null && vlist.size()>0){
			Cache guidCache = cacheManager.getCache(cacheName);

			Element valstEle = new Element(ckey,vlist);
			guidCache.put(valstEle);
		}
	}
	
	public <T> T getCacheBean(String cacheName,String ckey){
		Cache guidCache = cacheManager.getCache(cacheName);
		Element valstEle = guidCache.get(ckey);
		
		if(valstEle==null){
			return null;
		}else{
			@SuppressWarnings("unchecked")
			T ts = (T)valstEle.getObjectValue();
			return ts;
		}
	}
	
	
	public <T> void putCacheBean(String cacheName,String ckey,T tobj){
		if(tobj!=null){
			Cache guidCache = cacheManager.getCache(cacheName);
			Element valstEle = new Element(ckey,tobj);
			guidCache.put(valstEle);
		}
	}
	
}
