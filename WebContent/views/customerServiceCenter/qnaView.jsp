<%@page import="kr.co.rfy.member.model.vo.Member"%>
<%@page import="kr.co.rfy.qna.model.vo.Qna"%>
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

#content{
	height: 400px;
	width: 1000px;
	margin: 0 auto;
	border: 1px solid black;
}

#title{
	overflow : hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	height: 50px;
	width: 1000px;
	margin: 0 auto;
	border: 1px solid black;
}

#regDage{
	height: 30px;
	width: 1000px;
	margin: 0 auto;

}

#menu{
	height: 30px;
	width: 1000px;
	margin: 0 auto;

}

#qna{
	height: 100px;
	width : 500px;
	margin: 0 auto;
	
}
</style>

</head>
<body>
<%
Qna qna = (Qna)request.getAttribute("qna");
int currentPage = (int)request.getAttribute("currentPage");
Member m = (Member)session.getAttribute("member");
%>
<div id="wrap">
		<div id="header">
		
		</div>
		
		<div id="center">
	
			<!-- 소개 하는 공간 -->
		<div id="qna" align="center"">
				<h1>  |  QnA  |  </h1>
				<span>새로운 소식들과 유용한 정보들을 한곳에서 확인하세요.</span>
		</div><hr>
			
			<div id="title" align="center">
				<h2>[ <%=qna.getTitle() %> ]</h2>
			</div><hr>
			
			<div id="regDage" align="left">
				작성일 : [ <%=qna.getRegDate() %> ]  /  
					<%if(qna.getQ_code().substring(2,3).equals("1")){ %>
						<td>분류 : [ 회원 ]</td>
					<%}else if(qna.getQ_code().substring(2,3).equals("2")){ %>
						<td>분류 : [ 서비스 이용 ]</td>
					<%}else if(qna.getQ_code().substring(2,3).equals("3")){  %>
						<td>분류 : [ 주문/결제 ]</td>
					<%}else if(qna.getQ_code().substring(2,3).equals("4")){  %>
						<td>분류 : [ 배송/상품 ]</td>
					<%}else if(qna.getQ_code().substring(2,3).equals("5")){  %>
						<td>분류 : [ 취소/교환/환불 ]</td>
					<%} %>
				  /  글 번호 : [ <%=qna.getBoard_no()%> ]   
			</div><hr>
			
			<div id="content" style="overflow: auto;">
				<%=qna.getContent() %>
			</div><hr>
			
			<div id="menu" align="center">
				<input type="button" class="btn btn-success btn-sm" value="메인 메뉴" onclick="location='/customerServiceCenter/qna.do?currentPage=<%=currentPage%>'">
				
			</div>
			
			
		</div>
		
		
		<div id="footer">
			<jsp:include page="/views/common/footer.jsp"></jsp:include>
		</div>
	</div>

</body>
</html>