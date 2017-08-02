package com.loggar.user.member;

import java.util.List;

import com.loggar.component.generic.GenericDao;


public interface MemberDao extends GenericDao<Member> {
	int[] edits(List<Member> list);
	Member getByIdenti(String identi);
}
