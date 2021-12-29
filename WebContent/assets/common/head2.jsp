<%@page import="kr.co.rfy.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>

.mypage {
    display: inline-block;
    margin-right: 35px;
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
a { text-decoration:none !important }
</style>
<%
     Member m = (Member)session.getAttribute("member");
%>

    <div id="wrapper">
        <div class="header fixed-top" style="position:fixed;background-color: white">
            <div class="top">
                <div class=top-img><img src="/assets/common/images/topicon.png" alt="" width="18px" height="25px"></div>
                <span class="top1 align">마이냉장고에 나만의 식재료를 등록하고 레시피를 추천 받아 보세요!</span>
            </div>
            <div class="header1">
                <a href="" class="logo">
                    <img src="/assets/common/images/main%20logo.png" alt="">
                </a>
                 <div class="box-user">
               		<div id="mypage">
	                    <a href="" class="mypage">
	                    	<img src="/assets/common/images/headericon1.png" alt="" width="23px" height="28px">
	        	        </a>
       	    	    </div>
                 <%if(m==null) {%>
                 <div>
	                <a href="/views/member/memberLogin.jsp" class="login" style="text-decoration: 'none'">
	                    <img src="/assets/common/images/headericon2.png" alt="" width="23px" height="28px">
	                    로그인
	                </a>
                </div>
                <%}else{ %>
                <div id="myMenu">
                	<a href="#" class="myMenu login" style="text-decoration: 'none'">
	                    <img src="/assets/common/images/headericon2.png" alt="" width="23px" height="28px">
	                    <%=m.getUserName() %>님 
	                </a>    
                    <ul class="myMenuSub">
                    	<li><a href="/views/member/memberUpdateCheck.jsp">회원정보 수정</a></li>
                   		<li><a href="/views/member/memberPwdCheck.jsp">비밀번호 변경</a></li>
                   		<li><a href="/member/memberLogout.do">로그아웃</a></li>
                    </ul>
	            </div>    
                	<%if(m.getRoll().substring(0,2).equals("AD")) {%>
                <div>	
                	<a href="/recipeBoard/recipeBoardAllSelect.do" class="admin">
	            	    <img src="/assets/common/images/headericon3.png" width="15px" height="28px"> 관리자 페이지
	               	</a>
	            </div>    		
                	<%} %>
                <%} %> 
                </div>
                
            </div>

            <div class="navibar container-fluid">
                <nav class="navbar navbar-expand-lg nav-distance">
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active nav-about" href="#">ABOUT</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active nav-recipe" href="#">레시피</a>
                               <ul class="submenu">
									<li><a href="/recipe/recipeBoard/selectAll.do">모두보기</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=hansik ">한식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=yangsik ">양식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=ilsik & ">일식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=jungsik ">중식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=bunsik ">분식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=vege ">채식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=dite ">다이어트</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=banchan ">밑반찬</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=annju ">안주</a></li>
							</ul></li>

                            <li class="nav-item">
                                <a class="nav-link active nav-info" href="#">고객센터</a>
                                <ul class="submenu">
                                    <li><a href="">공지사항</a></li>
                                    <li><a href="">자주하는질문</a></li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active nav-mypage" href="#">마이냉장고</a>
                            </li>
                        </ul>
                    </div>
                </nav>
                <form action="/search/recipe.do" method="get" id="search">

<div class="searchbar">
<input type="text" size="30" name="keyword" class="search_input"/>
<input type="hidden" name="type" value="latest"/>
<label for="searchSubmit" class="search_icon" style="cursor: pointer;"><i class="fas fa-search search-icon"></i></label>
<input type="submit" id="searchSubmit" style="display:none;"/>
</div>
			</form>
            </div>
        </div>