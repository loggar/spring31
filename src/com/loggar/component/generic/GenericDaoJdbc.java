package com.loggar.component.generic;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.loggar.util.generic.GenericUtil;


public abstract class GenericDaoJdbc <T> implements GenericDao<T> {
	protected JdbcTemplate jdbcTemplate;
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	protected SimpleJdbcInsert simpleJdbcInsert;

	protected Class <T> domainClass;
	protected BeanPropertyRowMapper<T> beanPropertyRowMapper;
	protected String tableName;
	
	@SuppressWarnings("unchecked")
	public GenericDaoJdbc() {
		int index = 0;
		domainClass = (Class<T>) GenericUtil.getClassOfGenericTypeIn(getClass(), index);
		beanPropertyRowMapper = new BeanPropertyRowMapper<T>(domainClass);
		tableName = domainClass.getSimpleName().toLowerCase();
	}
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(tableName).usingGeneratedKeyColumns("id");
	}
	
	@Override 
	public int add(T entity) {
		return this.simpleJdbcInsert.executeAndReturnKey(new BeanPropertySqlParameterSource(entity)).intValue();
	}
	
	@Override
	public int getCount() {
		return this.jdbcTemplate.queryForInt("select count(*) from " + tableName);
	}
	
	@Override
	public T get(int id) {
		try {
			return this.namedParameterJdbcTemplate.queryForObject(
					"select * from " + tableName + " where id = :id",
					new MapSqlParameterSource().addValue("id", id),
					beanPropertyRowMapper);
		} catch (DataAccessException e) {
			return null;
		}
	}
	
	@Override
	public List<T> getAll() {
		return this.jdbcTemplate.query("select * from " + tableName, beanPropertyRowMapper);
	}
	
	@Override
	public List<T> getList(int start, int count) {
		return this.namedParameterJdbcTemplate.query(
				"select * from " + tableName + " limit :start, :count",
				new MapSqlParameterSource()
						.addValue("start", start)
						.addValue("count", count),
				beanPropertyRowMapper);
	}
	
	@Override
	public int remove(int id) {
		return this.jdbcTemplate.update("delete from " + tableName + " where id = ?", id);
	}
	
	@Override
	public int removeAll() {
		return this.jdbcTemplate.update("delete from " + tableName);
	}
}

