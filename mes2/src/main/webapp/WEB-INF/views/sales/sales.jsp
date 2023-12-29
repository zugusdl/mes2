<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="/resources/css/contents/contents.css">  
  </head>
  
  <body>
  <script src="/resources/js/sales/salesPlan/btn.js"></script>
  <script src="/resources/js/sales/salesPlan/details.js"></script>
  <script src="/resources/js/sales/salesPlan/list.js"></script>
  <script src="/resources/js/sales/salesPlan/search.js"></script>
  
    <div class="container">
    <section class="section1">
      <form id="sfm">
       <select name="type" >
       	<option value="title">제목</option>
       	<option value="writer">작성자</option>
       </select>

        <input type="text" name="key" placeholder="검색어를 입력하세요" />
        <input type="button" value="검색" onclick="searchBtnC()"/>
        <input type="button" value="리스트" onclick="loadList()"/>
      </form>




      <!-- 표 -->
      <form action='registerSales' class='list-form' id='salesForm'>
      <div class="list">
        <div class="list-btn">
        <!-- <button type="button" class="btn btn-secondary" onclick="return regCheck()">등록</button> -->     
        </div>

        <div class="list-box" id="view">
         		표 자리
        </div>
      </div>
      </form>
    </section >
	
 	
       
    <section class="section1" >
    <form class='list-form' id="view2">
    </form>
    </section>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
      crossorigin="anonymous"
    ></script>
  </body>
</html>