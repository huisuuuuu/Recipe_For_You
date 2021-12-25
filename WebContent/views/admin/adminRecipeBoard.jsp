<%@page import="kr.co.rfy.adminRecipeBoard.model.vo.AdminRecipeBoard"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
	integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
	crossorigin="anonymous">

<link rel="stylesheet" type="text/css" href="/assets/css/adminRecipeBoard.css">

<title>냉장고를 부탁해</title>

</head>
<body>
	<%
		//페이징 처리되어 넘어온 데이터를 가져와야 함
		HashMap<String,Object> pageDataMap = (HashMap<String,Object>)request.getAttribute("pageDataMap");
		
		ArrayList<AdminRecipeBoard> list = (ArrayList<AdminRecipeBoard>)pageDataMap.get("list");
		String pageNavi = (String)pageDataMap.get("pageNavi");
	%>
	
	<div id="wrapper">
		<div id="header"></div>
		<div id="navigation">
			<%@include file="/views/commons/adminNavigation.jsp" %>
		</div>
		<div id="content">
			<div class="page_section">
				<div class="head_article">
					<h2 class="tit">레시피 조회 및 관리</h2>
				</div>
				<form action="/recipeBoard/recipeBoardPostSearch.do" method="get">
					<div class="search-type">
						<select name="type" id="searchTypeSelect">
							<option value="writer">작성자</option>
							<option value="subject">제목</option>
						</select>
					</div>
					<div class="search-bar">
						<input type="text" size="30" name="keyword" class="search-input"/>
						<label for="searchSubmit" style="cursor: pointer;"><i class="fas fa-search search-icon">
						</i></label>
						<input type="submit" id="searchSubmit" style="display:none;"/>
					</div>
					<div id="abminBtn">
						<button id="blackBtn" type="button" onclick="btn(black)">블랙리스트 등록</button>
						&nbsp;
						<button id="delBtn" type="button" onclick="btn(post)">삭제</button>
						<%-- <button id="delBtn" type="button" onclick="$('#boardForm').submit();">삭제</button> --%>
					</div>
				</form>
				
				<script>
					function btn(str){
						
						if(str.equals("black"))
						{
							boardForm.action = "/adRecipeBoard/memberBlack.do";
						}else
						{
							boardForm.action = "/adRecipeBoard/deletAdminBoardList.do";
						}
						
					}
					
				</script>
				<form action="/adRecipeBoard/deletAdminBoardList.do" method="post" name="boardForm">
					<table>
						<thead>
							<tr>
								<th><input type="checkbox" id="allCheckBox"></th>
								<th>번호</th>
								<th><select id="recipeCode" class="filter">
										<option value>카테고리</option>
										<option value="Recipe_01">한식</option>
										<option value="Recipe_02">양식</option>
										<option value="Recipe_03">일식</option>
										<option value="Recipe_04">중식</option>
										<option value="Recipe_05">분식</option>
										<option value="Recipe_06">채식</option>
										<option value="Recipe_07">다이어트</option>
										<option value="Recipe_08">밑반찬</option>
										<option value="Recipe_09">안주</option>
								</select></th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
							</tr>
						</thead>
						<tbody>
							<%
								for(AdminRecipeBoard recipeBoard : list){
							%>
							<tr>
								<td><input type="checkbox" name="postNo"
									value="<%=recipeBoard.getBoardNo() %>" /></td>
								<td><%=recipeBoard.getBoardNo() %></td>
								<td><%=recipeBoard.getRecipeName() %></td>
								<td><a id="recipeName" href="/recipeBoard/recipeBoardSelectContent.do?boardNo=<%=recipeBoard.getBoardNo()%>"><%=recipeBoard.getTitle() %></a></td>
								<td><%=recipeBoard.getUserName() %></td>
								<td><%=recipeBoard.getRegDate() %></td>
							</tr>
							<%} %>
						</tbody>
					</table>
					<div class="layout-pagination">
						<div class="page-navi">
							<nav aria-label="Page navigation example">
							<ul class="pagination justify-content-center mt-5">
								<%=pageNavi %>
							</ul>
							</nav>
						</div>
					</div>
				</form>
			</div>
		</div>

		<script>
			$('#allCheckBox').click(function() {
				
				$('input[name=postNo]').trigger('click');
				
				if ($(this).prop('checked')) {
					$('input[name=postNo]').prop('checked', true);
					$('input[name=postNo]').parents('tr').css('background-color','#E3EEE6');
				} else {
					$('input[name=postNo]').prop('checked', false);
					$('input[name=postNo]').parents('tr').css('background-color','#white');
				}
			});
		</script>
		
		<script>
			$('input[name=postNo]').click(function(){
				
				if($(this).prop('checked'))
				{
					$(this).parents('tr').css('background-color','#E3EEE6');
				}else
				{
					$(this).parents('tr').css('background-color','white');
				}
			});
		</script>


		<div id="footer"></div>
	</div>
</body>
</html>