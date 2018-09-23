package com.simple.batch.etl.hive.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.simple.batch.etl.connection.HiveConnection;
import com.simple.batch.etl.model.EnrichedTrip;
import com.simple.batch.etl.utils.Constants;

public class EnrichedTripHiveDao implements HiveDao<EnrichedTrip> {
	
	EnrichedTripHiveDao(){
		
	}

	@Override
	public List<EnrichedTrip> getAll() {
		List<EnrichedTrip> enrichedTripData = new ArrayList<EnrichedTrip>();
		
		try {
			PreparedStatement preparedStatement = HiveConnection.getHiveConnection().prepareStatement(Constants.HIVE_ENRICHED_TRIP_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				EnrichedTrip enrichedTrip = new EnrichedTrip();
				enrichedTrip.setRouteId(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_ROUTE_ID));
				enrichedTrip.setServiceId(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_SERVICE_ID));
				enrichedTrip.setTripId(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_TRIP_ID));
				enrichedTrip.setTripHeadsign(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_TRIP_HEADSIGN));
				enrichedTrip.setWheelchairAccessible(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_WHEELCHAIR_ACCESSIBLE));
				enrichedTrip.setShapeId(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_SHAPE_ID));
				enrichedTrip.setNoteFr(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_NOTE_FR));
				enrichedTrip.setNoteEn(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_NOTE_EN));
				enrichedTrip.setDate(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_DATE));
				enrichedTrip.setExceptionType(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_EXCEPTION_TYPE));
				enrichedTrip.setStartTime(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_START_TIME));
				enrichedTrip.setEndTime(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_END_TIME));
				enrichedTrip.setHeadwaySecs(resultSet.getString(Constants.HIVE_ENRICHED_TRIP_HEADWAY_SECS));
				enrichedTripData.add(enrichedTrip);
			}
			
			resultSet.close();
			preparedStatement.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return enrichedTripData;
	}

}
