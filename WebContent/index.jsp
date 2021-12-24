<%@page import="kr.co.rfy.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<style>
	#loginForm{
		border : 1px solid grey;
		width : 300px;
		height : 250px;
		margin: 0 auto;
	}
	*{
	box-sizing: border-box;
	
	}
</style>
</head>
<body>
<H3>메인 페이지 (기본)</H3>
<%
	Member m = (Member)session.getAttribute("member");
%>

<%if(m!=null){ %>

	[<a href="/member/memberCheck.do"><%=m.getUser_Name()%></a>] 님 환영합니다!! <a href="/member/logout.do">로그아웃</a><br>


<%if(m.getRoll().substring(0,2).equals("AD")){  //관리자로 구분되어진 사용자라면%>
	<a href="/views/admin/adminmain.jsp">관리자메인</a>
	
	<a href="/admin/boardAllList.do">게시판관리 페이지</a><br>
	<%}else{ %>
	
	<a href="/member/memberWithDraw.do" id="withDraw">회원 탈퇴</a><br>
	<%} %>

<%}else{ %>
<div id="loginForm">
	<form action="/member/login.do" method="post">
		<fieldset>
		<legend style="text-align: center;">로그인</legend>
		<input type="text"  class="b-block w-100" name="userId" placeholder="ID를 입력하세요"/><br><br>
		<input type="password" class="b-block w-100"  name="userPwd" placeholder="PW를 입력하세요"/><br><br>
		<input type="submit" class="btn btn-primary b-block w-100" value="로그인"/>
		</fieldset>
		<br>
		<a href="/views/member/memberJoinus.jsp" style="display:block; text-align: right;">회원가입</a>
	</form>
</div>
<%} %>

</body>
</html>