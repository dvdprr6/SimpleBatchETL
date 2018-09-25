package com.simple.batch.etl.utils;

public class Constants {
	public static final String HIVE_JDBC_DRIVER = "org.apache.hive.jdbc.HiveDriver";
	public static final String HIVE_URL = "jdbc:hive2://localhost:10000/course4_project";
	public static final String HIVE_USER = "vagrant";
	public static final String HIVE_PASSWORD = "";
	
	public static final String HIVE_ENRICHED_TRIP_QUERY = "SELECT " + 
			"t.route_id, t.service_id, t.trip_id, t.trip_headsign, t.wheelchair_accessible, t.shape_id, t.note_fr, t.note_en, " + 
			"cd.`date`, cd.exception_type, " + 
			"f.start_time, f.end_time, f.headway_secs " + 
			"FROM " + 
			"ext_trips t " + 
			"JOIN " + 
			"ext_calendar_dates cd on t.service_id=cd.service_id " + 
			"JOIN " + 
			"ext_frequencies f on t.trip_id=f.trip_id";
	public static final String HIVE_ENRICHED_TRIP_ROUTE_ID = "route_id";
	public static final String HIVE_ENRICHED_TRIP_SERVICE_ID = "service_id";
	public static final String HIVE_ENRICHED_TRIP_TRIP_ID = "trip_id";
	public static final String HIVE_ENRICHED_TRIP_TRIP_HEADSIGN = "trip_headsign";
	public static final String HIVE_ENRICHED_TRIP_WHEELCHAIR_ACCESSIBLE = "wheelchair_accessible";
	public static final String HIVE_ENRICHED_TRIP_SHAPE_ID = "shape_id";
	public static final String HIVE_ENRICHED_TRIP_NOTE_FR = "note_fr";
	public static final String HIVE_ENRICHED_TRIP_NOTE_EN = "note_en";
	public static final String HIVE_ENRICHED_TRIP_DATE = "date";
	public static final String HIVE_ENRICHED_TRIP_EXCEPTION_TYPE = "exception_type";
	public static final String HIVE_ENRICHED_TRIP_START_TIME = "start_time";
	public static final String HIVE_ENRICHED_TRIP_END_TIME = "end_time";
	public static final String HIVE_ENRICHED_TRIP_HEADWAY_SECS = "headway_secs";
	
	public static final String HIVE_ENRICHED_STOP_TIMES_TRIP_ID = HIVE_ENRICHED_TRIP_TRIP_ID;
	public static final String HIVE_ENRICHED_STOP_TIMES_ARRIVAL_TIME = "arrival_time";
	public static final String HIVE_ENRICHED_STOP_TIMES_DEPARTURE_TIME = "departure_time";
	public static final String HIVE_ENRICHED_STOP_TIMES_STOP_ID = "stop_id";
	public static final String HIVE_ENRICHED_STOP_SEQUENCE = "stop_sequence";
	
	public static final String HBASE_TRIP_TABLE = "C4P:TRIP";
	public static final String HBASE_TRIP_COLUMN_FAMILY_TRIP = "T";
	public static final String HBASE_TRIP_COLUMN_FAMILY_CALENDAR_DATES = "CD";
	public static final String HBASE_TRIP_COLUMN_FAMILY_FREQUENCIES = "F";
	public static final String HBASE_QUALIFIER_ROUTE_ID = "route_id";
	public static final String HBASE_TRIP_QUALIFIER_SERVICE_ID = "service_id";
	public static final String HBASE_TRIP_QUALIFIER_TRIP_ID = "trip_id";
	public static final String HBASE_TRIP_QUALIFIER_HEADSIGN = "trip_headsign";
	public static final String HBASE_TRIP_QUALIFIER_WHEELCHAIR_ACCESSIBLE = "wheelchair_accessible";
	public static final String HBASE_TRIP_QUALIFIER_SHAPE_ID = "shape_id";
	public static final String HBASE_TRIP_QUALIFIER_NOTE_FR = "note_fr";
	public static final String HBASE_TRIP_QUALIFIER_NOTE_EN = "note_en";
	public static final String HBASE_TRIP_QUALIFIER_TRIP_DATE = "date";
	public static final String HBASE_TRIP_QUALIFIER_TRIP_EXCEPTION_TYPE = "exception_type";
	public static final String HBASE_TRIP_QUALIFIER_TRIP_START_TIME = "start_time";
	public static final String HBASE_TRIP_QUALIFIER_TRIP_END_TIME = "end_time";
	public static final String HBASE_TRIP_QUALIFIER_TRIP_HEADWAY_SECS = "headway_secs";
	
	public static final String STOP_TIMES_FILE = "/home/vagrant/SimpleBatchETL/gtfs_stm/stop_times.txt";
	public static final String ENRICHED_STOP_TIMES_FILE = "/home/vagrant/SimpleBatchETL/gtfs_stm/enriched_stop_times.txt";
	
}
