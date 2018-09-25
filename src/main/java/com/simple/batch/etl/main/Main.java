package com.simple.batch.etl.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.simple.batch.etl.hbase.dao.HBaseDaoFactory;
import com.simple.batch.etl.file.dao.FileDaoFactory;
import com.simple.batch.etl.file.dao.StopTimesFileDao;
import com.simple.batch.etl.hbase.dao.EnrichedTripHBaseDao;
import com.simple.batch.etl.hive.dao.EnrichedTripHiveDao;
import com.simple.batch.etl.hive.dao.HiveDaoFactory;
import com.simple.batch.etl.model.EnrichedStopTimes;
import com.simple.batch.etl.model.EnrichedTrips;
import com.simple.batch.etl.model.StopTimes;
import com.simple.batch.etl.utils.Constants;

public class Main {
	public static void main(String[] args) {
		
		sleep5seconds();
		
		List<EnrichedTrips> enrichedTripData = HiveDaoFactory.getDaoFactory(EnrichedTripHiveDao.class, EnrichedTrips.class).getAll();

		HBaseDaoFactory.getDaoFactory(EnrichedTripHBaseDao.class, EnrichedTrips.class).insert(enrichedTripData);
		
		List<StopTimes> stopTimesData = FileDaoFactory.getDaoFactory(StopTimesFileDao.class, StopTimes.class).getAll();
		
		List<EnrichedStopTimes> enrichedStopTimesData = new ArrayList<EnrichedStopTimes>();
		
		for(StopTimes stopTimes : stopTimesData) {
			EnrichedStopTimes enrichedStopTimes = new EnrichedStopTimes();
			EnrichedTrips enrichedTrips = HBaseDaoFactory.getDaoFactory(EnrichedTripHBaseDao.class, EnrichedTrips.class).getById(stopTimes.getTripId());
			
			enrichedStopTimes.setEnrichedTrips(enrichedTrips);
			enrichedStopTimes.setStopTimes(stopTimes);
			
			enrichedStopTimesData.add(enrichedStopTimes);
		}
		
		
		String fileData = buildStringForFile(enrichedStopTimesData);
		
		writeToFile(fileData);
		
	}
	
	private static void sleep5seconds() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	private static String buildStringForFile(List<EnrichedStopTimes> enrichedStopTimesData) {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(Constants.HIVE_ENRICHED_STOP_TIMES_TRIP_ID).append(",")
		.append(Constants.HIVE_ENRICHED_STOP_TIMES_ARRIVAL_TIME).append(",")
		.append(Constants.HIVE_ENRICHED_STOP_TIMES_DEPARTURE_TIME).append(",")
		.append(Constants.HIVE_ENRICHED_STOP_TIMES_STOP_ID).append(",")
		.append(Constants.HIVE_ENRICHED_STOP_SEQUENCE).append(",")
		.append(Constants.HIVE_ENRICHED_TRIP_ROUTE_ID).append(",")
		.append(Constants.HIVE_ENRICHED_TRIP_SERVICE_ID).append(",")
		.append(Constants.HIVE_ENRICHED_TRIP_TRIP_HEADSIGN).append(",")
		.append(Constants.HIVE_ENRICHED_TRIP_WHEELCHAIR_ACCESSIBLE).append(",")
		.append(Constants.HIVE_ENRICHED_TRIP_SHAPE_ID).append(",")
		.append(Constants.HIVE_ENRICHED_TRIP_NOTE_FR).append(",")
		.append(Constants.HIVE_ENRICHED_TRIP_NOTE_EN).append(",")
		.append(Constants.HIVE_ENRICHED_TRIP_DATE).append(",")
		.append(Constants.HIVE_ENRICHED_TRIP_EXCEPTION_TYPE).append(",")
		.append(Constants.HIVE_ENRICHED_TRIP_START_TIME).append(",")
		.append(Constants.HIVE_ENRICHED_TRIP_END_TIME).append(",")
		.append(Constants.HIVE_ENRICHED_TRIP_HEADWAY_SECS).append("\n");
		
		for(EnrichedStopTimes enrichedStopTimes : enrichedStopTimesData) {
			stringBuilder.append(enrichedStopTimes.getStopTimes().getTripId()).append(",")
			.append(enrichedStopTimes.getStopTimes().getArrivalTime()).append(",")
			.append(enrichedStopTimes.getStopTimes().getDepartureTime()).append(",")
			.append(enrichedStopTimes.getStopTimes().getStopId()).append(",")
			.append(enrichedStopTimes.getStopTimes().getStopSequence()).append(",")
			.append(enrichedStopTimes.getEnrichedTrips().getTrip().getRouteId()).append(",")
			.append(enrichedStopTimes.getEnrichedTrips().getTrip().getServiceId()).append(",")
			.append(enrichedStopTimes.getEnrichedTrips().getTrip().getTripHeadsign()).append(",")
			.append(enrichedStopTimes.getEnrichedTrips().getTrip().getWheelchairAccessible()).append(",")
			.append(enrichedStopTimes.getEnrichedTrips().getTrip().getShapeId()).append(",")
			.append(enrichedStopTimes.getEnrichedTrips().getTrip().getNoteFr()).append(",")
			.append(enrichedStopTimes.getEnrichedTrips().getTrip().getNoteEn()).append(",")
			.append(enrichedStopTimes.getEnrichedTrips().getCalendarDates().getDate()).append(",")
			.append(enrichedStopTimes.getEnrichedTrips().getCalendarDates().getExceptionType()).append(",")
			.append(enrichedStopTimes.getEnrichedTrips().getFrequencies().getStartTime()).append(",")
			.append(enrichedStopTimes.getEnrichedTrips().getFrequencies().getEndTime()).append(",")
			.append(enrichedStopTimes.getEnrichedTrips().getFrequencies().getHeadwaySecs()).append("\n");
		}
		
		return stringBuilder.toString();
	}
	
	private static void writeToFile(String fileData) {
		Path path = Paths.get(Constants.ENRICHED_STOP_TIMES_FILE);
		
		byte[] fileDataToByes = fileData.getBytes();
		
		try {
			Files.write(path, fileDataToByes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
