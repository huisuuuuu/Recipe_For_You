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

<style type="text/css">
#wrap{
	height: 1480px;
	width: 1280px;
	margin: 0 auto;
	
}

#header{
	height: 200px;
	width: 1280px;
	margin: 0 auto;
}

#center{
	height: 980px;
	width: 1280px;
	margin: 0 auto;
}

#footer{
	height: 300px;
	width: 1280px;
	margin: 0 auto;
}

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
</style>


</head>
<body>

<%
HashMap<String,Object> pageDataMap = (HashMap<String,Object>)request.getAttribute("pageDataMap");

ArrayList<Qna> list= (ArrayList<Qna>)pageDataMap.get("list");

String pageNavi = (String)pageDataMap.get("pageNavi");
int currentPage = (int)request.getAttribute("currentPage");

%>

<div id="warp">
	<div id="header">
	</div>
	
	
	<div id="center">
		<!-- 소개 하는 공간 -->
		<div id="qna" align="center"">
				<h1>  |  QnA  |  </h1>
				<span>새로운 소식들과 유용한 정보들을 한곳에서 확인하세요.</span>
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
					align-content: center;
					}
				</style>
						
				<script>
				$(function(){
					$('.prev').addClass('page-link');
					$('.prev').css('width','20%');
					$('.naviNum').addClass('page-link');
					$('.next').addClass('page-link');
					$('.next').css('width','20%');
				});
				</script>
				</div>
				<!-- 페이징 처리 -->
				<br>
				
				<!-- 검색 기능 -->
				<div id="serachList" align="right">
					<form action="/qna/qnaAdminSearch.do" method="get">
						<select name="type">
							<option value="title">제목</option>
							<option value="content">내용</option>
							<option value="all">제목+내용</option>
						</select>
						<input  type="text" name="keyword" size="30"/>
						<input class="btn btn-success btn-sm" type="submit"  value="검색하기"/>
						<input class="btn btn-success btn-sm"" type="button" value="메인센터" onclick="location='/'"/>
						<input class="btn btn-success btn-sm" type="button" value="글 쓰기" onclick="location='/views/customerServiceCenter/qnaWrite.jsp'"/>
					</form>
				</div>
				<!-- 검색 기능 -->
		
		
	</div>
	
	
	<div id="footer">
	</div>
</div>


</body>
</html>