package com.simple.batch.etl.file.dao;

import java.util.ArrayList;
import java.util.List;

import com.simple.batch.etl.connection.FileConnection;
import com.simple.batch.etl.model.StopTimes;
import com.simple.batch.etl.utils.Constants;

public class StopTimesFileDao implements FileDao<StopTimes> {

	StopTimesFileDao(){
		
	}
	
	@Override
	public List<StopTimes> getAll() {
		List<String> stopTimesRecords = FileConnection.readTextFile(Constants.STOP_TIMES_FILE);
		List<StopTimes> stopTimesObjects = new ArrayList<StopTimes>();
		
		stopTimesRecords.remove(0); // remove the column names in the file
		
		for(String stopTimesLine : stopTimesRecords) {
			String[] records = stopTimesLine.split(",");
			StopTimes stopTimes = new StopTimes();
			stopTimes.setTripId(records[0]);
			stopTimes.setArrivalTime(records[1]);
			stopTimes.setDepartureTime(records[2]);
			stopTimes.setStopId(records[3]);
			stopTimes.setStopSequence(records[4]);
			
			stopTimesObjects.add(stopTimes);
			
		}
		
		return stopTimesObjects;
	}

}
