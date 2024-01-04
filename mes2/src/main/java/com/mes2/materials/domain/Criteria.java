package com.mes2.materials.domain;

public class Criteria {
	
	private int page;
	private int pageSize;
	
	private String type; // 검색조건
	private String keyword; // 키워드
	
	public Criteria() {
		this.page = 1;
		this.pageSize = 10;
	}
		

	
	public String getType() {
		return type;
	}
	
	
	public void setType(String type) {
		this.type = type;
	}
	
	
	public String getKeyword() {
		return keyword;
	}
	
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	
	
	
	// alt shift s + r

	// 페이지를 설정한다
	public void setPage(int page) {
		if(page <=0) {
			this.page = 1;
			return;
		}
		this.page = page;		
	}

	
	
	

	public void setPageSize(int pageSize) {
		
		if(pageSize <= 0 || pageSize > 100) {
			this.pageSize = 10;
			return;
		}
		
		this.pageSize = pageSize;
	}
	
	
	public int getPage() {
		return page;
	}
	public int getPageSize() {
		return pageSize;
	}
	
	//private int startPage; // 변수선언없이 get메서드만 구현
	// 변수는 없지만, mapper에서 사용 # {startPage} 요소 호출
	public int getStartPage() {
		// 페이지 정보를 쿼리사용되는 값(시작인덱스)으로 변경
		return (this.page - 1) * pageSize;
	}

	
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", pageSize=" + pageSize + ", type=" + type + ", keyword=" + keyword + "]";
	}
	
	
	
}