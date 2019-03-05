package com.holidu.interview.assignment.api.service;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.holidu.interview.assignment.api.controller.TreesCountController;
import com.holidu.interview.assignment.api.domain.TreesData;

/**
 * 
 * @author Bhoomi Jivani
 *
 */

@Service
public class TreesCountService {
	private static final Logger LOG = LogManager.getLogger(TreesCountController.class);
	private static final String MODULE = "TreesCountService -->  ";

	/**
	 * This method acts as a client and calls another service to get tree data, converts it into list of objects and returns.
	 * @return
	 */
	public List<TreesData> getTreeData(){
		LOG.info(MODULE+"In Method: getTreeData ");
	
		List<TreesData> treeDataList = null;
		
		try {
			 final String uri = "https://data.cityofnewyork.us/resource/nwxe-4ae8.json";
			 LOG.debug(MODULE+"Calling web service");
			 RestTemplate restTemplate = new RestTemplate();
			 String result = restTemplate.getForObject(uri, String.class);
			 LOG.debug(MODULE+"Response from web service: "+result);
			 ObjectMapper objectMapper = new ObjectMapper();
			 TreesData[] treesDataArray = objectMapper.readValue(result, TreesData[].class);
			 LOG.debug(MODULE+"Response converted to array: "+treesDataArray != null?treesDataArray:null);
				
			 if(treesDataArray != null){
				 treeDataList = Arrays.asList(treesDataArray);
			 }
			 
			 LOG.debug(MODULE+"Response converted to object: "+treeDataList != null?treeDataList.toString():null);
		} catch (Exception e){
			LOG.error(MODULE+"Exception ");
			e.printStackTrace();
		}
		
		LOG.info(MODULE+"Out Method: getTreeData ");
		return treeDataList;
	}
	
}
