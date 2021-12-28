<%@page import="kr.co.rfy.member.model.vo.Member"%>
<%@page import="kr.co.rfy.qna.model.vo.Qna"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- boostrap5 라이브러리-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<!-- jQuery 라이브러리 -->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<style type="text/css">
#wrap{
	width: 100%;
}

#center{
	width: 85%;
	height: 850px;
	float: right;
}

#titleForm{
	height: 70px;
	width: 980px;
	margin: 0 auto;
	
}

#contentForm{
	height: 500px;
	width: 980px;
	margin: 0 auto;
	
}

#commitForm{
	height: 50px;
	width: 980px;
	margin: 0 auto;
	
}

#codeForm{
	height: 70px;
	width: 980px;
	margin: 0 auto;
	
}

#qna{
	height: 100px;
	width : 500px;
	margin: 0 auto;
}


* {
	font-family: 'Noto Sans KR', sans-serif;
}

div {
	box-sizing: border-box;
}

#header {
	width: 85%;
	height: 50px;
	float: right;
}

#navigation {
	width: 15%;
	height: 950px;
	float: left;
	position:fixed
}

#content {
	width: 980px;
	height: 450px;
	float: right;
}

#footer {
	width: 85%;
	height: 50px;
	float: right;
}

#mainLogo {
	width: 100%;
	height: 100px;
	background-color: #5D9A71;
	text-align: center;
}

#title_navi {
	letter-spacing: 3px;
	text-align: center;
	text-decoration: none;
	font-weight: 600;
	color: white;
	font-size: 15px;
}

#title_navi:before {
	content: "";
	display: block;
	width: 185px;
	border: 1px solid white;
	margin: 3px auto;
}

#title_navi:after {
	content: "";
	display: block;
	width: 185px;
	border: 1px solid white;
	margin: 3px auto;
}

#mainLogo>p {
	line-height: 40px;
	margin: 0px;
	display: inline-block;
	color: white;
}

#gnb {
	width: 100%;
	height: 850px;
	margin: 0px;
	padding: 0px;
	padding-top: 50px;
	background-color: #5D9A71;
}

#gnb>li {
	width: 100%;
	list-style: none;
}

#gnb>li>a {
	width: 100%;
	height: 100%;
	color: white;
	text-align: center;
	line-height: 59px;
	text-decoration: none;
	display: block;
	font-size: 17px;
}

#gnb>li>a:hover {
	font-weight: bolder;
}

.submenu {
	border: 1px solid white;
	width: 70%;
	height: 100%;
	display: none;
}

.submenu li {
	list-style: none;
	color: white;
}

.submenu>li>a {
	width: 100%;
	height: 100%;
	line-height: 40px;
	list-style: none;
	text-decoration: none;
	color: white;
	visibility: hidden;
	font-size: 14px;
}

.submenu>li>a:hover {
	font-weight: bolder;
}

#gnb>li:hover>.submenu {
	border: 1px solid white;
	margin: 0 auto;
	display: block;
}

#gnb>li:hover>.submenu>li {
	list-style: disc;
}

#gnb>li:hover>.submenu>li>a {
	visibility: visible;
}

.head_article{
	margin-bottom: 30px;
}

.tit {
	font-weight: bolder;
	font-family: "Noto Serif KR", serif, Helvetica, "Helvetica Neue", Arial;
	font-size: 22px;
}

.page_section {
	width: 92%;
	margin-top: 0px;
	margin-left: 50px;
}
</style>

<title>냉장고를 부탁해 QnA 게시판</title>
</head>
<%
Member m = (Member)session.getAttribute("member");

//Qna qna= new Qna();
request.setCharacterEncoding("utf-8");
int board_no = Integer.parseInt(request.getParameter("board_no"));
int currentPage = Integer.parseInt(request.getParameter("currentPage"));
String q_code = request.getParameter("q_code");
String title = request.getParameter("title");
String content = request.getParameter("content");

%>
<body>
		<div id="warp">
		<div id="navigation">
			<div id="mainLogo">
				<br> <br> <a href="/" id="title_navi">냉장고를 부탁해 관리자</a>
			</div>
			<ul id="gnb">
				<li><a href="">레시피 관리</a>
					<ul class="submenu">
						<li><a href="">레시피 등록</a></li>
						<li><a href="">레시피 조회 및 관리</a></li>
					</ul></li>

				<li><a href="">문의/리뷰 관리</a>
					<ul class="submenu">
						<li><a href="/customerServiceCenter/noticeAdmin.do">공지사항 관리</a></li>
						<li><a href="/customerServiceCenter/qnaAdmin.do">자주하는 질문 관리</a></li>
					</ul></li>

				<li><a href="/admin/memberAllList.do">회원 관리</a>
					<ul class="submenu">
						<li><a href="/admin/memberAllList.do">일반 회원 관리</a></li>
						<li><a href="/admin/blackList.do">블랙리스트 관리</a></li>
					</ul></li>
			</ul>
		</div>
		<br>
		
	<form action="/qna/qnaUpdate.do" method="post">
	<input type="hidden" name=board_no value="<%=board_no %>">
	<input type="hidden" name="currentPage" value="<%=currentPage%>">
		<div id="center">
		
			<div id="qna" align="center"">
				<h1>  |  QnA  |  </h1>
				<span>새로운 소식들과 유용한 정보들을 한곳에서 확인하세요.</span>
			</div><br>
			
			<div id="codeForm">
				카테고리 선택<br>
				<select class="form-select" name="q_code">
					<option value="Q-1">회원</option>
					<option value="Q-2">서비스 이용</option>
					<option value="Q-3">주문/결제</option>
					<option value="Q-4">배송/상품</option>
					<option value="Q-5">취소/교환/환불</option>
				</select>
			</div>
		
			<div id="titleForm">
				글 제목<br>
				 <input type="text" class="form-control" id="title" name="title" value="<%=title%>"/>
			</div>
			
			<div id="contentForm">
				글 내용<br>
				 <textarea class="form-control" id="content" name="content" rows="5" style="resize: none;"><%=content%></textarea>
			</div>
			
			<div id="commitForm" align="center">
				<input class="btn btn-success btn-sm" type="submit" value="글 쓰기">
				<input class="btn btn-success btn-sm" type="reset" value="초기화">
				<input class="btn btn-success btn-sm" type="button" value="메인으로" onclick="location='/customerServiceCenter/qnaAdmin.do'">
			</div>
		</div>
	</form>
	</div>
</body>
</html>