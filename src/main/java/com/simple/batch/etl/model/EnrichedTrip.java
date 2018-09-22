package com.simple.batch.etl.model;

public class EnrichedTrip implements Model{
	private String routeId;
	private String serviceId;
	private String tripId;
	private String tripHeadsign;
	private String wheelchairAccessible;
	private String shapeId;
	private String noteFr;
	private String noteEn;
	private String date;
	private String exceptionType;
	private String startTime;
	private String endTime;
	private String headwaySecs;
	
	public EnrichedTrip() {
		
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public String getTripHeadsign() {
		return tripHeadsign;
	}

	public void setTripHeadsign(String tripHeadsign) {
		this.tripHeadsign = tripHeadsign;
	}

	public String getWheelchairAccessible() {
		return wheelchairAccessible;
	}

	public void setWheelchairAccessible(String wheelchairAccessible) {
		this.wheelchairAccessible = wheelchairAccessible;
	}

	public String getShapeId() {
		return shapeId;
	}

	public void setShapeId(String shapeId) {
		this.shapeId = shapeId;
	}

	public String getNoteFr() {
		return noteFr;
	}

	public void setNoteFr(String noteFr) {
		this.noteFr = noteFr;
	}

	public String getNoteEn() {
		return noteEn;
	}

	public void setNoteEn(String noteEn) {
		this.noteEn = noteEn;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
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
