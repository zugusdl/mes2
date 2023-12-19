package com.mes2.production.domain;

import java.sql.Date;

public class InstructionsDTO {
	
	private String code;
	private int line;
	private String mdpCode;
	private String type;
	private String soiCode;
	private Date startTime;
	private Date closeTime;
	private int empNum;
	private String state;
	private int quantity;
	private int fault;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getMdpCode() {
		return mdpCode;
	}
	public void setMdpCode(String mdpCode) {
		this.mdpCode = mdpCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSoiCode() {
		return soiCode;
	}
	public void setSoiCode(String soiCode) {
		this.soiCode = soiCode;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}

	
	
	public int getEmpNum() {
		return empNum;
	}
	public void setEmpNum(int empNum) {
		this.empNum = empNum;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getFault() {
		return fault;
	}
	public void setFault(int fault) {
		this.fault = fault;
	}
	@Override
	public String toString() {
		return "InstructionsDTO [code=" + code + ", line=" + line + ", mdpCode=" + mdpCode + ", type=" + type
				+ ", soiCode=" + soiCode + ", startTime=" + startTime + ", closeTime=" + closeTime + ", empNum="
				+ empNum + ", state=" + state + ", quantity=" + quantity + ", fault=" + fault + "]";
	}

	
	
	
	
	
}


