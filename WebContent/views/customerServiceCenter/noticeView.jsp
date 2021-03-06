<%@page import="kr.co.rfy.member.model.vo.Member"%>
<%@page import="kr.co.rfy.notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>냉장고를 부탁해 공지사항 게시판</title>

<!-- boostrap5 라이브러리-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<!-- jQuery 라이브러리 -->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
	integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
	crossorigin="anonymous">

<style type="text/css">
#wrap{
	width: 100%;
	margin: 0 auto;
}

#header{
	heigth:200px;
}

#center{
	height: 980px;
	width: 100%;
	max-width: 1280px;
	margin: 0 auto;
}

#notice{
	height: 200px;
	width : 500px;
	margin: 0 auto;
}

#content{
	height: 400px;
	width: 1200px;
	margin: 0 auto;
	border: 1px solid black;
}

#title{
	overflow : hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	height: 50px;
	width: 1200px;
	margin: 0 auto;
	border: 1px solid black;
}

#regDage{
	height: 30px;
	width: 1200px;
	margin: 0 auto;
	
}

#menu{
	height: 30px;
	width: 1200px;
	margin: 0 auto;

}

</style>
</head>
<body>
	<%
	Notice n = (Notice)request.getAttribute("n");
	int currentPage = (int)request.getAttribute("currentPage");
	%>
	
	
	<div id="wrap">
		<div id="header">
			<%@ include file="/views/common/header2.jsp"%>
		</div>
		<br><br><br><br>

		<div id="center">
			<!-- 소개 하는 공간 -->
			<div id="notice" align="center">
				<h1>  |  NOTICE  |  </h1>
				<span>새로운 소식들과 유용한 정보들을 한곳에서 확인하세요.</span>
			</div>
			
			<form action="/views/customerServiceCenter/noticeUpdate.jsp" method="post">
				<div id="title" align="center">
					<h2>[ <%=n.getTitle() %> ]</h2>
				</div>
				
				<div id="regDage" align="left">
					작성일 : [ <%=n.getRegDate() %> ]  /  
					<%if(n.getBoard_code().substring(6,7).equals("4")){ %>
					코드 : [ 공지사항 ]  
					<%} %>
					/  글 번호 : [ <%=n.getBoard_no() %> ]  /  조회수 : <%=n.getView_count() %>   
				</div>
				
				<div id="content" style="overflow: auto;">
					<%=n.getContent() %>
				</div><br>
				
				<div id="menu" align="right">
					<input class="btn btn-success btn-sm" type="button" value="메인 메뉴" onclick="location='/customerServiceCenter/notice.do?currentPage=<%=currentPage%>'">
				</div>
			</form>
		
		</div>
		
		<div id="footer">
			<jsp:include page="/views/common/footer.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>