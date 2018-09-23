package com.simple.batch.etl.file.dao;

import java.util.List;

import com.simple.batch.etl.model.Model;

public interface FileDao<MODEL extends Model> {
	public List<MODEL> getAll();

}
