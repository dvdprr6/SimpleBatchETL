package com.simple.batch.etl.hive.dao;

import com.simple.batch.etl.model.Model;

public class HiveDaoFactory {
	
	public static <MODEL extends Model, HIVEDAO extends HiveDao<MODEL>> HIVEDAO getDaoFactory(Class<HIVEDAO> daoClass, Class<MODEL> modelClass){
		HIVEDAO hiveDao = null;
		
		try {
			hiveDao = daoClass.newInstance();
		}catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return hiveDao;
	}
}
