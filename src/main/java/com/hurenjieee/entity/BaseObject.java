package com.hurenjieee.entity;

import java.util.Map;

public class BaseObject implements java.io.Serializable {

	private Map<String, Integer> intMap;

	private Map<String, String> strMap;

	private Map<String, Double> douMap;

	public Map<String, Integer> getIntMap() {
		return intMap;
	}

	public void setIntMap(Map<String, Integer> intMap) {
		this.intMap = intMap;
	}

	public Map<String, String> getStrMap() {
		return strMap;
	}

	public void setStrMap(Map<String, String> strMap) {
		this.strMap = strMap;
	}

	public Map<String, Double> getDouMap() {
		return douMap;
	}

	public void setDouMap(Map<String, Double> douMap) {
		this.douMap = douMap;
	}

}
