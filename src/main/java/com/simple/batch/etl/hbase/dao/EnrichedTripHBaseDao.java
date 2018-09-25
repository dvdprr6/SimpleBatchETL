package com.simple.batch.etl.hbase.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import com.simple.batch.etl.connection.HBaseConnection;
import com.simple.batch.etl.model.CalendarDates;
import com.simple.batch.etl.model.EnrichedTrips;
import com.simple.batch.etl.model.Frequencies;
import com.simple.batch.etl.model.Trips;
import com.simple.batch.etl.utils.Constants;

public class EnrichedTripHBaseDao implements HBaseDao<EnrichedTrips>{
	private Table table;
	
	EnrichedTripHBaseDao(){
		try {
			this.table = HBaseConnection.getHBaseConnection().getTable(TableName.valueOf(Constants.HBASE_TRIP_TABLE));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insert(List<EnrichedTrips> model) {
		List<Put> enrichedTripPuts = new ArrayList<Put>();
		
		for(EnrichedTrips enrichedTrip : model) {
			Put put = new Put(Bytes.toBytes(enrichedTrip.getTrip().getTripId()));
			
			put = putIntoTripColumnFamily(enrichedTrip, put);
			put = putIntoCalendarDateColumnFamily(enrichedTrip, put);
			put = putIntoFrequenciesColumnFamily(enrichedTrip, put);
			
			enrichedTripPuts.add(put);
			
		}
		
		try {
			table.put(enrichedTripPuts);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public EnrichedTrips getById(String id) {
		Result result = null;
		EnrichedTrips enrichedTrips = new EnrichedTrips();
		
		Get get = new Get(Bytes.toBytes(id));
		
		try {
			result = table.get(get);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		Trips trips = getTripsColumnFamily(result);
		CalendarDates calendarDates = getCalendarDatesColumnFamily(result);
		Frequencies frequencies = getFrequenciesColumnFamily(result);
		
		enrichedTrips.setTrip(trips);
		enrichedTrips.setCalendarDates(calendarDates);
		enrichedTrips.setFrequencies(frequencies);
		
		return enrichedTrips;
	}
	
	private Put putIntoTripColumnFamily(EnrichedTrips enrichedTrip, Put put) {
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_QUALIFIER_ROUTE_ID), 
				Bytes.toBytes(enrichedTrip.getTrip().getRouteId()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_SERVICE_ID), 
				Bytes.toBytes(enrichedTrip.getTrip().getServiceId()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_ID), 
				Bytes.toBytes(enrichedTrip.getTrip().getTripId()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_HEADSIGN), 
				Bytes.toBytes(enrichedTrip.getTrip().getTripHeadsign()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_WHEELCHAIR_ACCESSIBLE), 
				Bytes.toBytes(enrichedTrip.getTrip().getWheelchairAccessible()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_SHAPE_ID), 
				Bytes.toBytes(enrichedTrip.getTrip().getShapeId()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_NOTE_FR), 
				Bytes.toBytes(enrichedTrip.getTrip().getNoteFr()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_NOTE_EN), 
				Bytes.toBytes(enrichedTrip.getTrip().getNoteEn()));
		
		return put;
	}
	
	private Put putIntoCalendarDateColumnFamily(EnrichedTrips enrichedTrip, Put put) {
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_CALENDAR_DATES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_SERVICE_ID), 
				Bytes.toBytes(enrichedTrip.getCalendarDates().getServiceId()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_CALENDAR_DATES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_DATE), 
				Bytes.toBytes(enrichedTrip.getCalendarDates().getDate()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_CALENDAR_DATES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_EXCEPTION_TYPE), 
				Bytes.toBytes(enrichedTrip.getCalendarDates().getExceptionType()));
		
		return put;
	}
	
	private Put putIntoFrequenciesColumnFamily(EnrichedTrips enrichedTrip, Put put) {
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_FREQUENCIES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_ID), 
				Bytes.toBytes(enrichedTrip.getFrequencies().getTripId()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_FREQUENCIES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_START_TIME), 
				Bytes.toBytes(enrichedTrip.getFrequencies().getStartTime()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_FREQUENCIES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_END_TIME), 
				Bytes.toBytes(enrichedTrip.getFrequencies().getEndTime()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_FREQUENCIES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_HEADWAY_SECS), 
				Bytes.toBytes(enrichedTrip.getFrequencies().getHeadwaySecs()));
		
		return put;
	}
	
	private Trips getTripsColumnFamily(Result result) {
		Trips trips = new Trips();
		String routeId;
		String serviceId;
		String tripId;
		String tripHeadsign;
		String wheelchairAccessible;
		String shapeId;
		String noteFr;
		String noteEn;
		
		try {
			routeId = new String(result.getValue(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_QUALIFIER_ROUTE_ID)));
		}catch(Exception e) {
			routeId = "";
		}
		
		try {
			serviceId = new String(result.getValue(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_SERVICE_ID)));
		}catch(Exception e) {
			serviceId = "";
		}
		
		try {
			tripId = new String(result.getValue(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_ID)));
		}catch(Exception e) {
			tripId = "";
		}
		
		try {
			tripHeadsign = new String(result.getValue(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_ID)));
		}catch(Exception e) {
			tripHeadsign = "";
		}
		
		try {
			wheelchairAccessible = new String(result.getValue(
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_WHEELCHAIR_ACCESSIBLE), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_ID)));
		}catch(Exception e) {
			wheelchairAccessible = "";
		}
		
		try {
			shapeId = new String(result.getValue(
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_WHEELCHAIR_ACCESSIBLE), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_SHAPE_ID)));
		}catch(Exception e) {
			shapeId = "";
		}
		
		try {
			noteFr = new String(result.getValue(
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_WHEELCHAIR_ACCESSIBLE), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_NOTE_FR)));
		}catch(Exception e) {
			noteFr = "";
		}
		
		try {
			noteEn = new String(result.getValue(
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_WHEELCHAIR_ACCESSIBLE), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_NOTE_EN)));
		}catch(Exception e) {
			noteEn = "";
		}
		
		trips.setRouteId(routeId);
		trips.setServiceId(serviceId);
		trips.setTripId(tripId);
		trips.setTripHeadsign(tripHeadsign);
		trips.setWheelchairAccessible(wheelchairAccessible);
		trips.setShapeId(shapeId);
		trips.setNoteFr(noteFr);
		trips.setNoteEn(noteEn);
		
		return trips;
		
	}
	
	private CalendarDates getCalendarDatesColumnFamily(Result result) {
		CalendarDates calendarDates = new CalendarDates();
		String serviceId;
		String date;
		String exceptionType;
		
		try {
			serviceId = new String(result.getValue(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_CALENDAR_DATES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_SERVICE_ID)));
		}catch(Exception e) {
			serviceId = "";
		}
		
		try {
			date = new String(result.getValue(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_CALENDAR_DATES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_DATE)));
		}catch(Exception e) {
			date = "";
		}
		
		try {
			exceptionType = new String(result.getValue(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_CALENDAR_DATES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_EXCEPTION_TYPE)));
		}catch(Exception e) {
			exceptionType = "";
		}
		
		calendarDates.setServiceId(serviceId);
		calendarDates.setDate(date);
		calendarDates.setExceptionType(exceptionType);
		
		return calendarDates;
	}
	
	private Frequencies getFrequenciesColumnFamily(Result result) {
		Frequencies frequencies = new Frequencies();
		String tripId;
		String startTime;
		String endTime;
		String headwaySecs;
		
		try {
			tripId = new String(result.getValue(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_FREQUENCIES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_ID)));
		}catch(Exception e) {
			tripId = "";
		}
		
		try {
			startTime = new String(result.getValue(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_FREQUENCIES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_START_TIME)));
		}catch(Exception e) {
			startTime = "";
		}
		
		try {
			endTime = new String(result.getValue(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_FREQUENCIES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_END_TIME)));
		}catch(Exception e) {
			endTime = "";
		}
		
		try {
			headwaySecs = new String(result.getValue(
					Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_FREQUENCIES), 
					Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_HEADWAY_SECS)));
		}catch(Exception e) {
			headwaySecs = "";
		}
		
		frequencies.setTripId(tripId);
		frequencies.setStartTime(startTime);
		frequencies.setEndTime(endTime);
		frequencies.setHeadwaySecs(headwaySecs);
		
		return frequencies;
		
	}
	
}
