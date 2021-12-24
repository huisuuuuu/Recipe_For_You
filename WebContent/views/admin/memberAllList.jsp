<%@page import="kr.co.rfy.member.model.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>일반 회원 관리</title>
</head>
<body>
<style>
#search{
	float:right;
}
table {
	text-align: center;
	line-height: 50px;
	border-top: 2px solid #5D9A71;
	border-bottom: 2px solid #5D9A71;
	border-spacing: 0px;
	font-size: 13px;
	width: 100%;
	margin-top: 5px;
}

tbody>tr>td {
	border-bottom: 1px solid #B9B9B9;
}

tbody>tr:first-child>td {
	border-top: 1px solid black;
}


.search {
	width: 100%;
	display: inline-block;
}

.search-type {
	flat: left;
	display: inline-block !important;
}

#searchTypeSelect {
	width: 80px;
	height: 30px;
	text-align: center;
	border-radius: 5px 5px;
	font-size: 13px;
}

.search-bar {
	margin: 10px 0px 10px 3px;
	width: 300px;
	height: 30px;
	border: 1px solid black;
	border-radius: 5px 5px;
	display: inline-block;

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
.link{
text-decoration:none;
color:black;
}
.UpDown{
color:black;
}

</style>


<jsp:include page="/views/commons/top.jsp"></jsp:include>
<%

HashMap<String, Object> pageDataMap = (HashMap<String, Object>)request.getAttribute("pageDataMap");

	ArrayList<Member> list = (ArrayList<Member>)pageDataMap.get("list");
	String pageNavi = (String)pageDataMap.get("pageNavi");
	int currentPage = (int)request.getAttribute("currentPage");
	
	String keyword = (String)request.getAttribute("keyword");
%>

<h2 class="tit">일반 회원 관리</h2>

<form action="/admin/MemberSearch.do" method="get" id="search">
<div class="search-type">
				<select name="type"  id="searchTypeSelect">
					<option value="userId">아이디</option>
					<option value="userName">이름</option>
					<option value="all">아이디+이름</option>
				</select>
</div>			
				
		<div class="search-bar">
<input type="text" size="30" name="keyword" class="search-input"/>
<label for="searchSubmit" style="cursor: pointer;"><i class="fas fa-search search-icon"></i></label>
<input type="submit" id="searchSubmit" style="display:none;"/>
</div>
</form>
			
					
							
							
						


<%if(!list.isEmpty()) { %>
	<table>
	<thead>
		<tr>
			<th>
			회원번호
		<a href="/admin/memberAllList.do" class="UpDown"><i class="fas fa-angle-down"></i></a>
		<a href="/admin/memberAllListUp.do" class="UpDown"><i class="fas fa-angle-up"></i></a>
		
		
				
			
			</th>
			<th>ID</th>
			<th>이름</th>
			<th>가입일</th>
			<th>이메일</th>
			<th>상태</th>
		</tr>
		</thead>
		<tbody>
		<%for(Member m: list){ %>
			<%if(m.getEnd_YN()=='Y'){ %>
		<tr style='color:grey;'>
		<%}else { %>
		
			<tr>
			<%} %>
				<td><%=m.getUser_No() %></td>
				<td><%=m.getUser_Id() %></td>
				
				<%if(m.getEnd_YN()=='N'){ %>
				<td><a class="link" href="/admin/memberSelect.do?userNo=<%=m.getUser_No() %>&currentPage=<%=currentPage%>" ><%=m.getUser_Name() %></a></td>
				<%}else{ %>
				<td><%=m.getUser_Name() %></td>
				<%} %>
				<td><%=m.getEnroll_date() %></td>
				<td><%=m.getUser_Email() %></td>

				
				<td>
				<%if(m.getEnd_YN()=='Y'){ %>
				탈퇴
				<%}else{ %>
				일반
				<%} %>

				</td>
				
				
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

<%}else{ %>
	

	<table>
	<thead>
		<tr>
			<th>
			회원번호
		<a href="/admin/memberAllList.do" class="UpDown"><i class="fas fa-angle-down"></i></a>
		<a href="/admin/memberAllListUp.do" class="UpDown"><i class="fas fa-angle-up"></i></a>

			</th>
			<th>ID</th>
			<th>이름</th>
			<th>가입일</th>
			<th>이메일</th>
			<th>상태</th>
		</tr>
		</thead>
		<tbody>
	<tr>
		<td colspan="6">검색한 회원 목록이 없습니다.</td>
	</tr>
		</tbody>
	</table>



<%} %>




<jsp:include page="/views/commons/bottom.jsp"></jsp:include>

</body>
</html>