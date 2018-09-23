package com.simple.batch.etl.hbase.dao;

import java.util.List;

import com.simple.batch.etl.model.Model;

public interface HBaseDao<MODEL extends Model> {
	public void insert(List<MODEL> model);
}
