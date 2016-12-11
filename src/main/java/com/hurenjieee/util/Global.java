package com.hurenjieee.util;

import java.util.HashMap;
import java.util.Map;

public class Global {
	
	private Map<String,Integer> intMap;
	
	private Map<String,Double> doubleMap;
	
	private Map<String,String> stringMap;
	

	public Global() {
		super();
		intMap=new HashMap<String, Integer>();
		doubleMap=new HashMap<String, Double>();
		stringMap=new HashMap<String,String>();
	}

	public Map<String, Integer> getIntMap() {
		return intMap;
	}

	public void setIntMap(Map<String, Integer> intMap) {
		this.intMap = intMap;
	}

	public Map<String, Double> getDoubleMap() {
		return doubleMap;
	}

	public void setDoubleMap(Map<String, Double> doubleMap) {
		this.doubleMap = doubleMap;
	}

	public Map<String, String> getStringMap() {
		return stringMap;
	}

	public void setStringMap(Map<String, String> stringMap) {
		this.stringMap = stringMap;
	}	
	
}
