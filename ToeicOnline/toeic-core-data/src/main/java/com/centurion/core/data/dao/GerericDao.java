package com.centurion.core.data.dao;

import java.io.Serializable;
import java.util.List;

public interface GerericDao<ID extends Serializable, T> {
	// ID la kieu du lieu cua class T (VD class User, co userid co kieu du lieu la Interger)
	
	List<T> findAll();
	T update (T entity);
	void save (T entity);
	T findId (ID id);
	Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection);
	Integer delete(List<ID> ids);
	
	
	
}
