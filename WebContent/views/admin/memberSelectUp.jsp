<%@page import="kr.co.rfy.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 상세 정보</title>
</head>
<body>
<style>
* {
	font-family: 'Noto Sans KR', sans-serif;
}
	#post{
		width: 500px;
		heigh: 400px;
		margin: 0 auto;
	}
	table {
	
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
.td{
text-align:right;
}

.button {

    width:100px;

    background-color: white;

    border: none;

    color:black;

    padding: 15px 0;

    text-align: right;

    text-decoration: none;

    display: inline-block;

    font-size: 15px;

    margin: 4px;

    cursor: pointer;


}
.back{  
      text-decoration:none;
      border: 1px solid #7FB292;
      background-color:#7FB292;

      color:white;
      padding:10px 20px 10px 20px;
      margin:20px;
      display:inline-block;
      border-radius: 10px;



    }





</style>
<%
	Member m = (Member)request.getAttribute("m");
	
	int currentPage = (int)request.getAttribute("currentPage");

%>




<%if(m!=null){ %>


<br><br><br>
<div id="post">
<br>
				<table>
				<thead>회원상세정보</thead>
				<tbody>
				<tr>
				<td>회원 번호</td>
				<td class="td"><%=m.getUser_No() %></td>
				</tr>
				
				<tr>
				<td>아이디</td>
				<td class="td"><%=m.getUser_Id() %></td>
				</tr>
				
				<tr>
				<td>이름</td>
				<td class="td"><%=m.getUser_Name() %></td>
				</tr>
				
				<tr>
				<td>휴대전화</td>
				<td class="td"><%=m.getUser_Phone() %></td>
				</tr>
				
				<tr>
				<td>이메일</td>
				<td class="td"><%=m.getUser_Email() %></td>
				</tr>
				
				<tr>
				<td>회원상태</td>
				<td class="td">

				<a href="/admin/memberEndYNChangeUp.do?userNo=<%=m.getUser_No()%>&blackYN=<%=m.getBlack_YN()%>">
				<button style="width:100%" class="button"><%if(m.getBlack_YN()=='N'){ %>
				정상
				<%}else{ %>
				블랙<%} %></button></a>
				
				
				
				</td>
				</tr>
			</tr>
			</tbody>
			</table>
			<br>
				<a href="/admin/memberAllListUp.do?currentPage=<%=currentPage%>" class="back">취소</a><br>
</div>
<script>
$('.button').click(function(){
	var data = $(this).html();
	 
	 if(data='정상')
		 {
		 return window.confirm("정상->블랙으로 변경하시겠습니까?");
		 }else
			 {
			return window.confirm("블랙->정상으로 변경하시겠습니까?");
			 }
});


</script>




<%}else{ %>
탈퇴한 회원입니다



<%} %>
</body>
</html>