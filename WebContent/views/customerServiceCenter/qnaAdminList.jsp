<%@page import="kr.co.rfy.qna.model.vo.Qna"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>냉장고를 부탁해 QnA 게시판</title>

<!-- boostrap5 라이브러리-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<!-- jQuery 라이브러리 -->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<link rel="stylesheet" type="text/css"
	href="/assets/css/adminRecipeBoard.css">


<style type="text/css">

#notice{
	height: 100px;
	width : 500px;
	margin: 0 auto;
	
}

#arrayList{
	height: 500px;
	width : 1000px;
	margin: 0 auto;

}

#pageList{
	height: 40px;
	width: 500px;
	margin: 0 auto;
}

#serachList{
	height: 30px;
	width: 1000px;
	margin: 0 auto;
	
}

#qna{
	height: 100px;
	width : 500px;
	margin: 0 auto;
	
}

.form-control me-2{
	size: 10px;
}

#submit_form{
 	width: 275px;
 	height: 50px;
 	float: right;
 	border: 1px solid white;
 }
 
#keyword_form{
 	width: 300px;
 	height: 50px;
 	float: right;
 	border: 1px solid white;
 }
 
#option_form{
 	width: 100px;
 	height: 50px;
 	float: right;
 	border: 1px solid white;
}
</style>


</head>
<body>

<%
HashMap<String,Object> pageDataMap = (HashMap<String,Object>)request.getAttribute("pageDataMap");

ArrayList<Qna> list= (ArrayList<Qna>)pageDataMap.get("list");

String pageNavi = (String)pageDataMap.get("pageNavi");
int currentPage = (int)request.getAttribute("currentPage");

%>

<div id="wrapper">
		<div id="header"></div>
		<div id="navigation">
			<%@include file="/views/common/adminNavigation.jsp"%>
		</div>
		</div>
	<div id="content">
		<!-- 소개 하는 공간 -->
		<div id="qna" align="center"">
				<h1 style="font-family: 'Noto Sans KR', sans-serif;">  |  QnA  |  </h1>
				<span style="font-family: 'Noto Sans KR', sans-serif;">새로운 소식들과 유용한 정보들을 한곳에서 확인하세요.</span>
		</div><br>
		
		<!-- 리스트 뿌려주는 공간 -->
		<div id="arrayList">
			<table class="table">
				<thead>
					<tr>
				      <th scope="col">번호</th>
				      <th scope="col">카테고리</th>
				      <th scope="col">제목</th>
				    </tr>
				</thead>
				
				<%for(Qna qna : list){ %>
				<tr>
					<th scope="row"><%=qna.getBoard_no() %></th>
					
					<!-- 구분 코드 -->					
						
						<%if(qna.getQ_code().substring(2,3).equals("1")){ %>
							<td>[ 회원 ]</td>
						<%}else if(qna.getQ_code().substring(2,3).equals("2")){ %>
							<td>[ 서비스 이용 ]</td>
						<%}else if(qna.getQ_code().substring(2,3).equals("3")){  %>
							<td>[ 주문/결제 ]</td>
						<%}else if(qna.getQ_code().substring(2,3).equals("4")){  %>
							<td>[ 배송/상품 ]</td>
						<%}else if(qna.getQ_code().substring(2,3).equals("5")){  %>
							<td>[ 취소/교환/환불 ]</td>
						<%} %>
					
					<!-- 구분 코드 -->					
					
					<td><a href="/qna/qnaAmdinView.do?board_no=<%=qna.getBoard_no() %>&currentPage=<%=currentPage%>"><%=qna.getTitle() %></a></td>
				</tr>
				<%} %>
				</table>
		</div>

						<!-- 페이징 처리 -->
						<div id="pageList" align="center">
						<nav aria-label="Page navigation example" style="width : 500px; margin: 0 auto;" >
					  		<ul class="navi" style="margin: 0 auto;">
					  			<%=pageNavi %>
							</ul>
						</nav>
									
						<style>
							.navi>a{
							width : 8%;
							float : left;
							}
						</style>
								
						<script>
						$(function(){
							$('.prev').addClass('page-link');
							$('.prev').css('width','35%');
							$('.naviNum').addClass('page-link');
							$('.next').addClass('page-link');
							$('.next').css('width','35%');
						});
						</script>
						</div>
						<!-- 페이징 처리 -->
						<br>

				
				<!-- 검색 기능 -->
				<div id="serachList" align="right">
					<form action="/qna/qnaAdminSearch.do" method="get">
						<div  id="submit_form" align="left">
						<input class="btn btn-success" type="submit"  value="검색하기"/>
						<input class="btn btn-success" type="button" value="메인센터" onclick="location='/'"/>
						<input class="btn btn-success" type="button" value="글 쓰기" onclick="location='/views/customerServiceCenter/qnaWrite.jsp'"/>
					</div>
					
					<div id="keyword_form" align="left">
						<input class="form-control me-2" type="text" name="keyword" size="30"/>
					</div>
					
					<div id="option_form">
						<select class="form-select" name="type">
							<option value="title">제목</option>
							<option value="content">내용</option>
							<option value="all">제목+내용</option>
						</select>
					</div>
					</form>
				</div>
				<!-- 검색 기능 -->
		<div id="footer"></div>
	</div>
</div>


</body>
</html>