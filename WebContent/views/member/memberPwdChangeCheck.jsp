<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<!-- bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<%-- Required meta tags --%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%--font--%>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&family=Noto+Serif+KR:wght@200&display=swap')
	;
</style>
<!-- header, footer css -->
<link rel="stylesheet" type="text/css"
	href="/assets/css/recipeHeaderFooter.css">
<!-- CSS -->
<link rel="stylesheet" type="text/css"
	href="/assets/css/memberPwdChangeCheck.css">
<title>Insert title here</title>

</head>
<body>
		<%@ include file="/views/common/header2.jsp" %>	
			<div id="innerContentWrapper">
				<form action="/member/memberIdPwCheck.do" method="post">
					<div>
						<H3 style=text-align:center>|  비밀번호 변경  |</H3>
					</div>
					<br>
					<div class="td" id="subTitle">비밀번호 재확인</div>
					<div class="td">
						<input type="text" class="input" name="userId"
							placeholder=" 아이디를 입력해주세요">
					</div>
					<div class="td">
						<input type="password" class="input" name="userPwd"
							placeholder=" 비밀번호를 입력해주세요"> <input type="password"
							class="input" name="type" value="change" hidden>
					</div>
					<br> <br>
					<div class="td">
						<input type="submit" value="확인" id="submit"
							class="btn greenBtn input confirmBtn">
					</div>
				</form>
			</div>
	
		<!-- 퀵바 -->
		<%@include file="/views/common/quickbar.jsp"%>
		<div class="footer">
		<!-- footer -->
		<%@include file="/views/common/footer.jsp"%>
		</div>

	<script>
	$(function(){
		$("input[name=userId]").val("${sessionScope.member.userId }");		
		
		$(function(){
			$(".confirmBtn").css("background-color","#7FB292");
			$(".confirmBtn").css("color","white");
			$(".confirmBtn").hover(function(){
				$(this).css("color","black");
			},function(){
				$(this).css("color","white");
			});
			$("#alertModal").css("top","20%");	
		})
		
	})
	</script>
</body>
</html>