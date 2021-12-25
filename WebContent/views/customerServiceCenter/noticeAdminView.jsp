<%@page import="kr.co.rfy.member.vo.Member"%>
<%@page import="kr.co.rfy.notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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

#contentForm{
	height: 400px;
	width: 1000px;
	margin: 0 auto;

}

#titleForm{
	overflow : hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	height: 50px;
	width: 1000px;
	margin: 0 auto;

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
</style>

<title>냉장고를 부탁해 공지사항 게시판</title>
</head>
<body>
<%
Notice n = (Notice)request.getAttribute("n");
int currentPage = (int)request.getAttribute("currentPage");
Member m = (Member)session.getAttribute("member");
//  /customerServiceCenter/noticeUpdate.do
%>

<div id="wrap">
		<div id="header">
		
		</div>
		
		<form action="/views/customerServiceCenter/noticeUpdate.jsp" method="post">
		<div id="center">
		
			<!-- 소개 하는 공간 -->
			<div id="notice" align="center"">
				<h1>  |  NOTICE  |  </h1>
				<span>새로운 소식들과 유용한 정보들을 한곳에서 확인하세요.</span>
			</div><hr>
				<input type="hidden" name="currentPage" value="<%=currentPage%>"/>
				<input type="hidden" name="board_no" value="<%=n.getBoard_no()%>"/>
				<input type="hidden" name="notice_title" value="<%=n.getTitle()%>"/>
				<input type="hidden" name="notice_content" value="<%=n.getContent()%>"/>
				
				<div id="titleForm" align="center">
					<h2><%=n.getTitle() %></h2>
				</div><hr>
				
				<div id="regDage" align="left">
					작성일 : [ <%=n.getRegDate() %> ]  /  
					
					<%if(n.getBoard_code().substring(6,7).equals("4")){ %>
					코드 : [ 공지사항 ]  /  
					<%} %>
					
					글 번호 : [ <%=n.getBoard_no() %> ]  /  
					조회수 : <%=n.getView_count() %>   
				</div><hr>
				
				<div id="contentForm" style="overflow: auto;">
					<%=n.getContent() %>
				</div><hr>
				
				<div id="menu" align="right">
					<input class="btn btn-success btn-sm" type="button" value="메인 메뉴" onclick="location='/customerServiceCenter/noticeAdmin.do?currentPage=<%=currentPage%>'">
					<%if(m != null & m.getUserId().equals(n.getUser_id())){ %>
					<input class="btn btn-success btn-sm" type="submit" value="글 수정"/>
					<input class="btn btn-success btn-sm" type="button" value="글 삭제" id="deleteBtn"/>
					<% }%>
				</div>
			</form>	
			
			
			
			<!-- 글 삭제 -->
			<script type="text/javascript">
				$('#deleteBtn').click(function(){
					if(window.confirm('삭제할건가요?')){
						var formTag = document.createElement("form");
						formTag.setAttribute("action",'/customerServiceCenter/noticeDelete.do');
						formTag.setAttribute("method",'post');
						
						var inputTag = document.createElement("input");
						inputTag.setAttribute("type","hidden");
						inputTag.setAttribute("name","board_no");
						inputTag.setAttribute("value","<%=n.getBoard_no()%>");
						
						formTag.appendChild(inputTag);//폼 테그안에 인풋 태그 넣고
						document.body.appendChild(formTag);//폼 테그를 연결시켜준다
						formTag.submit();
					}else{
						alert('삭제하지 않습니다');
					}
				});
			</script>
			<!-- 글 삭제 -->
		</div>
		
		<div id="footer">
		</div>
	</div>

</body>
</html>