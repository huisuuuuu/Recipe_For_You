<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<!-- bootstrap -->
	<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<!-- jQuery Cookie -->
<script src="https://cdn.jsdelivr.net/npm/js-cookie@3.0.1/dist/js.cookie.min.js"></script>
<!-- CSS -->
<link rel="stylesheet" type="text/css" href="/assets/css/memberLogin.css">
<!-- font -->
<style>
/* @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&family=Noto+Serif+KR:wght@200&display=swap'); */
</style>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&family=Roboto:ital,wght@0,100;1,100&display=swap" rel="stylesheet">
<title>냉장고를 부탁해 회원 로그인</title>
			
</head>
<body >
		<%@ include file="/views/common/header2.jsp" %>		
		<div id="innerContentWrapper">
			<form action="/member/memberLogin.do" method="post" id="loginForm">
				<div>
					<H3 style=text-align:center>|  로그인  |</H3>
				</div>
				<br>
				<br>
				<div class="td">
					<input type="text" class="input" name="userId"
						placeholder=" 아이디를 입력해주세요">
				</div>
				<div class="td">
					<input type="password" class="input" name="userPwd"
						placeholder=" 비밀번호를 입력해주세요">
				</div>
				<div class="td">
					<input type="checkbox" value="" id="rememberId" class="float">
					<div class="float">아이디 기억하기</div>
					<div class="float" id="findIdPwd">아이디/비밀번호 찾기</div>
				</div>
				<br>
				<br>
				<div class="td">
					<input type="button" value="로그인" id="login" class="greenBtn input">
				</div>
			</form>
			<div class="td">
				<input type="button" value="회원가입" id="join" class="btn input">
			</div>

			<!-- 알림 Modal -->
			<div class="modal fade Modal" id="alertModal" tabindex="-1"
				aria-labelledby="alertModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-scrollable">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="alertModalLabel">알림메세지</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body" id="alertModalMSG"></div>
						<div class="modal-footer">
							<button type="button" class="greenBtn btn confirmBtn" data-bs-dismiss="modal" id="btn greenBtn">확인</button>
						</div>
					</div>
				</div>
			</div>
		</div>

	<!-- 퀵바 -->
 	 <%@include file="/views/common/quickbar.jsp"%>
	<div class="footer">
	 <%@ include file="/views/common/footer.jsp"%>
	</div> 

	<script>
		$(function() { // 쿠키에서 값을 가져와서 아이디 입력
			var inputUserId = Cookies.get("inputUserId");
			var $userIdInputTag = $("input[name=userId]");

			$userIdInputTag.val(inputUserId);

			if ($userIdInputTag.val() != "") {
				$("#rememberId").attr("checked", true);
			} else {
				$("#rememberId").attr("checked", false);
			}
			
			$(".confirmBtn").css("background-color","#7FB292");
			$(".confirmBtn").css("color","white");
			$(".confirmBtn").hover(function(){
				$(".confirmBtn").css("color","black");
			},function(){
				$(".confirmBtn").css("color","white");
			});
			$(".Modal").css("top","20%");
		})

		$("#login").click(
				function() { // 로그인버튼 클릭시 아아디 기억하기가 체크되어 있다면 쿠키생성
					if ($("#rememberId").is(":checked")) {
						var inputUserId = $("input[name=userId]").val();
						Cookies.set("inputUserId", inputUserId, {expires : 30,path : ""})
					} else {
						Cookies.remove("inputUserId", {path : ""});
					}

					if ($("input[name=userId]").val() == "" || $("input[name=userPwd]").val() == "") { // 아이디 또는 비밀번호를 입력하지 않았다면
						$("#alertModalMSG").html("아이디 또는 비밀번호를 입력해주세요.");
						$("#alertModal").modal("show");
					} else {
						$("form").submit();
					}
				})

		$("#findIdPwd").click(function() { // 아이디 비밀번호 찾기 페이지 이동
			location.replace("/views/member/memberFindIdPwd.jsp");
		})
		$("#join").click(function() { // 회원가입 페이지 이동
			location.replace("/views/member/memberJoin.jsp");
		})
	</script>
	
</body>
</html>