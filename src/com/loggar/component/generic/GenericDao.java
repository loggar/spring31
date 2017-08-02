package com.loggar.component.generic;

import java.util.List;

public interface GenericDao<T> {
	int add(T entity);
	int getCount();
	T get(int id);
	List<T> getAll();
	List<T> getList(int start, int count);
	int edit(T entity);
	int remove(int id);
	int removeAll();
}
