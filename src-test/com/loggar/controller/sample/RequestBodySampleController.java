package com.loggar.controller.sample;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestBodySampleController {
	class User {
		private String name;
		private int age;
		private List<User> userList;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public List<User> getUserList() {
			return userList;
		}
		public void setUserList(List<User> userList) {
			this.userList = userList;
		}
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 * 
	 * <pre>
	 * http request : /resquestBodySample?{"userList":[{"name":"name1", "age", "age1"},{"name":"name2", "age", "age2"}]}
	 * </pre>
	 * 
	 */
	@RequestMapping(value="/resquestBodySample")
	public Integer test(@RequestBody User user) {
		return user.getUserList().size();
	}
}
