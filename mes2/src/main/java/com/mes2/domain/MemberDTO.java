package com.mes2.domain;

import java.sql.Timestamp;


import lombok.Data;


@Data
public class MemberDTO {
	
	private String emp_num;
	private String emp_id;
	private String emp_pw;
	private String emp_name;
	private String emp_department;
	private String emp_position;
	private String emp_jumin;
	private String emp_joindate;
	private String emp_tel;
	private Timestamp emp_insert_date;
	private Timestamp emp_update_date;
	private String emp_auth;

}
