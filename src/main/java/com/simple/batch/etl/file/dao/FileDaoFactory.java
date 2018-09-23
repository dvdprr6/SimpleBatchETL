package com.simple.batch.etl.file.dao;

import com.simple.batch.etl.model.Model;

public class FileDaoFactory {

	public static <MODEL extends Model, TEXTDAO extends FileDao<MODEL>> TEXTDAO getDaoFactory(Class<TEXTDAO> daoClass, Class<MODEL> modelClass){
		TEXTDAO textDao = null;
		
		try {
			textDao = daoClass.newInstance();
		}catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return textDao;
	}
}
