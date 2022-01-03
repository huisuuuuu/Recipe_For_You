<%@page import="kr.co.rfy.mybox.model.vo.RecipeWithFile"%>
<%@page import="java.util.LinkedList"%>
<%@page import="kr.co.rfy.recipeBoard.model.vo.OurRecipe"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.rfy.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
	integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
	crossorigin="anonymous">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&family=Roboto:ital,wght@0,100;1,100&display=swap" rel="stylesheet">
    <title>냉장고를 부탁해</title>
<style>
@charset "UTF-8";

div {
	box-sizing: border-box;
}

#wrapper {
	width: 100%;
	background-color: #fff;
}

.header {
	position: relative;
	width: 100%;
	height: 200px;
	border-bottom: 1px solid #E0E0E0;
}

.top {
	width: 100%;
	height: 35px;
	background-color: #5c9970;
	text-align: center;
}

.top-img {
	display: inline-block;
}

.top1 {
	font-family: 'Noto Sans KR', sans-serif;
	font-size: 13px;
	line-height: 35px;
	color: #fff;
}

.header1 {
	width: 100%;
	max-width: 1280px;
	height: 105px;
	text-align: center;
	margin: 0 auto;
	position: relative;
	padding: 30px 0;
}

.logo {
	width: 20%;
	height: 47px;
}

.box-user {
	position: absolute;
	top: 40px;
	right: 0;
}

.mypage {
	display: inline-block;
	margin-right: 35px;
}

.login {
	display: inline-block;
	margin-right: 35px;
}

.admin {
	display: inline-block;
	margin-right: 35px;
}

.navibar {
	width: 100%;
	max-width: 1280px;
	height: 60px;
	position: relative;
	padding: 0;
	text-align: center;
	margin: 0 auto;
}

.navbar {
	width: 50%;
	min-width: 550px;
	padding: 0;
	float: left;
	line-height: 30px;
	z-index: 999;
}

.navbar>ul {
	z-index: 999;
	float: left;
}

.navbar>ul::after {
	clear: both;
	display: block;
}

.navdistance {
	width: 100%;
	height: 100%;
}

.nav-item {
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
	z-index: 999;
}

#navbarSupportedContent {
	width: 100px;
	list-style: none;
	float: left;
	z-index: 999;
}

.nav-about {
	width: 100px;
	height: 60px;
	color: #5E5E5E;
}

.nav-recipe {
	width: 100px;
	height: 60px;
	color: #5E5E5E;
}

.nav-info {
	width: 100px;
	height: 60px;
	color: #5E5E5E;
}

.nav-mypage {
	width: 100px;
	height: 60px;
	color: #7FB292;
	margin-left: 20px;
	position: relative;
}

.nav-mypage:hover {
	color: #7FB292;
}

.nav-mypage:before {
	content: "";
	display: block;
	width: 2px;
	height: 30px;
	background-color: #E0E0E0;
	position: absolute;
	left: -20px;
	bottom: -5px;
	z-index: 999;
		
/* 	opacity: 1;
	visibility: visible; */
}

.nav-item:hover>a {
	color: #7FB292;
}

.nav-item:hover>.submenu {
	width: 100%;
	height: 100%;
	transition-property: height;
	transition-duration: 1s;
	border: 1px solid #5E5E5E;
	position: relative;
	
 	opacity: 1;
	visibility: visible;
}

.nav-item:hover>.myMenuSub>li>a:hover {
	color: #7FB292;
}
.nav-item>ul{
	float: left;
}
.submenu {
	margin-right: 10px;
	padding: 0px;
	height: 0px;
	/* overflow: hidden; */
	transition-property: height;
	background-color: white;
	float: left;
	
	position: relative;
	
 	opacity: 0;
	visibility: hidden;
}

.submenu>* {
	float: left;
}

.submenu>li {
	list-style: none;
	width: 100%;
	float: left;
	text-align: center;
	z-index: 999;
}

.submenu>li>a {
	display: block;
	width: 100%;
	height: 35px;
	text-decoration: none;
	text-align: center;
	font-family: 'Noto Sans KR', sans-serif;
	font-size: 14px;
	/* margin-top: 10px; */
	color: #5E5E5E;
	float: left;
}

.nav-line {
	height: 26px;
	float: left;
	position: absolute;
	top: 40%;
	left: 30%;
}

.box-search {
	width: 50%
}

.searchbar {
	position: relative;
	margin-bottom: auto;
	margin-top: 20px;
	margin-left: 0px;
	width: 50%;
	height: 30px;
	border-radius: 30px;
	border-color: #7FB292;
	border-style: solid;
	float: right;
}

.search_input {
	color: black;
	border: 0;
	outline: 0;
	background: none;
	border-color: #7FB292;
	width: 100%;
	caret-color: transparent;
	line-height: 25px;
	padding: 0 10px;
}

.search_icon {
	height: 12px;
	width: 10px;
	position: absolute;
	right: 3%;
	border-radius: 50%;
	color: #7FB292;
	text-decoration: none;
}

.form-control {
	width: 600px;
	border-radius: 20px 20px;
	margin: 10px 0 0 0;
}



.myMenu, .login, .admin {
	color: #5E5E5E;
	font-family: 'Noto Sans KR', sans-serif;
	font-size: 12px;
	font-weight: bold;
	line-height: 10px;
	letter-spacing: 1px;
	text-align: center;
	box-sizing: border-box;
}

#myMenu:hover>.myMenuSub {
	opacity: 1;
	visibility: visible;
}

.myMenuSub:hover>ul {
	opacity: 1;
	visibility: visible;
}

.myMenuSub {
	padding: 5px;
	background-color: white;
	position: relative;
	z-index: 999;
	opacity: 0;
	visibility: hidden;
	border: 1px solid #5E5E5E;
}

.myMenuSub>li {
	list-style: none;
}

.myMenuSub>li>a {
	width: 100%;
	height: 20px;
	text-decoration: none;
	text-align: center;
	font-family: 'Noto Sans KR', sans-serif;
	font-size: 12px;
	line-height: 30px;
	color: #5E5E5E;
	background-color: white;
}

.box-user>div {
	float: left;
}

a {
	text-decoration: none;
	color: #5E5E5E;
}

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

.contents {
    width: 100%;
    max-width: 1320px;
    height: 2350px;
    margin: 33px auto 50px auto;
	
}

.contetns-sub {
    width: 100%;
    max-width: 1280px;
    margin: 15px auto 40px auto;
  

}

.contents-top {
    display: inline-block;
}

.contents1-title {
    width: 23.125%;
    margin: 52px 492px 18px 492px;
    text-align: center;
    font-size: 20px;
    font-family: 'Noto Serif KR', sans-serif;
    font-weight: 700;
    letter-spacing: 0.255em;
}

.contents1-subtitle {
    width: 35.16%;
    margin: 0 415px 16px 415px;
    text-align: center;
    font-size: 16px;
    font-family: 'Noto Serif KR', sans-serif;
    font-weight: 700;
    color: #5E5E5E;
}

.contents1-button-more {
    width: 6.25%;
    height: 40px;
    border: 1px solid #7FB292;
    text-align: center;
    padding-top: 8px;
}

.contents1-button-more > a {
    color: #7FB292;
    font-size: 14px;
}

.contents1-image {

    height: 550px;
    width: 100%;
    margin: 32px 0px 15px 0px;
}

.contents1-image-1 {
    float: left;
}

.contents1-image-2 {
    margin: 0px 35px 0px 35px;
    float: left;
}

.contents1-image-3 {
    margin: 0px 0px 31px 986px;
}

.contents1-image-4 {
    float: right;
}


.contents2-title {
    width: 26.56%;
    margin: 61px 470px 20px 470px;
    text-align: center;
    font-size: 20px;
    font-family: 'Noto Serif KR', sans-serif;
    font-weight: 700;
    letter-spacing: 0.255em;
}

.contents2-button-more {
    width: 6.25%;
    height: 40px;
    border: 1px solid #7FB292;
    text-align: center;
    padding-top: 8px;
}

.contents2-button-more > a {
    color: #7FB292;
    font-size: 14px;
}


   #content_recipe_wrapper
        {
           
            width: 1280px;
            height: 1252px;
            margin: 0 auto;
         
            
        }
        .content_recipe
        {
            
            width: 100%;
            height: 417px;
           
           
              
        }
        .recipe_content
        {
           
            width: 285px;
            height: 418px;
            float: left;
            margin: 0px 10px 0px 20px;
            color:gray;
             
        }
        
        
        
        .image
        {
            width: 100%;
            height: 284px;
           
        }
    
        .subtitle
        {
           
            width: 100%;
            height: 33px;
            
        }
         .title
        {
           
            width: 100%;
            height: 33px;
           
        }
         .menu
        {
           
            width: 100%;
            height: 33px;
            
           
        }
        .empty
        {
           
            width: 100%;
            height: 33px;
           
        }
        .menu_level
        {
           
            width: 50%;
            height: 100%;
            float: left;
			            
        }
         .menu_time
        {
           
            width: 50%;
            height: 100%;
            float: left;
            
        }
        .img
        {
            transform: translateX(35px);
        }
        
        .menu span
        {
            display: inline-block;
            transform: translateX(40px);
            
        }


.image {
  position:relative;
  
 }
 
.image:hover
	{
		filter: grayscale(60%) opacity(0.7);
		
	}
	
#explain {
  position:absolute;
  visibility:hidden; 
 }


.image:hover #explain  {
   visibility:visible;
   color:white;
   font-size:20px;
   transform: translate(70px,110px);
   
   
 }  

.contents1-image-1 {
  position:relative;

 }
 
#explain1 {
  position:absolute;
  visibility:hidden;
  top : 100px;
 }

.contetns1-image-1:hover #explain  {
   visibility:visible;
   color:white;
   font-size:30px;
   transform: translate(100px,110px);

 }



.footer {
    width: 100%;
    height: 200px;
    border-top: 1px solid #E0E0E0;
}
.footer1 {
    max-width: 1280px;
    margin-top: 20px;
}

.footer1>pre{
    font-size: 13px;
    text-align: left;
    font-family: 'Noto Sans KR', sans-serif;
}
.footer-bottom {
    width: 100%;
    background-color: #E4E4E4;
}

#footer-word>pre{
    max-width: 1280px;
    margin: 0 auto;
    text-align: left;
    font-size: 13px;
    font-family: 'Noto Sans KR', sans-serif;
    
}

.myMenu, .login, .admin {
    color: #5E5E5E;
    font-family: 'Noto Sans KR', sans-serif;
    font-size: 12px;
    font-weight: bold;
    line-height: 10px;
    letter-spacing: 1px;
    text-align: center;
	box-sizing: border-box;
}


#myMenu:hover > .myMenuSub {
	display: block;
}
.myMenuSub:hover > ul {
	display: block;
	
}
.myMenuSub{
	padding: 5px;
	background-color: white;
	position: relative;
	z-index:999;
	display: none;
	border: 1px solid #5E5E5E;
}
.myMenuSub > li {
    list-style: none;
}
.myMenuSub > li > a {
    width: 100%;
    height: 20px;
    text-decoration: none;
    text-align: center;
    font-family: 'Noto Sans KR', sans-serif;
    font-size: 12px;
    line-height: 30px;
    color: #5E5E5E;    
    background-color: white;
}
.box-user > div{
	float: left;
}

</style>

</head>
<body>
<%
     Member m = (Member)session.getAttribute("member");
	ArrayList<OurRecipe> bestRecipe = (ArrayList<OurRecipe>)request.getAttribute("bestRecipe");
	LinkedList<RecipeWithFile>recipeWithFileList = (LinkedList<RecipeWithFile>)request.getAttribute("recipeWithFileList");
	  
	int currentPage = (int)request.getAttribute("currentPage");
			
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
					<a href="/mybox/myboxList.do" class="mypage"> <img
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
							<li><a href="/customerServiceCenter/notice.do">공지사항</a></li>
							<li><a href="/customerServiceCenter/qna.do">자주하는질문</a></li>
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
        
        <div class="contents">
        	<div class="contents-top"><img src="/assets/common/images/contents-top.jpg" alt="" width="1320px"></div>
            
            <div class="contetns-sub">
            <div class="contents1-title">RECIPES FOR YOU</div>
            <div class="contents1-subtitle">마이냉장고에 재료를 입력하고 레시피를 추천받아보세요.</div>
            <div class="contents1-image">
               <div class="contents1-image-1"><a href="http://127.0.0.1/recipe/recipeSelectContent.do?boardNo=1&currentPage=1"><p id="explain1">${recipeWithFileList[0].title}</p><img src="${recipeWithFileList[0].file_path}" alt="" width="625px" height="550px"></a></div>
                <div class="contents1-image-2"><a href="http://127.0.0.1/recipe/recipeSelectContent.do?boardNo=43&currentPage=1"><p id="explain1">${recipeWithFileList[1].title}</p><img src="${recipeWithFileList[1].file_path}" alt="" width="290px" height="257px"></a></div>
                <div class="contents1-image-3"><a href="http://127.0.0.1/recipe/recipeSelectContent.do?boardNo=50&currentPage=1"><p id="explain1">${recipeWithFileList[2].title}</p><img src="${recipeWithFileList[2].file_path}" alt="" width="290px" height="260px"></a></div>
                <div class="contents1-image-4"><a href="http://127.0.0.1/recipe/recipeSelectContent.do?boardNo=57&currentPage=1"><p id="explain1">${recipeWithFileList[3].title}</p><img src="${recipeWithFileList[3].file_path}" alt="" width="615px" height="259px"></a></div>
            </div>
            <div class="contents2-title">BEST RECIPES</div>
            <div class="contents2-button-more container"><a href="/recipe/recipeBoard/selectAll.do" type="button">더보기</a></div>
            
            <div id="content_recipe_wrapper" style="transform: translateY(20px)">
                     <div class="content_recipe" id="recipe1">
                   
                  <%if(m!=null) {%>   
         			<%for(OurRecipe bRe :bestRecipe){ %>
                        <div class="recipe_content">																	
                        	<a href="/recipe/recipeSelectContent.do?boardNo=<%=bRe.getBoardNo()%>&currentPage=<%=currentPage %>&userId=<%=bRe.getUserId()%>" style="text-decoration: none; color: inherit;">
	                            <div class="image" style="text-align: center;"><p id ="explain"> <%=bRe.getTitle()%></p><img src="<%=bRe.getFilePath()%>" width="278" height="278" class="allImage" ></div>
                            </a>
                        </div>
                       <%} %>
                 <%}else{ %>
                 		<%for(OurRecipe bRe :bestRecipe){ %>
                        <div class="recipe_content">																	
                        	<a href="/recipe/recipeSelectContent.do?boardNo=<%=bRe.getBoardNo()%>&currentPage=<%=currentPage %>" style="text-decoration: none; color: inherit;">
	                            <div class="image" style="text-align: center;"><p id ="explain"> <%=bRe.getTitle()%></p><img src="<%=bRe.getFilePath()%>" width="278" height="278" class="allImage" ></div>
                            </a>
                        </div>
                       <%} %>
                 
                 
                 
                 
                 <%} %>

        </div>
        </div>
            
            
            
            
            
            </div>
            </div>
        </div>
        
      
        	
        
        
        <%@include file="/views/common/quickbar.jsp"%>
        <div class="footer">
            <div class="footer1 container">
            <pre>
(주)컬쳐히어로
            
            
상호:(주)컬쳐히어로   대표자:양준규  개인정보관리책임자:장호진  사업자 등록번호:144-81-35400
            
통신판매업 신고:제 2015-경기성남-1940호  전화:1833-8307  팩스:031-8017-1800
            
주소:(13487) 경기도 성남시 분당구 삼평동625 판교세븐벤처밸리1단지 제3동 1001호 이메일:commerce@culturehero.net
            </pre>
            </div>
            <div class="footer-bottom">
               <div id="footer-word">
                <pre>      이용약관   개인정보처리방침   공지사항   자주묻는질문   광고/제휴문의:contact@culturehero.net</pre>
                </div>
            </div>
        </div>
    </div>



    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->
</body>
</html>