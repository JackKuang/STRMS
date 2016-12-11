package com.hurenjieee.util;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;


import com.hurenjieee.entity.Dictionary;
import com.hurenjieee.service.DictionaryService;

public class GlobalUtil {
	
	
	private static Global global;
	
	/**
	 * 初始化去全局参数
	 * @param servletContext
	 */
	public static void initGlobalToServlet(ServletContext servletContext){
		Map<String,String> map=new TreeMap<String,String>();
		// FIXME 对数据库数据字典的访问，写到ServletContext中
		DictionaryService dictionaryService = (DictionaryService) SpringContextUtil.getBean("dictionaryService");
		List<Dictionary> list=dictionaryService.getList();
		Global g =new Global();
		for(Dictionary dictionary:list){
			switch(dictionary.getValueType()){
			case "Integer":
				g.getIntMap().put(dictionary.getKey(), Integer.valueOf(dictionary.getValue()));
				break;
			case "Double":
				g.getDoubleMap().put(dictionary.getKey(), Double.valueOf(dictionary.getValue()));
				break;
			default:
				g.getStringMap().put(dictionary.getKey(), dictionary.getValue());				
			}
		}
		global=g;
		servletContext.setAttribute("Global", g);
	}
	
	public static Integer getIntValue(String key){
		return global.getIntMap().get(key);
	}
	
	public static Double getDoubleValue(String key){
		return global.getDoubleMap().get(key);
	}
	
	public static String getStringValue(String key){
		return global.getStringMap().get(key);
	}
}
