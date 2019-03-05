package com.holidu.interview;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.holidu.interview.assignment.api.facade.TreesCountFacade;

public class TestTreesCountFacade {
	
	@InjectMocks
	TreesCountFacade treesCountFacade;
	
	private MockMvc mockMvc;
	 
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(treesCountFacade).build();
    }
	
	@Test
	public void validateTreesDataRequestShouldNotBeNull() throws Exception{
		Double xSpCenter=5.0, ySpCenter=5.0, xSpPoint=3.0, ySpPoint=3.0;
		assertEquals(3.0,treesCountFacade.rangeCalculation(xSpCenter, ySpCenter, xSpPoint,ySpPoint),1.00);
	}
}
