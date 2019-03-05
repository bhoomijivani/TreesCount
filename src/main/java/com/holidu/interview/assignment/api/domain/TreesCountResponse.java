package com.holidu.interview.assignment.api.domain;

import java.util.Map;
/**
 * 
 * @author Bhoomi Jivani
 *
 */
public class TreesCountResponse {
	private Map<String,Integer> namesCountMap;

	public Map<String,Integer> getNameCountMap() {
		return namesCountMap;
	}

	public void setNameCountMap(Map<String,Integer> nameCountMap) {
		this.namesCountMap = nameCountMap;
	}

	@Override
	public String toString() {
		return "TreesCountResponse [nameCountMap=" + namesCountMap + "]";
	}
	
}
