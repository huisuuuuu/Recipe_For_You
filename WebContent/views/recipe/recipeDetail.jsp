
<%@page import="kr.co.rfy.recipeBoard.model.vo.MyboxIngredient"%>
<%@page import="kr.co.rfy.member.model.vo.Member"%>
<%@page import="kr.co.rfy.recipeBoard.model.vo.Ingredient"%>
<%@page import="kr.co.rfy.recipeBoard.model.vo.File"%>
<%@page import="kr.co.rfy.recipeBoard.model.vo.Content"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.rfy.recipeBoard.model.vo.RecipeDetail"%>
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
   
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- font -->
   <style>
   @import url('https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@200&display=swap');
   </style>

   
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&family=Roboto:ital,wght@0,100;1,100&display=swap" rel="stylesheet">
    <title>header</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/recipeDetailMain.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/recipeDetailContent.css">
    


</head>
<body>

	<%
	int currentPage	=(int)request.getAttribute("currentPage");//현재페이지 목록을 불러오기 위한 값
	HashMap<String,Object> recipeDetailInfo = (HashMap<String,Object>)request.getAttribute("recipeDetailInfo");
	
	// recipeDetailInfo에 들어있는 5가지 값 꺼내기
	
	RecipeDetail recipeInfo =(RecipeDetail)recipeDetailInfo.get("recipeInfo");
	ArrayList<Content> contentList = (ArrayList<Content>)recipeDetailInfo.get("contentList");
	ArrayList<File> fileList =(ArrayList<File>) recipeDetailInfo.get("fileList");	
	
	%>

		 <%@include file="/views/common/header3.jsp"%>
		 <%@include file="/views/common/quickbar.jsp"%>
		 
	<%
	
	ArrayList<Ingredient> ingredientList = (ArrayList<Ingredient>)recipeDetailInfo.get("ingredientList");
	
	ArrayList<MyboxIngredient> myBoxList = null;
	if(m!=null)
	{
		myBoxList = (ArrayList<MyboxIngredient>)recipeDetailInfo.get("myBoxList");	
		System.out.print(myBoxList);
	}
	
	
	%>

	<!-- jQuery Cookie --> 
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.js" integrity="sha512-aUhL2xOCrpLEuGD5f6tgHbLYEXRpYZ8G5yD+WlFrXrPy2IrWBlu6bih5C9H6qGsgqnU6mgx6KtU8TreHpASprw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<script>
        $(function(){
            // 세션에서 필요한 정보 가져오기
            var recipeBoardNo = "<%=recipeInfo.getBoardNo()%>";
            var recipeFilaPath = "<%=fileList.get(0).getFilePath()%>";
            var currentPage = "<%=currentPage%>";

            // 기존 쿠키값 여부 확인
            if($.cookie("boardNo")===undefined){ // 기존 쿠기값이 없다면
                var boardNo = [recipeBoardNo];
                var filePath = [recipeFilaPath];
                var page = [currentPage];

            }else{ // 기존 쿠기값이 있다면
                var boardNo = [$.cookie("boardNo")];
                var filePath = [$.cookie("imgAddr")];
                var page = [$.cookie("page")];
                // 기존 쿠기값에 레시피가 있는지 확인

                var check = $.cookie("boardNo").split(",").indexOf(recipeBoardNo); // 확인

                if(check<0){ // 없다면
                    boardNo.unshift(recipeBoardNo);
                    filePath.unshift(recipeFilaPath);
                    page.unshift(currentPage);

                }else{ // 있다면

                    // 값을 찾아가 삭제
                    boardNo = $.cookie("boardNo").split(",");
                    filePath = $.cookie("imgAddr").split(",");
                    page = $.cookie("page").split(",");

                    boardNo.splice(check,1);
                    filePath.splice(check,1);
                    page.splice(check,1);

                    // 앞에 추가
                    boardNo.unshift(recipeBoardNo);
                    filePath.unshift(recipeFilaPath);
                    page.unshift(currentPage);
                }
            }
            $.cookie("boardNo",boardNo,{path:"/"})
            $.cookie("imgAddr",filePath,{path:"/"})
            $.cookie("page",page,{path:"/"})
        })
    </script>
	
 
	
  
        <div class="contents">
                <div id="cWrapper">
       <div class="side_empty"></div>
       <div id="content_wrapper">
           <div id="content_empty"></div>
           
           <div id="content1">
               
               <div id="food_img">
                <img src="<%=fileList.get(0).getFilePath()%>"  width="100%" height="648px">
               </div>
               <div id="subtitle"><%=recipeInfo.getSubTitle() %></div>
               <div id="title"><%=recipeInfo.getTitle() %><hr>
               </div>
               <div id="introduction">
                   <%=contentList.get(0).getContent() %>
                   
               </div>
           </div>
           <div id="content2_ingredient">
              <div style=font-weight:bold;>재료 <hr class="line"></div>
              <div id="ingredient_warpper">
                <table width="90%">
             
             
		
          <%if(m!=null){ %> 
            
            
            	<%if(!myBoxList.isEmpty()){ %>
              		 <%for(int i=0;i<ingredientList.size();i++) {%>
                   		<%if(i%2==0){ %>
                   		 <tr>
                 			<%for(int k=0; k<myBoxList.size();k++ ){ %>

		                 			<%if(ingredientList.get(i).getIngredientName().equals(myBoxList.get(k).getIngredientName())){ %> 
			                       		<td><%=ingredientList.get(i).getIngredientName() %> <spn ></spn></td>
			                      		<td><%=ingredientList.get(i).getIngredientNum() %></td>
			                     
			                     	<%break;}else{ %>
			                     	<%if(k==(myBoxList.size()-1)){ %>
			                     		 <td><%=ingredientList.get(i).getIngredientName() %> <spn style="color: red">필요해요!</spn></td>
			                       		 <td><%=ingredientList.get(i).getIngredientNum() %></td>
			                       	<%} %>
			                       		
			                     <%} %>
	                    	 <%} %>
	                   <%}else{ %>
		                    	 
		                    		<%for(int j=0; j<myBoxList.size();j++ ){ %>
				                    	 
					                    	<%if(ingredientList.get(i).getIngredientName().equals(myBoxList.get(j).getIngredientName())){ %> 
					                       		<td><%=ingredientList.get(i).getIngredientName() %> <spn ></spn></td>
					                      		<td><%=ingredientList.get(i).getIngredientNum() %></td>
				                     
					                     	<%break;}else{ %>
					                    	 <%if(j==myBoxList.size()-1){ %>
						                       	<td><%=ingredientList.get(i).getIngredientName() %> <spn style="color: red">필요해요!</spn></td>
						                       	<td><%=ingredientList.get(i).getIngredientNum() %></td>
						                       	<%} %>
				                       	 	<%} %>
				                   <%} %>
				                     </tr>
		                  <%} %>
                   <%} %>
                   
              <%}else {%>
                   		<%for(int i=0;i<ingredientList.size();i++) {%>
                   <tr>
                       <td><%=ingredientList.get(i).getIngredientName() %> <spn style="color: red">필요해요!</spn></td>
                       <td><%=ingredientList.get(i).getIngredientNum() %></td>

                     <%if(i!=ingredientList.size()-1){ %>
                       <td><%=ingredientList.get(i+1).getIngredientName() %> <spn style="color: red">필요해요!</spn></td>
                       <td><%=ingredientList.get(i+1).getIngredientNum() %></td>
                       <%} %>
                   </tr>
                   <% i++;} %>
                   
                   <%} %>

               
               
               
               
               
          <%--비로그인일 경우 필요해요 기능 미실행--%>        
          <%}else{ %>   
                   
		                   <%for(int i=0;i<ingredientList.size();i++) {%>
		                   <tr>
		                       <td><%=ingredientList.get(i).getIngredientName() %> </td>
		                       <td><%=ingredientList.get(i).getIngredientNum() %></td>
		
		                     <%if(i!=ingredientList.size()-1){ %>
		                       <td><%=ingredientList.get(i+1).getIngredientName() %> </td>
		                       <td><%=ingredientList.get(i+1).getIngredientNum() %></td>
		                       <%} %>
		                   </tr>
		                   <% i++;} %>
		                   
                 <%} %>  
                   
                   
               </table>
            </div>
               
           </div>
           <div id="content3_cook">
               <div style=font-weight:bold;>조리법 <hr class="line"></div>
               
              
         <%for(int i=1;i<fileList.size();i++) {%>
               <div class="step_wrapper">
                   <div class="step_empty">step<%=i %></div>
                   <div class="step_content">
                      <hr>
                       <div class="step_img" style=" transform: translateY(3px);">
                           <img src="<%=fileList.get(i).getFilePath() %>" width="302px" height="308px">
                       </div>
                       <div class="step_text" style=" transform: translateY(7px);">
                          <%=contentList.get(i).getContent() %>
                       </div>
                   </div>
               </div>
              <%} %>
               
  
               
               
               

            <!--content3_cook div -->   
           </div>
           <div id="content_fotter">
             <div id="fotter_empty1"><hr id="fotter_line"></div>
              <!--작성자라면 수정,취소/미작성자는 추천-->
               <div id="fotter_button">
               
              
               <%if(m!=null && m.getUserId().equals(recipeInfo.getUserId())){ %>  
                  이 레시피를 &nbsp&nbsp
                   <button type="button" class="btn btn-success" id="updateBtn" >
                   <a href="/recipe/recipeUserPostUpdate.do?boardNo=<%=recipeInfo.getBoardNo()%>" style="color: white;"> 수정</a> </button> &nbsp 할래요! 
                   <button type="button" class="btn btn-success" id="deleteBtn" style="transform: translate(280px,-10px)">삭제</button>
                    <a href="/recipe/recipeBoard/selectAll.do?currentPage=<%=currentPage%>"><button type="button" class="btn btn-success" id="listBtn" style="transform: translate(300px,-10px)">목록</button></a>
     			
        <%}else if(m!=null && !(m.getUserId().equals(recipeInfo.getUserId()))){ %>   
          	  이 레시피를 &nbsp&nbsp
                   <button type="button" class="btn btn-success" id="likeBtn"> 추천</button> 
                    <button type="button" class="btn btn-success" style="width: 15%; display:none" id="likeCancelBtn">추천취소</button> &nbsp 할래요!
                     <a href="/recipe/recipeBoard/selectAll.do?currentPage=<%=currentPage%>"><button type="button" class="btn btn-success" id="listBtn" style="transform: translate(300px,-13px)">목록</button></a> 
               		
             <%}else{ %> 
             
             
               <a href="/recipe/recipeBoard/selectAll.do?currentPage=<%=currentPage%>"><button type="button" class="btn btn-success" id="listBtn" style="transform: translateX(300px)">목록</button></a> 
             <%} %>
               </div>
               <div id="fotter_empty2"></div>
           </div>
           
    <!--content_wrapper div끝-->    
       </div>

       <div class="side_empty"></div>
    </div>
            
            
   <%--추천 버튼 기능 --%>         
   <script>
	$('#likeBtn').click(function(){
		
		var text = $(this).text();
		
		$('#likeCancelBtn').css("display","inline");
		$('#likeBtn').css("display","none");
		
		//최신 likeNum 값 가져오기
		var likeNum= 0;
		$.ajax({
			url:"/recipe/recipeGetPostLike.do",
			type:"post",
			async:false,
			dataType:"json",
			data:{"boardNo":<%=recipeInfo.getBoardNo()%>},
			success:function(result){
				
				likeNum=result;
				
			},
			error:function(){
				consol.log("ajax2.do 서버 호출 실패");
			}
		});
		
		
		
		likeNum++;
	
		$.ajax({
			url:"/recipe/recipeOnePostLike.do",
			type:"post",
			data:{"boardNo":<%=recipeInfo.getBoardNo()%>,"likeNum":likeNum},
			success:function(result){
				
				alert('<%=recipeInfo.getTitle()%>'+' 레시피를 추천하셨습니다.');
				
			},
			error:function(){
				consol.log("ajax2.do 서버 호출 실패");
			}
		});
		
		

		
	});   
   
	$('#likeCancelBtn').click(function(){
		
		
		var text = $(this).text();
		
		$('#likeBtn').css("display","inline");
		$('#likeCancelBtn').css("display","none");
		
		
		//최신 likeNum 값 가져오기
		var likeNum= 0;
		$.ajax({
			url:"/recipe/recipeGetPostLike.do",
			type:"post",
			async:false,
			dataType:"json",
			data:{"boardNo":<%=recipeInfo.getBoardNo()%>},
			success:function(result){
				
				likeNum=result;
				
			},
			error:function(){
				consol.log("ajax2.do 서버 호출 실패");
			}
		});
		
		
		likeNum=(likeNum-1);
	
		console.log(likeNum);
		
		$.ajax({
			url:"/recipe/recipeOnePostLikeCancel.do",
			type:"post",
			data:{"boardNo":<%=recipeInfo.getBoardNo()%>,"likeNum":likeNum},
			success:function(result){
				
				alert('<%=recipeInfo.getTitle()%>'+' 레시피 추천을 취소하셨습니다.');
				
			},
			error:function(){
				consol.log("ajax2.do 서버 호출 실패");
			}
		
		});
		
	});   

   
   </script>         
    
    
    <%--삭제 버튼 기능 --%>
    
    <script>
    	$('#deleteBtn').click(function(){
    		
    		if(window.confirm('<%=recipeInfo.getTitle()%>'+'레시피를 삭제하시겠습니까?'))
    		{
    			
    			$.ajax({
    				url:"/recipe/recipePostDelete.do",
    				type:"post",
    				data:{"boardNo":<%=recipeInfo.getBoardNo()%>},
    				success:function(){
    					
    					alert('<%=recipeInfo.getTitle()%>'+'레시피를 삭제하였습니다.');
    					location.replace('/recipe/recipeBoard/selectAll.do?currentPage=<%=currentPage%>');
    					
    				},
    				error:function(){
    					consol.log("ajax2.do 서버 호출 실패");
    				}
    			
    			});
    			
    			
    		}else
    		{
    			alert('레시피 삭제를 취소하셨습니다.');
    			return;
    		}
    		
    		
    	});
    
    </script>
    
   
	
	
  
    
    
    
    
            
            
            
<!-- conntent 끝-->  
        </div>
        
	<%@include file="/views/common/footer.jsp"%>


</body>
</html>