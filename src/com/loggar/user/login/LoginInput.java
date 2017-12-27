package com.loggar.user.login;

import javax.validation.constraints.Size;

/**
 * 사용자 로그인 폼 '@ModelAttribute', '@SessionAttributes' 저장용 도메인
 *
 */
public class LoginInput {
	@Size(min=4, max=100)
	String identi;
	
	@Size(min=4, max=45)
	String password;

	public String getIdenti() {
		return identi;
	}

	public void setIdenti(String identi) {
		this.identi = identi;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginInput [identi=").append(identi).append(", password=").append(password).append("]");
		return builder.toString();
	}

}
