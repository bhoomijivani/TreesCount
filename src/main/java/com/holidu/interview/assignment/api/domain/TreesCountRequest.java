package com.holidu.interview.assignment.api.domain;
/**
 * 
 * @author Bhoomi Jivani
 *
 */
public class TreesCountRequest {
	private String radius;
	private String centerPoint;
	
	public String getRadius() {
		return radius;
	}
	public void setRadius(String radius) {
		this.radius = radius;
	}
	
	public String getCenterPoint() {
		return centerPoint;
	}
	public void setCenterPoint(String centerPoint) {
		this.centerPoint = centerPoint;
	}
	
	@Override
	public String toString() {
		return "TreesCountRequest [radius=" + radius + ", centerPoint="
				+ centerPoint + "]";
	}
	
}
