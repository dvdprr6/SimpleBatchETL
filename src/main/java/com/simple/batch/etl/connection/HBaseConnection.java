package com.simple.batch.etl.connection;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.ClusterConnection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

public class HBaseConnection {
	private static ClusterConnection clusterConnection;
	
	private HBaseConnection() {
		
	}
	
	public static synchronized ClusterConnection getHBaseConnection() {
		if(clusterConnection == null) {
			try {
				clusterConnection = (ClusterConnection) ConnectionFactory.createConnection(new Configuration());
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return clusterConnection;
	}
}
