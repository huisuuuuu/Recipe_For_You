<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/assets/css/adminNavigation.css">
<title>Insert title here</title>
</head>
<body>
	<div id="wrapper">
		<div id="navigation">
			<div id="mainLogo">
				<br> <br> <a href="/" id="mainLogoTitle">냉장고를 부탁해 관리자</a>
			</div>
			<ul id="gnb">
				<li><a href="">레시피 관리</a>
					<ul class="submenu">
						<li><a href="/views/admin/adminRecipeUpload.jsp">레시피 등록</a></li>
						<li><a href="/recipeBoard/recipeBoardAllSelect.do">레시피 조회 및 관리</a></li>
					</ul></li>
				<li><a href="">문의/리뷰 관리</a>
					<ul class="submenu">
						<li><a href="/customerServiceCenter/noticeAdmin.do">공지사항 관리</a></li>
						<li><a href="/customerServiceCenter/qnaAdmin.do">자주하는 질문 관리</a></li>
					</ul></li>

				<li><a href="">회원 관리</a>
					<ul class="submenu">
						<li><a href="/admin/memberAllList.do">일반 회원 관리</a></li>
						<li><a href="/admin/blackList.do">블랙리스트 관리</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</body>
</html>