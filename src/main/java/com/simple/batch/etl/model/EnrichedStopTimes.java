package com.simple.batch.etl.model;

public class EnrichedStopTimes implements Model{
	private EnrichedTrips enrichedTrips;
	private StopTimes stopTimes;
	
	public EnrichedStopTimes() {
		
	}
	
	public EnrichedStopTimes(StopTimes stopTimes, EnrichedTrips enrichedTrips){
		this.stopTimes = stopTimes;
		this.enrichedTrips = enrichedTrips;
	}

	public EnrichedTrips getEnrichedTrips() {
		return enrichedTrips;
	}

	public void setEnrichedTrips(EnrichedTrips enrichedTrips) {
		this.enrichedTrips = enrichedTrips;
	}

	public StopTimes getStopTimes() {
		return stopTimes;
	}

	public void setStopTimes(StopTimes stopTimes) {
		this.stopTimes = stopTimes;
	}
	
	

}
