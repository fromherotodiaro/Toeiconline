package com.centurion.core.data.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GerericDao<ID extends Serializable, T> {
	// ID la kieu du lieu cua class T (VD class User, co userid co kieu du lieu la
	// Interger)

	List<T> findAll();

	T update(T entity);

	void save(T entity);

	T findById(ID id);

	Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset,
			Integer limit);

	Integer delete(List<ID> ids);

	T findEqualUnique(String property, Object value);

}
