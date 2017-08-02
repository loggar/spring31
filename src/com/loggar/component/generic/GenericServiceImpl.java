package com.loggar.component.generic;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.loggar.util.generic.GenericUtil;


@Transactional
public abstract class GenericServiceImpl<T, D extends GenericDao<T>> implements GenericService<T> {
	@Autowired ApplicationContext applicationContext;
	
	@SuppressWarnings("unused")
	private Class<T> domainClass;
	private Class<D> daoClass;
	private D dao;
	
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void setDaoBean() {
		int index = 0;
		domainClass = (Class<T>) GenericUtil.getClassOfGenericTypeIn(getClass(), index++);
		daoClass = (Class<D>) GenericUtil.getClassOfGenericTypeIn(getClass(), index++);
		dao = applicationContext.getBean(daoClass);
	}
	
	@Override
	public int add(T entity) {
		return dao.add(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public int getCount() {
		return dao.getCount();
	}

	@Override
	@Transactional(readOnly = true)
	public T get(int id) {
		return dao.get(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getAll() {
		return dao.getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getList(int start, int count) {
		return dao.getList(start, count);
	}

	@Override
	public T edit(T entity) {
		dao.edit(entity);
		return entity;
	}

	@Override
	public boolean remove(int id) {
		int deleteResult = dao.remove(id);
		if (deleteResult > 0) return true;
		else return false;
	}

	@Override
	public int removeAll() {
		return dao.removeAll();
	}

}
