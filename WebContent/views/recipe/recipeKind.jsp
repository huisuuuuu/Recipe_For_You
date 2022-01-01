<%@page import="kr.co.rfy.member.model.vo.Member"%>
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

	
    <link rel="stylesheet" type="text/css" href="/assets/css/ourRecipe.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/ourRecipeNavi.css">

<style>
	
.image {
  position:relative;
  
 }
 
.image:hover
	{
		filter: grayscale(100%) opacity(0.8);
		
	}
	
#explain {
  position:absolute;
  visibility:hidden;
 
 }


.image:hover #explain  {
   visibility:visible;
   color:white;
   font-size:30px;
   transform: translate(100px,110px);
   
 }  
 
    #menu_content2 td
        {
            font-family: 'Noto Serif KR', serif;
        }
        #menu_content2 td
        {  
            width: 80px;
            height: 43px;
             border: 2px solid;
            border-color: #7FB292;

        }
       
        td{
            border: 2px solid;
            border-color: #7FB292;
            
        }
         table,tr,td 
        { 
           border-collapse: collapse;
            padding: 0px;
            margin: 0px;
            
        } 
 
	
</style>

</head>



<body>

	<% //Servlet에서 넘겨준 값 받아오기
	HashMap<String, Object> kindPageDataMap =(HashMap<String, Object>)request.getAttribute("kindPageDataMap");
	ArrayList<OurRecipe> list =(ArrayList<OurRecipe>) kindPageDataMap.get("list");
	String pageNavi =(String)kindPageDataMap.get("pageNavi");	
	int currentPage = (int)request.getAttribute("currentPage");
	String recipe=(String)request.getAttribute("recipe");
	String type = (String)request.getAttribute("type");	
	
	
	%>

 <%@include file="/views/common/header3.jsp"%>
 <%@include file="/views/common/quickbar.jsp"%>
   
        
        <div class="contents">
            <div id=menuNavi_wrapper>
            <div id="menu_content1"></div>
            <div id="menu_content2">
                <H3 style=text-align:center>|  OUR RECIPE  |</H3>
                <H6>냉장고 속 재료로 맛있는 음식을 만들어보세요</H6>
                <table border="2px" width=100% height ="87px" id="tavbleNavi">
                    <tr>
                        <td><a  href="/recipe/recipeBoard/selectAll.do">모두보기</a></td>
                        <td><a  href="/recipe/recipeBoardSelectList.do?recipe=hansik">한식</a></td>
                        <td><a  href="/recipe/recipeBoardSelectList.do?recipe=yangsik">양식</a></td>
                        <td><a  href="/recipe/recipeBoardSelectList.do?recipe=ilsik">일식</a></td>
                        <td><a  href="/recipe/recipeBoardSelectList.do?recipe=jungsik">중식</a></td>
                    </tr>
                     <tr>
                        <td><a  href="/recipe/recipeBoardSelectList.do?recipe=bunsik">분식</a></td>
                        <td><a  href="/recipe/recipeBoardSelectList.do?recipe=vege">채식</a></td>
                        <td><a  href="/recipe/recipeBoardSelectList.do?recipe=dite">다이어트</a></td>
                        <td><a  href="/recipe/recipeBoardSelectList.do?recipe=banchan">밑반찬</a></td>
                        <td><a  href="/recipe/recipeBoardSelectList.do?recipe=annju">안주</a></td>
                        
                    </tr>
                </table>
	
             
                
            </div>
            <div id="menu_content3">
                <div id="menu_content3_top"></div>
                <div id="menu_content3_bottom">
                
                <%if(m!=null) {%> 
                   <button type="button" class="btn btn-success" id="myRecipeBtn">MY Recipe</a></button>
                    <button type="button" class="btn btn-success" id="recipeUploadBtn">레시피 등록</button>
                
                <%} %>  

                </div>
            </div>
        </div>
        
        <script>
			$('#myRecipeBtn').click(function(){
				
				
				location.replace("/recipe/recipeBoard/myRecipe.do");
				
			});




		</script>
				<script>
			$('#recipeUploadBtn').click(function(){
				
				location.replace("/views/recipe/userRecipePostUpload.jsp");
				
			});
		</script>
		
        
        
        
        
                 <div id="content_wrapper">
          
           <div id="recipe_search">
               <form action="/recipe/recipeBoardSelectList.do" id="recipeSearch"  method="get" onchange="searchAllPost()">
                   <select name="type">
                    <%if(type.equals("latest_desc")) {%>
                       <option value="latest_desc" selected >최신순</option>
                       <option value="like_desc" >추천순</option>
                       <option value="level_asc">난이도</option>
                       <option value="time_asc">조리시간</option>

					<%}else if(type.equals("like_desc")){ %>
					   <option value="latest_desc" selected>최신순</option>
                       <option value="like_desc" selected>추천순</option>
                       <option value="level_asc">난이도</option>
                       <option value="time_asc">조리시간</option>
			
					<%}else if(type.equals("level_asc")){ %>
					   <option value="latest_desc">최신순</option>
                       <option value="like_desc">추천순</option>
                       <option value="level_asc" selected>난이도</option>
                       <option value="time_asc">조리시간</option>

					<%}else if(type.equals("time_asc")){ %>
					   <option value="latest_desc">최신순</option>
                       <option value="like_desc">추천순</option>
                       <option value="level_asc">난이도</option>
                       <option value="time_asc" selected>조리시간</option>

					<%} %>
                   </select>
                   <input type="hidden" name="recipe" value="<%=recipe%>"/>
                </form>               
           </div>
           
            <%--검색 조건을 선택 시 한번 더 submit 동작--%>
           <script>
           	
           	function searchAllPost(){
           	
           		recipeSearch.submit();
           	}
           	
           	
           </script>
           
           
           
           
            <div id="content_recipe_wrapper">
                     <div class="content_recipe" id="recipe1">
               
               <%if(m!=null) {%>
               			<%for(OurRecipe o:list) {%>
                        <div class="recipe_content">
                        	<a href="/recipe/recipeSelectContent.do?boardNo=<%=o.getBoardNo()%>&currentPage=<%=currentPage%>&userId=<%=m.getUserId() %>" style="text-decoration: none; color: inherit;">
	                            <div class="image" style="text-align: center;"><p id ="explain">추천수 <%=o.getLikeNum() %></p><img src="<%=o.getFilePath()%>" width="278" height="278" class="allImage" ></div>
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
	                            <div class="empty" style="text-align:center"><%=o.getUserName() %></div>
                            </a>
                        </div>
                        <%} %>
                   <%}else{ %>     
                      <%for(OurRecipe o:list) {%>
                        <div class="recipe_content">
                        	<a href="/recipe/recipeSelectContent.do?boardNo=<%=o.getBoardNo()%>&currentPage=<%=currentPage%>" style="text-decoration: none; color: inherit;">
	                            <div class="image" style="text-align: center;"><p id ="explain">추천수 <%=o.getLikeNum() %></p><img src="<%=o.getFilePath()%>" width="278" height="278" class="allImage" ></div>
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
	                            <div class="empty" style="text-align:center"><%=o.getUserName() %></div>
                            </a>
                        </div>
                        <%} %>
           
         
         
         
         
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
        

	<%@include file="/views/common/footer.jsp"%>


</body>
</html>