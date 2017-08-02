package com.loggar.user.member;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext-test.xml")
public class MemberDaoTest {
	@Autowired MemberDao memberDao;
	
	Member member1;
	Member member2;

	@Before public void init() {
		memberDao.removeAll();
		
		member1 = new Member(0, "member1", "member1name", "member1pw");
		member2 = new Member(0, "member2", "member2name", "member2pw");
	}
	
	@Test 
	public void simpleDriverDataSource() {
		assertThat(memberDao.getCount(), is(0));
	}
	
	@Test
	public void add() {
		member1.setId(memberDao.add(member1));
		Logger.getLogger(this.getClass()).debug(member1);
		Member newMember = memberDao.get(member1.getId());
		
		assertThat(member1.equals(newMember), is(true));
		assertThat(memberDao.getCount(), is(1));
	}
	
	@Test
	public void getByIdenti() {
		int member1_id = memberDao.add(member1);
		member1.setId(member1_id);
		Member newMember = memberDao.getByIdenti(member1.getIdenti());
		assertThat(member1.equals(newMember), is(true));
		
		memberDao.remove(member1.getId());
		assertThat(memberDao.getCount(), is(0));
	}
	
	@Test
	public void getList() {
		int member1_id = memberDao.add(member1);
		int member2_id = memberDao.add(member2);
		assertThat(memberDao.getCount(), is(2));
		
		List<Member> list1 = memberDao.getAll();
		List<Member> list2 = memberDao.getList(0, 2);
		
		assertThat(list1.size(), is(2));
		assertThat(list2.size(), is(2));
		
		assertThat(list1.get(0).getId(), is(member1_id));
		assertThat(list2.get(1).getId(), is(member2_id));
	}
	
	@Test
	public void update() {
		int member1_id = memberDao.add(member1);
		
		Member member = memberDao.get(member1_id);
		member.setPassword("newPw");
		assertThat(memberDao.edit(member), is(1));
		
		Member updateMember = memberDao.get(member.getId());
		assertThat(member.equals(updateMember), is(true));
	}
	
	@Test
	public void updateBatch() {
		int member1_id = memberDao.add(member1);
		int member2_id = memberDao.add(member2);
		
		member1.setName("newName1");
		member2.setName("newName2");
		
		List<Member> list = new ArrayList<Member>();
		list.add(member1);
		list.add(member2);
		
		memberDao.edits(list);
		
		List<Member> list1 = memberDao.getAll();
		
		assertThat(list1.get(0).getId(), is(member1_id));
		assertThat(list1.get(1).getId(), is(member2_id));
	}
}
