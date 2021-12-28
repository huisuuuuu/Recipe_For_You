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

#notice{
	height: 100px;
	width : 500px;
	margin: 0 auto;
	
}

#contentForm{
	height: 400px;
	width: 1000px;
	margin: 0 auto;
	border: 1px solid black;
}

#titleForm{
	overflow : hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	height: 50px;
	width: 1000px;
	margin: 0 auto;
	border: 1px solid black;
}

#regDage{
	height: 30px;
	width: 1000px;
	margin: 0 auto;
	
}

#menu{
	height: 30px;
	width: 1000px;
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

#title {
	letter-spacing: 3px;
	text-align: center;
	text-decoration: none;
	font-weight: 600;
	color: white;
	font-size: 15px;
}

#title:before {
	content: "";
	display: block;
	width: 185px;
	border: 1px solid white;
	margin: 3px auto;
}

#title:after {
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
<body>
<% 
Qna qna = (Qna)request.getAttribute("qna");
int currentPage = (int)request.getAttribute("currentPage");
Member m = (Member)session.getAttribute("member");
%>

	<div id="wrap">
		<div id="navigation">
			<div id="mainLogo">
				<br> <br> <a href="/" id="title">냉장고를 부탁해 관리자</a>
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
		
		<form action="/views/customerServiceCenter/qnaUpdate.jsp" method="post">
		<input type="hidden" name="board_no" value="<%=qna.getBoard_no()%>">
		<input type="hidden" name="currentPage" value="<%=currentPage%>">
		<input type="hidden" name="q_code" value="<%=qna.getQ_code()%>">
		<input type="hidden" name="title" value="<%=qna.getTitle()%>">
		<input type="hidden" name="content" value="<%=qna.getContent()%>">
		<div id="center"> 
			<!-- 소개 하는 공간 -->
		<div id="qna" align="center"">
				<h1>  |  QnA  |  </h1>
				<span>새로운 소식들과 유용한 정보들을 한곳에서 확인하세요.</span>
		</div>
			
			<div id="titleForm" align="center">
				<h2>[ <%=qna.getTitle() %> ]</h2>
				
			</div>
			
			<div id="regDage" align="left">
				작성일 : [ <%=qna.getRegDate() %> ]  /  
					<%if(qna.getQ_code().substring(2,3).equals("1")){ %>
						<td>분류 : [ 회원 ]</td>
					<%}else if(qna.getQ_code().substring(2,3).equals("2")){ %>
						<td>분류 : [ 서비스 이용 ]</td>
					<%}else if(qna.getQ_code().substring(2,3).equals("3")){  %>
						<td>분류 : [ 주문/결제 ]</td>
					<%}else if(qna.getQ_code().substring(2,3).equals("4")){  %>
						<td>분류 : [ 배송/상품 ]</td>
					<%}else if(qna.getQ_code().substring(2,3).equals("5")){  %>
						<td>분류 : [ 취소/교환/환불 ]</td>
					<%} %>
					
				  /  글 번호 : [ <%=qna.getBoard_no()%> ]   
				  
			</div>
			
			<div id="contentForm" style="overflow: auto;">
				<%=qna.getContent() %>
			</div><br>
			
			<div id="menu" align="center">
				<input class="btn btn-success btn-sm" type="button" value="메인 메뉴" onclick="location='/customerServiceCenter/qnaAdmin.do?currentPage=<%=currentPage%>'">
				<%if(m != null && m.getUserId().equals(qna.getUser_id())){ %>
				<input class="btn btn-success btn-sm" type="submit" value="글 수정"/>
				<input class="btn btn-success btn-sm" type="button" value="글 삭제" id="deleteBtn"/>
				<%} %>
				
				<!-- 글 삭제 -->
			<script type="text/javascript">
			$('#deleteBtn').click(function(){
				if(window.confirm('삭제할건가요?')){
					var formTag = document.createElement("form");
					formTag.setAttribute("action",'/qna/qnaDelete.do');
					formTag.setAttribute("method",'post');
					
					var inputTag = document.createElement("input");
					inputTag.setAttribute("type","hidden");
					inputTag.setAttribute("name","board_no");
					inputTag.setAttribute("value","<%=qna.getBoard_no()%>");
					
					formTag.appendChild(inputTag);//폼 테그안에 인풋 태그 넣고
					document.body.appendChild(formTag);//폼 테그를 연결시켜준다
					formTag.submit();
				}else{
					alert('삭제하지 않습니다');
				}				
			});
			</script>
			<!-- 글 삭제 -->
			</div>
			
		</div>
		</form>
	</div>
</body>
</html>