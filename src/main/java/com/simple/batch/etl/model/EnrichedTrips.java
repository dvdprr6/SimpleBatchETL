package com.simple.batch.etl.model;

public class EnrichedTrips implements Model{
	private Trips trip;
	private CalendarDates calendarDates;
	private Frequencies frequencies;
	
	public EnrichedTrips() {
		
	}
	
	public EnrichedTrips(Trips trip, CalendarDates calendarDates, Frequencies frequencies) {
		this.trip = trip;
		this.calendarDates = calendarDates;
		this.frequencies = frequencies;
	}
	

	public Trips getTrip() {
		return trip;
	}

	public void setTrip(Trips trip) {
		this.trip = trip;
	}

	public CalendarDates getCalendarDates() {
		return calendarDates;
	}

	public void setCalendarDates(CalendarDates calendarDates) {
		this.calendarDates = calendarDates;
	}

	public Frequencies getFrequencies() {
		return frequencies;
	}

	public void setFrequencies(Frequencies frequencies) {
		this.frequencies = frequencies;
	}
}
