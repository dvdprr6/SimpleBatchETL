use course4_project;

CREATE EXTERNAL TABLE IF NOT EXISTS ext_trips(
    route_id                STRING,
    service_id              STRING,
    trip_id                 STRING,
    trip_headsign           STRING,
    direction_id            STRING,
    shape_id                STRING,
    wheelchair_accessible   STRING,
    note_fr                 STRING,
    note_en                 STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
STORED AS TEXTFILE
LOCATION '/user/vagrant/stm/gtfs/staging/trips'
tblproperties('skip.header.line.count'='1');

CREATE EXTERNAL TABLE IF NOT EXISTS ext_calendar_dates(
   service_id       STRING,
   `date`           STRING,
   exception_type   STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
STORED AS TEXTFILE
LOCATION '/user/vagrant/stm/gtfs/staging/calendar_dates'
tblproperties('skip.header.line.count'='1');

CREATE EXTERNAL TABLE IF NOT EXISTS ext_frequencies(
    trip_id         STRING,
    start_time      STRING,
    end_time        STRING,
    headway_secs    STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
STORED AS TEXTFILE
LOCATION '/user/vagrant/stm/gtfs/staging/frequencies'
tblproperties('skip.header.line.count'='1');

CREATE TABLE IF NOT EXISTS enriched_stop_times(
    trip_id               STRING,
    arrivale_time         STRING,
    departure_time        STRING,
    stop_id               STRING,
    stop_sequence         STRING,
    route_id              STRING,
    service_id            STRING,
    trip_headsign         STRING,
    wheelchair_accessible STRING,
    shape_id              STRING,
    note_fr               STRING,
    note_en               STRING,
    `date`                STRING,
    exception_type        STRING,
    start_time            STRING,
    end_time              STRING,
    headway_secs          STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LINES TERMINATED BY '\n'
STORED AS TEXTFILE
LOCATION '/user/vagrant/stm/gtfs/staging/enriched_stop_times'
tblproperties('skip.header.line.count'='1');


SELECT
  t.route_id, t.service_id, t.trip_id, t.trip_headsign, t.wheelchair_accessible, t.shape_id, t.note_fr, t.note_en,
  cd.`date`, cd.exception_type, cd.service_id,
  f.start_time, f.end_time, f.headway_secs, f.trip_id
FROM
  ext_trips t
JOIN
  ext_calendar_dates cd on t.service_id=cd.service_id
JOIN
  ext_frequencies f on t.trip_id=f.trip_id;
  
  
-- HBASE SHELL COMMAND
-- disable 'C4P:TRIP'
-- drop 'C4P:TRIP'
-- create 'C4P:TRIP', {NAME => 'T', VERSIONS => 5}, {NAME => 'CD', VERSIONS => 5}, {NAME => 'F', VERSIONS => 5}
  