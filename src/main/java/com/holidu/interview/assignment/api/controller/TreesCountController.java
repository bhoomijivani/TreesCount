package com.holidu.interview.assignment.api.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.holidu.interview.assignment.api.domain.TreesCountRequest;
import com.holidu.interview.assignment.api.domain.TreesCountResponse;
import com.holidu.interview.assignment.api.facade.TreesCountFacade;

/**
 * 
 * @author Bhoomi Jivani
 *
 */

@RestController
public class TreesCountController {
	
	@Autowired
	TreesCountFacade treesCountFacade;
	
	private static final Logger LOG = LogManager.getLogger(TreesCountController.class);
	private static final String MODULE = "TreesCountController -->  ";

	/**
	 * This method validates and calls the facade method for filtering data based on request. 
	 * @param treesCountRequest
	 * @return
	 */
	@RequestMapping(name = "treesCountEndpoint",method = RequestMethod.POST,value = "/treesCounter")
	//@ExceptionHandler({ SQLException.class, Exception.class })
	@ResponseBody
	public ResponseEntity<Object>  treesCounter(@RequestBody TreesCountRequest treesCountRequest) {
		LOG.info(MODULE+"In Method: treesCounter"+ treesCountRequest != null? treesCountRequest.toString():null);
		ResponseEntity<Object> responseEntity = null;
		
		try{
			//validate request
			boolean validateTreesData = validateTreesData(treesCountRequest);
			
			if(validateTreesData){
				//calling filter method to get data and filter it.
				TreesCountResponse filterTreesData = treesCountFacade.filterTreesData(treesCountRequest);
				if(filterTreesData != null){
					responseEntity = new ResponseEntity<Object>(filterTreesData.getNameCountMap(), HttpStatus.OK);
				}else{
					responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			
		}catch(Exception e){
			responseEntity = new ResponseEntity<Object>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		LOG.info(MODULE+"Out Method: treesCounter");
        return responseEntity;
    }
	
	/**
	 * Validate the request
	 * @param treesCountRequest
	 * @return
	 * @throws Exception
	 */
	public boolean validateTreesData(TreesCountRequest treesCountRequest) throws Exception{
		LOG.info(MODULE+"In Method: validateTreesData"+ treesCountRequest != null? treesCountRequest.toString():null);
		
		boolean validRequest = false;
		
		if(treesCountRequest == null ||((treesCountRequest.getCenterPoint() == null || treesCountRequest.getCenterPoint().isEmpty()) && (treesCountRequest.getRadius()==null || treesCountRequest.getRadius().isEmpty()))){
			throw new Exception("Request must contain both radius and center point.");
		}
		
		if(treesCountRequest.getRadius() == null || treesCountRequest.getRadius().length() <= 0){
			throw new Exception("Radius cannot be left blank.");
		}
		
		if(treesCountRequest.getCenterPoint() == null || treesCountRequest.getCenterPoint().length() <= 0){
			throw new Exception("Center point cannot be left blank.");
		}
		
		if(!treesCountRequest.getCenterPoint().contains(",")){
			throw new Exception("Center point must contain both x and y coordinates.");
		}
		
		 try {
	            Double.parseDouble(treesCountRequest.getRadius());
	      } catch (NumberFormatException e) {
	        	throw new Exception("Radius must be a number.");
	      }
		 
		 try {
			 String[] split = treesCountRequest.getCenterPoint().split(",");
	            Double.parseDouble(split[0]);
	            Double.parseDouble(split[01]);
	      } catch (NumberFormatException e) {
	        	throw new Exception("Both x and y coordinates of center point must be a number.");
	     }
		 validRequest = true;
		 
		 LOG.info(MODULE+"Out Method: validateTreesData"+ treesCountRequest != null? treesCountRequest.toString():null);
		return validRequest;
	}
}
