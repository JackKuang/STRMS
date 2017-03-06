package com.hurenjieee.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

public class GlobalUtil {

    /**
     * 初始化去全局参数
     * @param servletContext
     */
    //从数据获取到相对应的数据
//    public static void initGlobalToServlet(ServletContext servletContext){
//        new TreeMap<String, String>();
//        // FIXME 对数据库数据字典的访问，写到ServletContext中
//        DictionaryService dictionaryService = (DictionaryService) SpringContextUtil.getBean("dictionaryService");
//        List<Dictionary> list = dictionaryService.getList();
//        Global g = new Global();
//        for ( Dictionary dictionary : list ) {
//            switch (dictionary.getValueType()) {
//                case "Integer":
//                    g.getIntMap().put(dictionary.getKey(),Integer.valueOf(dictionary.getValue()));
//                    break;
//                case "Double":
//                    g.getDoubleMap().put(dictionary.getKey(),Double.valueOf(dictionary.getValue()));
//                    break;
//                default:
//                    g.getStringMap().put(dictionary.getKey(),dictionary.getValue());
//            }
//        }
//        global = g;
//        servletContext.setAttribute("Global",g);
//    }
//
//    public static Integer getIntValue(String key){
//        return global.getIntMap().get(key);
//    }
//
//    public static Double getDoubleValue(String key){
//        return global.getDoubleMap().get(key);
//    }
//
//    public static String getStringValue(String key){
//        return global.getStringMap().get(key);
//    }
    public static void loadSystemProperties(ServletContext servletContext){
    	Properties properties = new Properties();
    	Map<String, String> stringMap = new HashMap<String, String>();
    	try {
    		String filePath= servletContext.getRealPath("\\")+"\\WEB-INF\\classes\\systemConfig.properties";
    		InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath));
			properties.load(inputStream);
			Iterator<String> iterator = properties.stringPropertyNames().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				stringMap.put(key, properties.getProperty(key));
			}
			servletContext.setAttribute("prop",stringMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
