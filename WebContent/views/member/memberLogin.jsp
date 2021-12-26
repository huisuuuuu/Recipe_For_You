<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	<!-- bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<!-- jQuery Cookie -->
	<script src="https://cdn.jsdelivr.net/npm/js-cookie@3.0.1/dist/js.cookie.min.js"></script>
	<%-- Required meta tags --%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

  <style>
        *{
            box-sizing: border-box;
        }
        #innerContentWrapper {
            margin: 0px auto;
            width: 600px;
            height: 950px;
            padding: 20px;
            text-align: center;
        }
        #title{
            margin: auto;
            text-align: center;
            font-size: 23px;
            font-weight: 700;
            border-left: 1px solid black;
            border-right: 1px solid black;
            width: 100px;
            height: 20px;
            line-height: 20px;
        }
         .td{
            width: 350px;
            margin: 0 auto;
        }
        .input{
            height : 50px;
            width : 350px;
            border : 1px solid #707070;
            padding-left: 10px;
            margin: 10px auto;
        }
        .greenBtn{
            background-color: #7FB292;
            color: white;
            border: 0px;
        }
        .float{
            float: left;
            margin-left: 10px;
            line-height: 12px;
            margin: 5px;
        }
        #findIdPwd{
			margin-left: 75px;
            cursor: pointer;
            
        }
        #join{
            border: 1px solid #7FB292;
            color: #7FB292;
        }
        #join:hover{
            color: black;
        }
        .Modal{
        	top: 20%;
        	text-align: left;
        }
        #login:hover{
        	color: black;
        }
    </style>

<title>Insert title here</title>
</head>
<body>

     <div id="wrapper">

        <div class="contents">
        	    <div id="innerContentWrapper">
		        <form action="/member/memberLogin.do" method="post" id="loginForm">
		            <div><div id="title" >로그인</div></div><br><br>
		            <div class="td">
		                <input type="text" class="input" name="userId" placeholder=" 아이디를 입력해주세요">
		            </div>
		            <div class="td">
		                <input type="password" class="input" name="userPwd" placeholder=" 비밀번호를 입력해주세요">
		            </div>
		            <div class ="td">
		            <input type="checkbox" value="" id="rememberId" class="float">
		            <div class="float">아이디 기억하기</div>
		            <div class="float" id="findIdPwd">아이디/비밀번호 찾기</div>
		            </div>
		            <br><br>
		            <div class="td"><input type="button" value="로그인" id="login" class="greenBtn input"></div>
		        </form>
		            <div class="td"><input type="button" value="회원가입" id="join" class="btn input"></div>
		            
			       	<!-- 알림 Modal -->
			    <div class="modal fade Modal" id="alertModal" tabindex="-1" aria-labelledby="alertModalLabel" aria-hidden="true">
			      <div class="modal-dialog modal-dialog-scrollable">
			        <div class="modal-content">
			          <div class="modal-header">
			            <h5 class="modal-title" id="alertModalLabel">알림메세지</h5>
			            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			          </div>
			          <div class="modal-body" id="alertModalMSG">
			          	
			          </div>
			          <div class="modal-footer">
			            <button type="button" class="btn greenBtn" data-bs-dismiss="modal" id="btn">확인</button>
			          </div>
			        </div>
			      </div>
			    </div>     
		    </div>
        </div>
        
        
        <div class="footer">
			<%@ include file="/views/common/footer.jsp" %>
        </div>
    </div>
    
    <script>
     	$(function(){ // 쿠키에서 값을 가져와서 아이디 입력
    		var inputUserId = Cookies.get("inputUserId");
    		var $userIdInputTag = $("input[name=userId]");
    		
    		$userIdInputTag.val(inputUserId);
    		
    		if($userIdInputTag.val()!=""){
    			$("#rememberId").attr("checked", true);	
    		}else{
    			$("#rememberId").attr("checked", false);
    		}
    	})
     
     	$("#login").click(function(){ // 로그인버튼 클릭시 아아디 기억하기가 체크되어 있다면 쿠키생성
    		if($("#rememberId").is(":checked")){
    			var inputUserId = $("input[name=userId]").val();
    			Cookies.set("inputUserId",inputUserId,{expires:30, path:""})
    		}else{
    			Cookies.remove("inputUserId", {path:""});
    		}
     		
     		if($("input[name=userId]").val()=="" || $("input[name=userPwd]").val()==""){ // 아이디 또는 비밀번호를 입력하지 않았다면
     			$("#alertModalMSG").html("아이디 또는 비밀번호를 입력해주세요.");
     			$("#alertModal").modal("show");
     		}else{
    			$("form").submit();
     		}
    	})
    			
    	$("#findIdPwd").click(function(){ // 아이디 비밀번호 찾기 페이지 이동
    		location.replace("/views/member/memberFindIdPwd.jsp");
    	})
    	$("#join").click(function(){ // 회원가입 페이지 이동
    		location.replace("/views/member/memberJoin.jsp");
    	})
    </script>
</body>
</html>