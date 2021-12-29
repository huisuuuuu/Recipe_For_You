	<%@page import="kr.co.rfy.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- Required meta tags -->
<meta charset="utf-8">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<!-- font awesome CSS -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
	integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
	crossorigin="anonymous">
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/assets/css/header2.css">
<title>냉장고를 부탁해</title>

<style>
#GNB-area {
	width: 600px;
	height: 70px;
	margin: 0 auto;
}

#GNB-area>ul {
	width: 100%;
	height: 100%;
}

#GNB-area>ul {
	list-style-type: none;
	float: left;
}

#GNB-area>ul>li>ul>li {
	list-style-type: none;
	text-align: center;
	padding: 0px;
	text-align: center;
	font-family: 'Noto Sans KR', sans-serif;
	font-size: 14px;
	color: #5E5E5E;
	height: 35px;
	width: 100%;
	line-height: 35px;
}

#GNB-area>ul>li>ul {
	list-style-type: none;
	text-align: center;
	padding: 0px;
	opacity: 0;
	visibility: hidden;
}

#GNB-area>ul>li {
	width: 25%;
	height: 100%;
	float: left;
	color: #5E5E5E;
	font-family: 'Noto Sans KR', sans-serif;
	font-size: 15px;
	font-weight: bold;
	letter-spacing: 1.8px;
	text-align: center;
	float: left;
	padding-top: 20px;
	width: 100px;
	list-style: none;
}

#GNB-area>ul>li:hover>ul {
	opacity: 1;
	visibility: visible;
}

#GNB-area>ul>li>ul:hover {
	opacity: 1;
	visibility: visible;
}

.GNB-float {
	float: left;
	width: 25%;
	height: 100%;
}

#centerul {
	margin: 0 auto;
	text-align: center;
}

.greenColor {
	color: #7FB292;
	text-decoration: none;
}

.ul-list {
	margin-top: 5px;
	border: 1px solid #5E5E5E;
	background-color: white;
}

#myBox:before {
	content: "";
	display: block;
	width: 2px;
	height: 30px;
	background-color: #E0E0E0;
	position: absolute;
	left: 350px;
	bottom: 20px;
	z-index: 999;
}

#myBox {
	padding-left: 20px;
}

.login:hover {
	color: #7FB292;
}

.hoverColor:hover {
	color: #7FB292;
}
</style>
<%
	Member m = (Member) session.getAttribute("member");
%>
<div id="wrapper">
	<div class="header">
		<div class="top">
			<div class=top-img>
				<img src="/assets/common/images/topicon.png" alt="" width="18px"
					height="25px">
			</div>
			<span class="top1">마이냉장고에 나만의 식재료를 등록하고 레시피를 추천 받아 보세요!</span>
		</div>
		<div class="header1">
			<a href="/" class="logo"> <img
				src="/assets/common/images/main%20logo.png" alt="">
			</a>
			<div class="box-user">
				<div id="mypage">
					<a href="" class="mypage"> <img
						src="/assets/common/images/headericon1.png" alt="" width="23px"
						height="28px">
					</a>
				</div>
				<%
					if (m == null) {
				%>
				<div>
					<a href="/views/member/memberLogin.jsp" class="login"
						style="text-decoration: 'none'"> <img
						src="/assets/common/images/headericon2.png" alt="" width="23px"
						height="28px"> 로그인
					</a>
				</div>
				<%
					} else {
				%>
				<div id="myMenu">
					<a href="#" class="myMenu login" style="text-decoration: 'none'">
						<img src="/assets/common/images/headericon2.png" alt=""
						width="23px" height="28px"> <%=m.getUserName()%>님
					</a>
					<ul class="myMenuSub">
						<li><a href="/views/member/memberUpdateCheck.jsp">회원정보 수정</a></li>
						<li><a href="/views/member/memberPwdCheck.jsp">비밀번호 변경</a></li>
						<li><a href="/member/memberLogout.do">로그아웃</a></li>
					</ul>
				</div>
				<%
					if (m.getRoll().substring(0, 2).equals("AD")) {
				%>
				<div>
					<a href="/recipeBoard/recipeBoardAllSelect.do" class="admin login">
						<img src="/assets/common/images/headericon3.png" width="15px"
						height="28px"> 관리자 페이지
					</a>
				</div>
				<%
					}
				%>
				<%
					}
				%>
			</div>

		</div>

		<div class="navibar">
			<nav class="navbar"> <!-- <div class="" id="navbarSupportedContent"> -->
			<div id="GNB-area">
				<ul id="centerul">
					<li class="GNB-float"><a href="#" class="hoverColor">ABOUT</a></li>
					<li class="GNB-float"><a href="#" class="hoverColor">레시피</a>
						<ul class="ul-list">
							<li><a href="/recipe/recipeBoard/selectAll.do">모두보기</a></li>
							<li><a href="/recipe/recipeBoardSelectList.do?recipe=hansik">한식</a></li>
							<li><a
								href="/recipe/recipeBoardSelectList.do?recipe=yangsik">양식</a></li>
							<li><a
								href="/recipe/recipeBoardSelectList.do?recipe=ilsik &">일식</a></li>
							<li><a
								href="/recipe/recipeBoardSelectList.do?recipe=jungsik">중식</a></li>
							<li><a href="/recipe/recipeBoardSelectList.do?recipe=bunsik">분식</a></li>
							<li><a href="/recipe/recipeBoardSelectList.do?recipe=vege">채식</a></li>
							<li><a href="/recipe/recipeBoardSelectList.do?recipe=dite">다이어트</a></li>
							<li><a
								href="/recipe/recipeBoardSelectList.do?recipe=banchan">밑반찬</a></li>
							<li><a href="/recipe/recipeBoardSelectList.do?recipe=annju">안주</a></li>
						</ul></li>
					<li class="GNB-float"><a href="#" class="hoverColor">고객센터</a>
						<ul class="ul-list">
							<li><a href="#">공지사항</a></li>
							<li><a href="#">자주하는질문</a></li>
						</ul></li>
					<li class="GNB-float" id="myBox"><a href="/mybox/myboxList.do"
						class="greenColor hoverColor"> 마이냉장고</a></li>
				</ul>
			</div>
			<!--                         <ul class="">
                            <li class="nav-item">
                                <a class="" href="#">ABOUT</a>
                            </li>
                            <li class="nav-item">
                                <a class="" href="#">레시피</a>
                                <ul class="submenu">
              						<li><a href="/recipe/recipeBoard/selectAll.do">모두보기</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=hansik">한식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=yangsik">양식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=ilsik &">일식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=jungsik">중식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=bunsik">분식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=vege">채식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=dite">다이어트</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=banchan">밑반찬</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=annju">안주</a></li>
                                </ul>
                            </li>

                            <li class="nav-item">
                                <a class="" href="#">고객센터</a>
                                <ul class="submenu">
                                    <li><a href="">공지사항</a></li>
                                    <li><a href="">자주하는질문</a></li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a class="nav-mypage" href="/mybox/myboxList.do">마이냉장고</a>
                            </li>
                        </ul> --> <!-- </div> --> </nav>
			<form action="/search/recipe.do" method="get" id="search">
				<div class="searchbar">
					<input type="text" size="30" name="keyword" class="search_input" />
					<input type="hidden" name="type" value="latest" /> <label
						for="searchSubmit" class="search_icon" style="cursor: pointer;"><i
						class="fas fa-search search-icon"></i></label> <input type="submit"
						id="searchSubmit" style="display: none;" />
				</div>
			</form>
		</div>
	</div>
</div>

