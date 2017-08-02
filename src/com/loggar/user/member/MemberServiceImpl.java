package com.loggar.user.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loggar.component.generic.GenericServiceImpl;


@Service
public class MemberServiceImpl extends GenericServiceImpl<Member, MemberDao> implements MemberService {
	@Autowired MemberDao memberDao;

	@Override
	public int updates(List<Member> list) {
		int[] updateResult = memberDao.edits(list);
		int successCnt = 0;
		for (int i : updateResult) {
			if (i > 0) successCnt++;
		}
		return successCnt;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Member getByIdenti(String identi) {
		return memberDao.getByIdenti(identi);
	}

	@Override
	@Transactional(readOnly = true)
	public void login(Member member) {
		// add later
		/*
		 * member.setLoginCount(member.getLoginCount() +1);
		 * memberDao.update(member);
		 */
	}

}
