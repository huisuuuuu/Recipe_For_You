<%@page import="kr.co.rfy.member.vo.Member"%>
<%@page import="kr.co.rfy.recipeBoard.model.vo.OurRecipe"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


	<%--제이쿼리 라이브러리 --%>   
    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>


    <%-- Required meta tags --%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--font--%>
    <style>    
    @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&family=Noto+Serif+KR:wght@200&display=swap');
    </style>
   
   
    <%-- Bootstrap CSS --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
   <link rel="stylesheet" type="text/css" href="stylesheet/recipe_navi.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&family=Roboto:ital,wght@0,100;1,100&display=swap" rel="stylesheet">
    <title>header</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/recipeHeaderFooter.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/ourRecipe.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/ourRecipeNavi.css">


</head>



<body>

	<% //Servlet에서 넘겨준 값 받아오기
	HashMap<String, Object> pageDataMap =(HashMap<String, Object>)request.getAttribute("pageDataMap");
	ArrayList<OurRecipe> list =(ArrayList<OurRecipe>) pageDataMap.get("list");
	String pageNavi =(String)pageDataMap.get("pageNavi");	
	int currentPage = (int)request.getAttribute("currentPage");
		
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
                    <img src="/assets/common/images//main%20logo.png" alt="">
                </a>
                <div class="box-user">
                   <a href="" class="mypage">
                    <img src="/assets/common/images//headericon1.png" alt="" width="23px" height="28px">
                </a>
                <a href="" class="login">
                    <img src="/assets/common/images//headericon2.png" alt="" width="80px" height="30px">
                </a>
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
                                <a class="nav-link active nav-recipe" href="/recipe/recipeBoard/selectAll.do">레시피</a>
                                <ul class="submenu">
                                    <li><a href="">모두보기</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=hansik">한식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=yangsik">양식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=ilsik">일식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=jungsik">중식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=bunsik ">분식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=vege">채식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=dite">다이어트</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=banchan">밑반찬</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=annju">안주</a></li>
                                </ul>
                            </li>

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
                <div class=" box-search d-flex justify-content-center h-100">
                    <div class="searchbar">
                        <input class="search_input" type="text" name="" placeholder="">
                        <a href="" class="search_icon"><i class="fas fa-search"></i></a>
                    </div>
                </div>

            </div>
        </div>
        
        <div class="contents">
            <div id=menuNavi_wrapper>
            <div id="menu_content1"></div>
            <div id="menu_content2">
                <H3 style=text-align:center>|  OUR RECIPE  |</H3>
                <H6>냉장고 속 재료로 맛있는 음식을 만들어보세요</H6>
                <table border="2px" width=100% height ="87px">
                    <tr>
                        <td><a href="">모두보기</a></td>
                        <td><a href="/recipe/recipeBoardSelectList.do?recipe=hansik">한식</a></td>
                        <td><a href="/recipe/recipeBoardSelectList.do?recipe=yangsik">양식</a></td>
                        <td><a href="/recipe/recipeBoardSelectList.do?recipe=ilsik">일식</a></td>
                        <td><a href="/recipe/recipeBoardSelectList.do?recipe=jungsik">중식</a></td>
                    </tr>
                     <tr>
                        <td><a href="/recipe/recipeBoardSelectList.do?recipe=bunsik">분식</a></td>
                        <td><a href="/recipe/recipeBoardSelectList.do?recipe=vege">채식</a></td>
                        <td><a href="/recipe/recipeBoardSelectList.do?recipe=dite">다이어트</a></td>
                        <td><a href="/recipe/recipeBoardSelectList.do?recipe=banchan">밑반찬</a></td>
                        <td><a href="/recipe/recipeBoardSelectList.do?recipe=annju">안주</a></td>
                        
                    </tr>
                </table>
                
            </div>
            <div id="menu_content3">
                <div id="menu_content3_top"></div>
                <div id="menu_content3_bottom">
          <%--       <%if(m!=null) {%>  --%>
                   <button type="button" class="btn btn-success" id="myRecipeBtn">MY Recipe</a></button>
                    <button type="button" class="btn btn-success">레시피 등록</button>
                
          <%--        <%} %>     --%>
                 	  
                </div>
            </div>
        </div>

		<script>
			$('#myRecipeBtn').click(function(){
				
				
				location.replace("/recipe/recipeBoard/myRecipe.do");
				
			});




		</script>



        
                 <div id="content_wrapper">
          
           <div id="recipe_search">
              <form action=" " method="">
                   <select>
                       <option value="latest_desc">최신순</option>
                       <option value="like_desc">추천순</option>
                       <option value="level_asc">난이도</option>
                       <option value="time_asc">조리시간</option>
                   </select>
                </form>               
           </div>
            <div id="content_recipe_wrapper">
                     <div class="content_recipe" id="recipe1">
               
               			<%for(OurRecipe o:list) {%>
                        <div class="recipe_content">
                        	<a href="/recipe/recipeSelectContent.do?boardNo=<%=o.getBoardNo()%>&currentPage=<%=currentPage %>" style="text-decoration: none; color: inherit;">
	                            <div class="image" style="text-align: center"><img src="<%=o.getFilePath()%>" width="278" height="278" ></div>
	                            <div class="subtitle" style="text-align:center" ><%=o.getSubTitle() %></div>
	                            <div class="title" style="text-align:center"><b><%=o.getTitle() %></b></div>
	                            <div class="menu"  style="color:gray">
	                                <div class="menu_level" style="transform: translateX(45px)"> 
	                                <img src="/assets/images/Level.png"  width="20px" height="20px" class="img">
	                                <span ><%=o.getLevelName() %></span>
	                                </div>
	                                <div class="menu_time" style="transform: translateX(-15px)">
	                                    <img src="/assets/images/clock_time.png" width="20px" height="20px" class="img">
	                                    <span><%=o.getTimeName() %></span>
	                                </div>
	                            </div>
	                            <div class="empty" ></div>
                            </a>
                        </div>
                        <%} %>
                        
                      
           
         
                
            </div>

        </div>
        </div>
        
        <div id="content_fotter">
            <div id="page_navi">
            	<%=pageNavi %>
            
            </div>
        </div>
  
            
            <!--컨텐츠 </div>-->     
        </div>
        
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