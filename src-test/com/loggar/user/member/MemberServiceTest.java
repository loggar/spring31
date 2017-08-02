package com.loggar.user.member;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext-test.xml")
public class MemberServiceTest {
	@Autowired MemberService memberService;
	
	Member member1;
	Member member2;

	@Before public void init() {
		memberService.removeAll();
		
		member1 = new Member(0, "member1", "member1name", "member1pw");
		member2 = new Member(0, "member2", "member2name", "member2pw");
	}
	
	@Test public void memberServiceTest() {
		memberService.add(member1);
		memberService.add(member2);
		assertThat(memberService.getCount(), is(2));
		
		Member newMember1 = memberService.getByIdenti(member1.getIdenti());
		Member newMember2 = memberService.get(newMember1.getId());
		assertThat(newMember1.equals(newMember2), is(true));
		
		newMember1.setName("newName");
		assertThat(memberService.edit(newMember1).equals(newMember1), is(true));
		
		newMember1.setPassword("newPw1");
		newMember2.setPassword("newPw2");
		
		List<Member> list = new ArrayList<Member>();
		list.add(newMember1);
		list.add(newMember2);
		assertThat(memberService.updates(list), is(2));
		
		memberService.remove(newMember1.getId());
		assertThat(memberService.getCount(), is(1));
	}
}
