package com.loggar.component.generic;

import java.util.List;

public interface GenericService<T> {
	int add(T entity);
	int getCount();
	T get(int id);
	List<T> getAll();
	List<T> getList(int start, int count);
	T edit(T entity);
	boolean remove(int id);
	int removeAll();
}
