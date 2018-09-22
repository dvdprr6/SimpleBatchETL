package com.simple.batch.etl.hive.dao;

import java.util.List;

import com.simple.batch.etl.model.Model;

public interface HiveDao<MODEL extends Model> {
	public List<MODEL> getAll();
}
