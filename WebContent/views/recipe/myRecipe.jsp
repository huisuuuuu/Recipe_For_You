<%@page import="kr.co.rfy.recipeBoard.model.vo.OurRecipe"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="kr.co.rfy.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

    
    <link rel="stylesheet" type="text/css" href="/assets/css/ourRecipe.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/ourRecipeNavi.css">





</head>
<body>

	<%
		HashMap<String, Object> myRecipeDataMap =(HashMap<String, Object>)request.getAttribute("myRecipeDataMap");
		ArrayList<OurRecipe> list =(ArrayList<OurRecipe>) myRecipeDataMap.get("list");
		String pageNavi =(String)myRecipeDataMap.get("pageNavi");	
		int currentPage = (int)request.getAttribute("currentPage");
	
	
	
	%>

   
       <%@include file="/views/common/header3.jsp"%>
       <%@include file="/views/common/quickbar.jsp"%>
       
        
        <div class="contents">
           <div id=menuNavi_wrapper>
            <div id="menu_content1"></div>
            <div id="menu_content2">
                <H3 style=text-align:center>|  My RECIPE  |</H3>
                <H6>내가 등록한 레시피를 확인 할 수 있어요</H6>
                
            </div>
            
            
        </div>  
                  <div id="content_wrapper">
          <%if(!list.isEmpty()){ %>
         
            <div id="content_recipe_wrapper">
             <div class="content_recipe" id="recipe1">
               		
               		
               			<%for(OurRecipe o:list) {%>
	                        <div class="recipe_content">
	                        	<a href="/recipe/recipeSelectContent.do?boardNo=<%=o.getBoardNo()%>&currentPage=<%=currentPage %>&userId=<%=m.getUserId() %>" style="text-decoration: none; color: inherit;">
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
                      <%}else{ %>
                      
								<H3 style="text-align:center">등록된 레시피가 없습니다.</H3>	

                	<%} %>
            </div>

        </div>
        </div>
        
        <div id="content_fotter">
            <div id="page_navi">
            	<%=pageNavi %>
            </div>
        </div>  
            
              
            <%--컨텐츠 </div>--%>     
        </div>
        

	<%@include file="/views/common/footer.jsp"%>



</body>
</html>