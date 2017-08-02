package com.loggar.user.member;

import javax.validation.constraints.Size;

public class Member {
	int id;
	
	@Size(min=4, max=100)
	String identi;
	
	@Size(min=4, max=100)
	String name;
	
	@Size(min=4, max=45)
	String password;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdenti() {
		return identi;
	}

	public void setIdenti(String identi) {
		this.identi = identi;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", identi=" + identi + ", name=" + name + ", password=" + password + "]";
	}

	public Member(int id, String identi, String name, String password) {
		super();
		this.id = id;
		this.identi = identi;
		this.name = name;
		this.password = password;
	}

	public Member() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Member other = (Member) obj;
		if (id != other.id) return false;
		return true;
	}
}
