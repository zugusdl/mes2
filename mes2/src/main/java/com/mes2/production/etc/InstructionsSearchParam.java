package com.mes2.production.etc;

import java.sql.Date;

import com.mes2.production.vo.InstructionsState;

public class InstructionsSearchParam {
	
	private String code;
	private InstructionsState state;
	private Date startDate;
	private Date endDate;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public InstructionsState getState() {
		return state;
	}
	public void setState(InstructionsState state) {
		this.state = state;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
	

}
