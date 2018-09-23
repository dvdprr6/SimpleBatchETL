package com.simple.batch.etl.hbase.dao;

import com.simple.batch.etl.model.Model;

public class HBaseDaoFactory {
	public static <MODEL extends Model, HBASEDAO extends HBaseDao<MODEL>> HBASEDAO getDaoFactory(Class<HBASEDAO> daoClass, Class<MODEL> modelClass){
		HBASEDAO hbaseDao = null;
		
		try {
			hbaseDao = daoClass.newInstance();
		}catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return hbaseDao;
	}
}
