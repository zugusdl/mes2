package com.mes2.platform.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class mdbDTO {
	private String mdb_code; // 거래처 코드
	private String mdb_category; // 발주처, 수주처
	private String mdb_pw; // 플랫폼 비밀번호
	private String mdb_name; // 회사명
	private String mdb_manager; // 대표자
	private String mdb_address; // 주소
	private String mdb_call; // 전화번호
	private String mdb_fax; // 팩스번호
	private String mdb_email; // 이메일
	private String mdb_company_status; // 거래유무
}
