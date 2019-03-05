package com.holidu.interview.assignment.api.facade;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.holidu.interview.assignment.api.controller.TreesCountController;
import com.holidu.interview.assignment.api.domain.TreesCountRequest;
import com.holidu.interview.assignment.api.domain.TreesCountResponse;
import com.holidu.interview.assignment.api.domain.TreesData;
import com.holidu.interview.assignment.api.service.TreesCountService;
/**
 * 
 * @author Bhoomi Jivani
 *
 */

@Component
public class TreesCountFacade {
	
	private static final Logger LOG = LogManager.getLogger(TreesCountController.class);
	private static final String MODULE = "TreesCountFacade -->  ";
	
	@Autowired
	TreesCountService treesCountService;
	
	private double xCoordinate = 0.0;
	private double yCoordinate = 0.0;

	/**
	 * This method filters the tree data returned by service by radius and formats the data for response.
	 * @param treesCountRequest
	 * @return
	 */
	public TreesCountResponse filterTreesData(TreesCountRequest treesCountRequest){
		LOG.info(MODULE+"In Method: filterTreesData "+ treesCountRequest != null? treesCountRequest.toString():null);
		TreesCountResponse treesCountResponse = null;
		List<TreesData> treeDataList = treesCountService.getTreeData();
		
		if(treeDataList != null && !treeDataList.isEmpty() && treesCountRequest != null){
			//Radius converted to feet
			double radius = Double.parseDouble(treesCountRequest.getRadius()) * 3.281;
			LOG.debug(MODULE+"Radius:  "+ radius );
			
			String[] centerCoordinate = treesCountRequest.getCenterPoint().split(",");
			if(centerCoordinate != null && centerCoordinate.length > 1){
				//Center coordinates converted to x, y and in feet
				xCoordinate = Double.parseDouble(centerCoordinate[0]) * 3.281;
				yCoordinate = Double.parseDouble(centerCoordinate[1]) * 3.281;
				LOG.debug(MODULE+"Center Coordinates seperated:  "+ xCoordinate  +" , "+yCoordinate);
				
				List<String> filteredTreesName = treeDataList.stream()
													.filter(x -> x.getSpc_common() != null && rangeCalculation(xCoordinate,yCoordinate,Double.parseDouble(x.getX_sp()), Double.parseDouble(x.getY_sp())) <= radius)
													.map(x -> x.getSpc_common())
													.collect(Collectors.toList());
				
				LOG.info(MODULE+"filteredTreesName List:  "+ filteredTreesName != null ? filteredTreesName.toString(): null);
				
				//setting response object by identifying unique trees and its frequency.
				if(filteredTreesName != null){
					Set<String> uniqueNames = new HashSet<String>(filteredTreesName);
					LOG.debug(MODULE+"uniqueNames Set:  "+uniqueNames.toString());
					
					Map<String,Integer> nameCountMap = new HashMap<String,Integer>();
					treesCountResponse = new TreesCountResponse();
					for (String key : uniqueNames) {
						nameCountMap.put(key,Collections.frequency(filteredTreesName, key));
					}
					LOG.debug(MODULE+"nameCountMap:  "+ nameCountMap != null ? nameCountMap.toString(): null);
					
					treesCountResponse.setNameCountMap(nameCountMap);
				}
			}
		}
		LOG.info(MODULE+"Out Method: filterTreesData ");
		return treesCountResponse;
	}
	
	/**
	 * This method calculates the distance between center and given Cartesian point and returns it.
	 * @param xSpCenter
	 * @param ySpCenter
	 * @param xSpPoint
	 * @param ySpPoint
	 * @return
	 */
	public double rangeCalculation(Double xSpCenter, Double ySpCenter, Double xSpPoint, Double ySpPoint){
		LOG.info(MODULE+"In Method: rangeCalculation ");
		double xDiff = xSpCenter - xSpPoint;
		double yDiff = ySpCenter - ySpPoint;
		double distance = Math.sqrt((xDiff*xDiff)+(yDiff*yDiff));
		LOG.info(MODULE+"Out Method: rangeCalculation "+distance);
		return distance;
	}

	
}
