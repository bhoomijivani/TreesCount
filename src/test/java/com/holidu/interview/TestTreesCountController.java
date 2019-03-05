package com.holidu.interview;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.holidu.interview.assignment.api.controller.TreesCountController;
import com.holidu.interview.assignment.api.domain.TreesCountRequest;



public class TestTreesCountController {
	
	@InjectMocks
	TreesCountController treesCountController;

	private MockMvc mockMvc;
	 
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(treesCountController).build();
    }
	
    @Test
	public void validateTreesDataRequestShouldNotBeNull() throws Exception{
		TreesCountRequest treesCountRequest = new TreesCountRequest();
		treesCountRequest.setCenterPoint("123.34,2312.42");
		treesCountRequest.setRadius("123.3");
		assertTrue(treesCountController.validateTreesData(treesCountRequest));
	}
    
	@Test
	public void validateTreesDataRadiusShouldNotBeNull() throws Exception{
		TreesCountRequest treesCountRequest = new TreesCountRequest();
		treesCountRequest.setCenterPoint("123.34,2312.42");
		treesCountRequest.setRadius("123.3");
		assertTrue(treesCountController.validateTreesData(treesCountRequest));
	}
	
	@Test
	public void validateTreesDataCenterPointShouldNotBeNull() throws Exception{
		TreesCountRequest treesCountRequest = new TreesCountRequest();
		treesCountRequest.setCenterPoint("123.34,2312.42");
		treesCountRequest.setRadius("123.3");
		assertTrue(treesCountController.validateTreesData(treesCountRequest));
	}
	
	@Test
	public void validateTreesDataCenterPointShouldHaveBothCoordinates() throws Exception{
		TreesCountRequest treesCountRequest = new TreesCountRequest();
		treesCountRequest.setCenterPoint("12,312.42");
		treesCountRequest.setRadius("123.3");
		assertTrue(treesCountController.validateTreesData(treesCountRequest));
	}
	
	@Test
	public void validateTreesDataCenterPointShouldBeNumber() throws Exception{
		TreesCountRequest treesCountRequest = new TreesCountRequest();
		treesCountRequest.setCenterPoint("123,456");
		treesCountRequest.setRadius("123.3");
		assertTrue(treesCountController.validateTreesData(treesCountRequest));
	}
	
	@Test
	public void validateTreesDataRadiusShouldBeNumber() throws Exception{
		TreesCountRequest treesCountRequest = new TreesCountRequest();
		treesCountRequest.setCenterPoint("123,456");
		treesCountRequest.setRadius("453");
		assertTrue(treesCountController.validateTreesData(treesCountRequest));
	}
	
}
