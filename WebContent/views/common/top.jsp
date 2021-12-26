<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<!-- BootStrap -->
<!-- boostrap 라이브러리-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
	integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
	crossorigin="anonymous">

<title>레시피 조회 및 관리</title>

<style>
* {
	font-family: 'Noto Sans KR', sans-serif;
}

div {
	box-sizing: border-box;
}

#wrapper {
	width: 100%;
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
	width: 85%;
	height: 850px;
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
</head>
<body>
	<div id="wrapper">
		<div id="header"></div>
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
						<li><a href="">공지사항 관리</a></li>
						<li><a href="">자주하는 질문 관리</a></li>
					</ul></li>

				<li><a href="/admin/memberAllList.do">회원 관리</a>
					<ul class="submenu">
						<li><a href="/admin/memberAllList.do">일반 회원 관리</a></li>
						<li><a href="/admin/blackList.do">블랙리스트 관리</a></li>
					</ul></li>
			</ul>
		</div>
		<div id="content">
			<div class="page_section">
				
					