package com.simple.batch.etl.model;

public class Frequencies {
	private String tripId;
	private String startTime;
	private String endTime;
	private String headwaySecs;
	
	public Frequencies() {
		
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getHeadwaySecs() {
		return headwaySecs;
	}

	public void setHeadwaySecs(String headwaySecs) {
		this.headwaySecs = headwaySecs;
	}
}
