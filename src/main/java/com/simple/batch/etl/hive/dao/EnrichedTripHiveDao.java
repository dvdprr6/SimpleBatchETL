package com.simple.batch.etl.hive.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.simple.batch.etl.connection.HiveConnection;
import com.simple.batch.etl.model.CalendarDates;
import com.simple.batch.etl.model.EnrichedTrips;
import com.simple.batch.etl.model.Frequencies;
import com.simple.batch.etl.model.Trips;
import com.simple.batch.etl.utils.Constants;

public class EnrichedTripHiveDao implements HiveDao<EnrichedTrips> {
	
	EnrichedTripHiveDao(){
		
	}

	@Override
	public List<EnrichedTrips> getAll() {
		List<EnrichedTrips> enrichedTripData = new ArrayList<EnrichedTrips>();
		
		try {
			PreparedStatement preparedStatement = HiveConnection.getHiveConnection().prepareStatement(Constants.HIVE_ENRICHED_TRIP_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Trips trips = new Trips();
				CalendarDates calendarDates = new CalendarDates();
				Frequencies frequencies = new Frequencies();
				EnrichedTrips enrichedTrips = new EnrichedTrips();
				
				trips.setRouteId(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_ROUTE_ID));
				trips.setServiceId(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_SERVICE_ID));
				trips.setTripId(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_TRIP_ID));
				trips.setTripHeadsign(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_TRIP_HEADSIGN));
				trips.setWheelchairAccessible(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_WHEELCHAIR_ACCESSIBLE));
				trips.setShapeId(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_SHAPE_ID));
				trips.setNoteFr(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_NOTE_FR));
				trips.setNoteEn(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_NOTE_EN));
				
				calendarDates.setServiceId(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_SERVICE_ID));
				calendarDates.setDate(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_DATE));
				calendarDates.setExceptionType(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_EXCEPTION_TYPE));
				
				frequencies.setTripId(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_TRIP_ID));
				frequencies.setStartTime(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_START_TIME));
				frequencies.setEndTime(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_END_TIME));
				frequencies.setHeadwaySecs(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_HEADWAY_SECS));
				
				enrichedTrips.setTrip(trips);
				enrichedTrips.setCalendarDates(calendarDates);
				enrichedTrips.setFrequencies(frequencies);
				
				enrichedTripData.add(enrichedTrips);
				
			}
			
			resultSet.close();
			preparedStatement.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return enrichedTripData;
	}

}
