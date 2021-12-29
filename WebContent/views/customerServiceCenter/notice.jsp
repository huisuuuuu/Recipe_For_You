<%@page import="kr.co.rfy.notice.model.vo.Notice"%>
<%@page import="kr.co.rfy.notice.model.vo.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>냉장고를 부탁해 공지사항 게시판</title>

<!-- boostrap5 라이브러리-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>


<!-- jQuery 라이브러리 -->
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>

<style type="text/css">
* {
	font-family: 'Noto Sans KR', sans-serif;
}

#wrap {
    width: 100%;
    background-color: #fff;
}

#header {
    position: relative;
    width: 100%;
    height: 200px;
    border-bottom: 1px solid #E0E0E0;
}

#center_form {
	width: 1200px;
	height: 1000px;
	margin: 0 auto;
}

#notice {
	height: 100px;
	width: 500px;
	margin: 0 auto;
}

#arrayList {
	height: 600px;
	width: 1200px;
	margin: 0 auto;
}

#pageList {
	height: 40px;
	width: 500px;
	margin: 0 auto;
}

#serachList {
	height: 30px;
	width: 1200px;
	margin: 0 auto;
}

#footer {
	height: 200px;
	margin: 0 auto;
}

#submit_form {
	width: 180px;
	height: 50px;
	float: right;
	border: 1px solid white;
}

#keyword_form {
	width: 300px;
	height: 50px;
	float: right;
	border: 1px solid white;
}

#option_form {
	width: 130px;
	height: 50px;
	float: right;
	border: 1px solid white;
}
</style>
</head>
<body>
	<%
		HashMap<String, Object> pageDataMap = (HashMap<String, Object>) request.getAttribute("pageDataMap");

		ArrayList<Notice> list = (ArrayList<Notice>) pageDataMap.get("list");

		String pageNavi = (String) pageDataMap.get("pageNavi");
		int currentPage = (int) request.getAttribute("currentPage");
	%>
	<div id="wrap">
		<div id="header">
			<%@ include file="/views/common/header2.jsp"%>
		</div>
		<br><br><br><br>

		<div id="center_form">
			<!-- 소개 하는 공간 -->
			<div id="notice" align="center">
				<h1>| NOTICE |</h1>
				<span>새로운 소식들과 유용한 정보들을 한곳에서 확인하세요.</span>
			</div>
			<br><br>
			
			<!-- 여기는 arrayList단 -->
			<div id="arrayList" align="left">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">번호</th>
							<th scope="col">제목</th>
							<th scope="col">작성자</th>
							<th scope="col">작성일</th>
							<th scope="col">조회</th>
						</tr>
					</thead>

					<%
						for (Notice notice : list) {
					%>

					<tr>
						<th scope="row"><%=notice.getBoard_no()%></th>
						<td><a
							href="/notice/noticeView.do?board_no=<%=notice.getBoard_no()%>&currentPage=<%=currentPage%>"><%=notice.getTitle()%>
						</a></td>
						<td><%=notice.getUser_id()%></td>
						<td><%=notice.getRegDate()%></td>
						<td><%=notice.getView_count()%></td>
					</tr>

					<%
						}
					%>
				</table>
			</div>
			<br>

			<!-- 페이징 처리 -->
			<div id="pageList" align="center">
				<nav aria-label="Page navigation example"
					style="width : 500px; margin: 0 auto;">
				<ul class="navi" style="margin: 0 auto;">
					<%=pageNavi%>
				</ul>
				</nav>

			<style>
			.navi>a {
			width: 8%;
			float: left;
			}
			</style>

				<script>
					$(function() {
						$('.prev').addClass('page-link');
						$('.prev').css('width', '35%');
						$('.naviNum').addClass('page-link');
						$('.next').addClass('page-link');
						$('.next').css('width', '35%');
					});
				</script>
			</div>
			<!-- 페이징 처리 -->
			<br>
			<!-- 검색 기능 -->
			<div id="serachList" align="right">

				<form action="/notice/noticeSearch.do" method="get">
					<div id="submit_form" align="left">
						<input class="btn btn-success" type="submit" value="검색하기" /> <input
							class="btn btn-success" type="button" value="메인센터"
							onclick="location='/'" />
					</div>

					<div id="keyword_form" align="left">
						<input class="form-control me-2" type="text" name="keyword"
							size="30" />
					</div>

					<div id="option_form">
						<select class="form-select" name="type">
							<option value="title">제목</option>
							<option value="content">내용</option>
							<option value="all">제목+내용</option>
						</select>
					</div>
				</form>

			</div>
			<!-- 검색 기능 -->
		</div>
		<br> <br> <br>

		<div id="footer">
			<%@ include file="/views/common/footer.jsp"%>
		</div>
	</div>
</body>
</html>