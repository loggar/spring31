package com.loggar.user.member;

import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import com.loggar.component.generic.GenericDaoJdbc;


@Repository
public class MemberDaoJdbc extends GenericDaoJdbc<Member> implements MemberDao {
	String updateSql = "update " + tableName + " set identi = :identi, name = :name, password = :password where id = :id";
	
	@Override
	public int edit(Member entity) {
		return this.namedParameterJdbcTemplate.update(updateSql, new BeanPropertySqlParameterSource(entity));
	}

	@Override
	public int[] edits(List<Member> list) {
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(list.toArray());
		return this.namedParameterJdbcTemplate.batchUpdate(updateSql, batch);
	}

	@Override
	public Member getByIdenti(String identi) {
		try {
			return this.namedParameterJdbcTemplate.queryForObject(
					"select * from member where identi = :identi",
					new MapSqlParameterSource("identi", identi),
					beanPropertyRowMapper);
		} catch (Exception e) {
			return null;
		}
	}
}
