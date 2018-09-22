package com.simple.batch.etl.main;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.simple.batch.etl.hive.dao.EnrichedTripDao;
import com.simple.batch.etl.hive.dao.HiveDaoFactory;
import com.simple.batch.etl.model.EnrichedTrip;

public class Main {
	public static void main(String[] args) {
		
		sleep5seconds();
		
		List<EnrichedTrip> enrichedTripData = HiveDaoFactory.getDaoFactory(EnrichedTripDao.class, EnrichedTrip.class).getAll();

	}
	
	private static void sleep5seconds() {
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
