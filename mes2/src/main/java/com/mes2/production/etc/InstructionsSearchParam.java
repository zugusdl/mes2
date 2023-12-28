package com.mes2.production.etc;

import java.sql.Date;
import java.sql.Timestamp;

import com.mes2.production.vo.InstructionsState;

public class InstructionsSearchParam {
	
	private String code;
	private InstructionsState state;
	private Timestamp startTime;
	private Timestamp endTime;
	private String searchType;
	
	// 날짜 검색 전용
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

	
	
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
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
