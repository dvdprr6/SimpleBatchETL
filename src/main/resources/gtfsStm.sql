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

SELECT
  t.route_id, t.service_id, t.trip_id, t.trip_headsign, t.wheelchair_accessible, t.shape_id, t.note_fr, t.note_en,
  cd.`date`, cd.exception_type,
  f.start_time, f.end_time, f.headway_secs
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
