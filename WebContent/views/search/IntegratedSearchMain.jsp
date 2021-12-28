<%@page import="kr.co.rfy.recipe.vo.Recipe"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&family=Roboto:ital,wght@0,100;1,100&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <%-- jQuery 라이브러리 --%>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <title>main</title>
    <link rel="stylesheet" type="text/css" href="WebContent/assets/css/main.css">
	<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
	integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
	crossorigin="anonymous">



<body>

<jsp:include page="/assets/css/search.css"></jsp:include>

<style>

.big-title{
text-align:center;
font-size:20px;
font-weight:bold;
letter-spacing : 5px;
}

.small-title{
text-align:center;
font-size: 15px;
font-weight:bolder;
}
.cate{
border: 1px solid white;
width:95%;
text-align: right;
    

}


}
.tt{
width:100%;
height:50%;
text-align:center;

}


.contents {
    width: 100%;
    max-width: 1320px;
    height: 1400px;
    margin: 33px auto 50px auto;

}

.search {
	width: 100%;
	display: inline-block;
	
}


.search-bar {
	margin: 10px 0px 10px 3px;
	width: 300px;
	height: 30px;
	border: 1px solid black;
	border-radius: 5px 5px;
	display: inline-block;
	float:right;

}

.search-input {
	color: black;
	border: 0;
	outline: 0;
	background: none;
	border-color: #7FB292;
	width: 90%;
	caret-color: transparent;
	line-height: 28px;
	padding: 0 10px;
	font-size: 13px;
}

.search-icon {
	height: 15px;
	width: 10px;
	color: #7FB292;
	text-decoration: none;
	border: 1px solid white;
	
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
.contents2-main-1 {
    width: 100%;
    max-width: 1280px;
    height: 418px;

    margin: 35px 0px 44px 0px
    
}
.color{
color: #5D9A71;
text-align:center;
}
.sun{

color:gray;
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
    float:right;
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


</style>
<jsp:include page="/assets/common/head.jsp"></jsp:include>


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
<%
//페이징 처리되어 넘어온 데이터를 가져와야 함
HashMap<String, Object> pageDataMap = (HashMap<String, Object>)request.getAttribute("pageDataMap");

ArrayList<Recipe> list = (ArrayList<Recipe>)pageDataMap.get("list");
String pageNavi = (String)pageDataMap.get("pageNavi");
int count = (int)pageDataMap.get("count");
int currentPage = (int)request.getAttribute("currentPage");

String keyword = (String)request.getAttribute("keyword");
String type =(String)request.getAttribute("type");

%>

<%if(!list.isEmpty()) { %>
<br><br><br><br>
<div class="tt">
<div class="big-title">l SEARCH RESULTS l</div><br>
          
          <div class="small-title">총<span class="color"> <%=count %> </span>개의 맛있는 <span class="color"><%if(keyword != null){ %>
															<%=keyword %>
															<%} %></span> 레시피가 있습니다.</div>
    
</div>


<br><br><br>


<div class="contents">
<hr>
<div class="cate">
<a class="sun" id="latest" href="/search/recipe.do?keyword=<%=keyword%>&currentPage=<%=currentPage%>&type=latest">최신순</a>
<span class="sun"> | </span>
<a class="sun" id="likeNo" href="/search/recipe.do?keyword=<%=keyword%>&currentPage=<%=currentPage%>&type=like">추천순</a>   

<script>
	$(document).ready(function(){
		var str = '<%=type%>';
		if(str=='latest')
		{
			$('#latest').css('color','#7FB292').css('font-weight','bold');
		}else{
			$('#likeNo').css('color','#7FB292').css('font-weight','bold');
		}
		
	});
</script>


</div>

<div class ="ww">
 <div class="contents2-main-1">
             <%for(Recipe r:list) {%>
                        <div class="recipe_content">
                            <a href="/recipe/recipeSelectContent.do?boardNo=<%=r.getBoard_no()%>&currentPage=<%=currentPage %>" style="text-decoration: none; color: inherit;">
                                <div class="image" style="text-align: center"><img src="<%=r.getFile_path()%>" width="278" height="278" >
                           
                                </div>
                               
                                <div class="subtitle" style="text-align:center" ><%=r.getSubtitle() %></div>
                                <div class="title" style="text-align:center"><b><%=r.getTitle() %></b></div>
                                <div class="menu"  style="color:gray">
                                    <div class="menu_level" style="transform: translateX(45px)"> 
                                    <img src="/assets/images/Level.png"  width="20px" height="20px" class="img">
                                    <span ><%=r.getLevel_name() %></span>
                                    </div>
                                    <div class="menu_time" style="transform: translateX(-15px)">
                                        <img src="/assets/images/clock_time.png" width="20px" height="20px" class="img">
                                        <span><%=r.getTime_name() %></span>
                                    </div>
                                </div>
                                <div class="empty" style="text-align:center"><%=r.getUser_id() %></div>
                            </a>
                        </div>
                        <%} %>

			
</div>
</div>
</div>

<div class="layout-pagination">
						<div class="page-navi">
							<nav aria-label="Page navigation example">
							<ul class="pagination justify-content-center mt-5">
								<%=pageNavi %>
							</ul>
							</nav>
						</div>
					</div>
					
					<%}else{ %>
					<br><br><br><br>
<div class="tt">
<div class="big-title">ㅣSEARCH RESULTSㅣ</div><br>
           
         <div class="small-title">총<span class="color"> <%=count %> </span>개의 맛있는 <span class="color"><%if(keyword != null){ %>
															<%=keyword %>
															<%} %></span> 레시피가 있습니다.</div>
</div>
					
<br><br><br>

<div class="contents">
<hr>
</div>
					
					<%} %>
					

<jsp:include page="/assets/common/foot.jsp"></jsp:include>
