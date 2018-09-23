package com.simple.batch.etl.hbase.dao;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import com.simple.batch.etl.connection.HBaseConnection;
import com.simple.batch.etl.model.EnrichedTrip;
import com.simple.batch.etl.utils.Constants;

public class EnrichedTripHBaseDao implements HBaseDao<EnrichedTrip>{
	private Table table;
	
	EnrichedTripHBaseDao(){
		try {
			this.table = HBaseConnection.getHBaseConnection().getTable(TableName.valueOf(Constants.HBASE_TRIP_TABLE));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void insert(List<EnrichedTrip> model) {
		long rowNumber = 0;
		List<Put> enrichedTripPuts = new ArrayList<Put>();
		
		for(EnrichedTrip enrichedTrip : model) {
			Put put = new Put(Bytes.toBytes("row-" + rowNumber++));
			
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
	
	
	private Put putIntoTripColumnFamily(EnrichedTrip enrichedTrip, Put put) {
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_QUALIFIER_ROUTE_ID), 
				Bytes.toBytes(enrichedTrip.getRouteId()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_SERVICE_ID), 
				Bytes.toBytes(enrichedTrip.getServiceId()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_ID), 
				Bytes.toBytes(enrichedTrip.getTripId()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_HEADSIGN), 
				Bytes.toBytes(enrichedTrip.getTripHeadsign()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_WHEELCHAIR_ACCESSIBLE), 
				Bytes.toBytes(enrichedTrip.getWheelchairAccessible()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_SHAPE_ID), 
				Bytes.toBytes(enrichedTrip.getShapeId()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_NOTE_FR), 
				Bytes.toBytes(enrichedTrip.getNoteFr()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_TRIP), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_NOTE_EN), 
				Bytes.toBytes(enrichedTrip.getNoteEn()));
		
		return put;
	}
	
	private Put putIntoCalendarDateColumnFamily(EnrichedTrip enrichedTrip, Put put) {
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_CALENDAR_DATES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_SERVICE_ID), 
				Bytes.toBytes(enrichedTrip.getServiceId()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_CALENDAR_DATES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_DATE), 
				Bytes.toBytes(enrichedTrip.getDate()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_CALENDAR_DATES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_EXCEPTION_TYPE), 
				Bytes.toBytes(enrichedTrip.getExceptionType()));
		
		return put;
	}
	
	private Put putIntoFrequenciesColumnFamily(EnrichedTrip enrichedTrip, Put put) {
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_FREQUENCIES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_ID), 
				Bytes.toBytes(enrichedTrip.getTripId()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_FREQUENCIES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_START_TIME), 
				Bytes.toBytes(enrichedTrip.getStartTime()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_FREQUENCIES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_END_TIME), 
				Bytes.toBytes(enrichedTrip.getEndTime()));
		
		put.addColumn(
				Bytes.toBytes(Constants.HBASE_TRIP_COLUMN_FAMILY_FREQUENCIES), 
				Bytes.toBytes(Constants.HBASE_TRIP_QUALIFIER_TRIP_HEADWAY_SECS), 
				Bytes.toBytes(enrichedTrip.getHeadwaySecs()));
		
		return put;
	}
	
}
