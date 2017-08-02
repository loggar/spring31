package com.loggar.user.member;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

public class MemberDetail {
	private int id;
	private Level level;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	Date date;

	@NumberFormat(pattern = "$###,##0.00")
	BigDecimal money;

	@NumberFormat(pattern = "###,##0")
	int point;

	private boolean isAdmin;
	private String enableFlag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberInfo [id=").append(id).append(", level=").append(level).append(", date=").append(date).append(", money=").append(money).append(", point=").append(point).append(", isAdmin=").append(isAdmin).append(", enableFlag=").append(enableFlag).append("]");
		return builder.toString();
	}
}
