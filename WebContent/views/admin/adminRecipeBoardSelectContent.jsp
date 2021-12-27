<%@page import="kr.co.rfy.member.model.vo.Member"%>
<%@page import="kr.co.rfy.adminRecipeBoard.model.vo.RecipeImage"%>
<%@page import="kr.co.rfy.adminRecipeBoard.model.vo.RecipeIngredient"%>
<%@page import="kr.co.rfy.adminRecipeBoard.model.vo.RecipeContent"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="kr.co.rfy.adminRecipeBoard.model.vo.AdminRecipeBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- font -->
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@200&display=swap')
	;
</style>

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&family=Roboto:ital,wght@0,100;1,100&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="/assets/css/adminRecipeDetailContent.css">
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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
	integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css"
	href="/assets/css/adminRecipeUpload.css">

<title>Insert title here</title>
</head>
<body>

	<%
		//Servlet에서 보내온 데이터 가져오기
		HashMap<String, Object> pageDataMap = (HashMap<String, Object>) request.getAttribute("pageDataMap");

		AdminRecipeBoard recipeBoard = (AdminRecipeBoard) pageDataMap.get("recipeBoard");
		ArrayList<RecipeContent> contentList = (ArrayList<RecipeContent>) pageDataMap.get("contentList");
		ArrayList<RecipeIngredient> ingredientList = (ArrayList<RecipeIngredient>) pageDataMap
				.get("ingredientList");
		ArrayList<RecipeImage> imageList = (ArrayList<RecipeImage>) pageDataMap.get("imageList");

		//recipeBoard 안에는 2가지 상황이 있음
		//1. recipeBoard 객체가 담겨있는 상황(정상적으로 해당 글의 정보를 가져왔을 때)
		//2. null이 담겨있는 상황(정상적으로 해당 글의 정보를 가져오지 못했을 때)
	%>

	<%
		if (recipeBoard != null) {
	%>

	<div id="wrapper">
		<div id="header"></div>
		<div id="navigation">
			<%@include file="/views/common/adminNavigation.jsp"%>
		</div>
		<div class="contents">
			<div id="cWrapper">
				<div class="side_empty"></div>
				<div id="content_wrapper">
					<div id="content1">
						<div id="food_img">
							<img src="<%=imageList.get(0).getFilePath()%>" alt=""
								width="100%" height="648px">
						</div>
						<div id="subtitle"><%=recipeBoard.getSubTitle()%></div>
						<div id="title"><%=recipeBoard.getTitle()%><hr>
						</div>
						<div id="introduction">
							<%=contentList.get(0).getContent()%>
						</div>
					</div>
					<div id="content2_ingredient">
						<div style="font-weight: bold;">
							재료
							<hr class="line">
						</div>
						<div id="ingredient_warpper">
							<table width="90%">
								<%
									for (int i = 0; i < ingredientList.size(); i++) {
								%>
								<tr>
									<td><%=ingredientList.get(i).getIngredientName()%> <spn
											style="color: red">필요해요!</spn></td>
									<td><%=ingredientList.get(i).getIngredientNum()%></td>

									<%
										if (i != ingredientList.size() - 1) {
									%>
									<td><%=ingredientList.get(i + 1).getIngredientName()%> <spn
											style="color: red">필요해요!</spn></td>
									<td><%=ingredientList.get(i + 1).getIngredientNum()%></td>
									<%
										}
									%>
								</tr>
								<%
									i++;
										}
								%>
							</table>
						</div>

					</div>
					<div id="content3_cook">
						<div style="font-weight: bold;">
							조리법
							<hr class="line">
						</div>

						<%
							for (int i = 1; i < contentList.size(); i++) {
						%>
						<div class="step_wrapper">
							<div class="step_empty">
								step<%=i%></div>
							<div class="step_content">
								<hr>
								<div class="step_img" id="img1">
									<img src="<%=imageList.get(i).getFilePath()%>" width="302px"
										height="308px">
								</div>
								<div class="step_text" id="text1">
									<%=i%>.
									<%=contentList.get(i).getContent()%>
								</div>
							</div>
						</div>
						<%} %>

						<!--content3_cook div -->
					</div>
					<div id="content_fotter">
						<div id="fotter_empty1">
							<hr id="fotter_line">
						</div>
						<!--작성자라면 수정,취소/미작성자는 추천
						 session 객체에서 회원 ID를 확인한 뒤 작성자와 일치하면 수정 버튼을 활성화-->
						<%
							Member m = (Member) session.getAttribute("member");
						%>
						<div id="fotter_button">
							<span>이 레시피를&nbsp&nbsp</span>
							<%
								if (m != null && m.getUserId().equals(recipeBoard.getUserId())) {
							%>
							<button type="button" class="btnStyle" id="updateBtn">
								<a href="/recipeBoard/recipePostUpdate.do?boardNo=<%=recipeBoard.getBoardNo()%>">수정</a></button>
							<%
								} else {
							%>
							<form action="/recipeBoard/recipeBoardPostLike.do" method="post"
								id="likeUpdateForm" style="display: inline-block;">
								<button class="btnStyle" id="likeBtn">추천</button>
								<input type="hidden" name="boardNo"
									value="<%=recipeBoard.getBoardNo()%>" /> <input type="hidden"
									name="likeNum" value="<%=recipeBoard.getLikeNum()%>" />
							</form>
							<%
								}
							%>
							<span style="margin: 3px 2px;">&nbsp할래요!</span>
							<%if(m != null && m.getUserId().equals(recipeBoard.getUserId())) {%>
							<button type="button" class="btnStyle" id="deleteBtn">
							<a href="">삭제</a></button>
							<%} %>
							<button type="button" class="btnStyle" id="boardListBtn">
								<a href="/recipeBoard/recipeBoardAllSelect.do">목록</a>
							</button>
						</div>
						<div id="fotter_empty2"></div>
					</div>
					<!--content_wrapper div끝-->
				</div>

				<div class="side_empty"></div>
			</div>
		</div>
		<!-- conntent 끝-->
		<div id="footer"></div>
	</div>

	<script>
			$('#likeBtn').click(function(){
				alert('추천이 완료되었습니다.');
			});
			
			$('#deleteBtn').click(function(){
				
				if(window.confirm('정말로 삭제하시겠습니까? 삭제된 글은 절대로 복원 불가합니다.'))
				{
					
					//하기 태그는 get 방식. 회원 삭제 url은 재사용 할 일이 없으므로 get 방식을 사용하는건 자원의 낭비이다.
					//따라서 post 방식도 사용할 수 있는 폼태그를 생성
					<%--location.replace("/recipeBoard/recipeBoardPostDelete.do?boardNo="+<%=recipeBoard.getBoardNo() %>); --%>
					
					var formTag = document.createElement("form");
					formTag.setAttribute("action","/recipeBoard/recipeBoardPostDelete.do");
					formTag.setAttribute("method","post");
					
					var inputTag = document.createElement("input");
					inputTag.setAttribute("type","hidden");
					inputTag.setAttribute("name","boardNo");
					inputTag.setAttribute("value","<%=recipeBoard.getBoardNo() %>");
					
					formTag.appendChild(inputTag);
					
					//해당 문서 안에 새롭게 만든 formTag를 추가 시켜줘야 함
					document.body.appendChild(formTag);
					
					formTag.submit();
				}else
				{
					alert('삭제를 취소하였습니다.');
				}
		});
			
		</script>

	<!-- Optional JavaScript; choose one of the two! -->

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script>
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"</script>

	<!-- Option 2: Separate Popper and Bootstrap JS -->
	<!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->
	<%
		} else {
	%>
	<script>
		alert('해당글이 존재하지 않습니다. 다시 확인해주세요.');
		location.replace('/recipeBoard/recipeBoardAllSelect.do');
	</script>
	<%
		}
	%>

</body>
</html>