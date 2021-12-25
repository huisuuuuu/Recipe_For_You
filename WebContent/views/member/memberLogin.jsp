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
    <%--font--%>
    <style>    
    @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&family=Noto+Serif+KR:wght@200&display=swap');
    </style>
    <!-- header, footer css -->
    <link rel="stylesheet" type="text/css" href="/assets/css/recipeHeaderFooter.css">
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
    </style>

<title>Insert title here</title>
</head>
<body>
     <div id="wrapper">
        <div class="header fixed-top" style="position:fixed;background-color: white">
            <div class="top">
                <div class=top-img><img src="/assets/common/images/topicon.png" alt="" width="18px" height="25px"></div>
                <span class="top1 align">마이냉장고에 나만의 식재료를 등록하고 레시피를 추천 받아 보세요!</span>
            </div>
            <div class="header1">
                <a href="" class="logo">
                    <img src="/assets/common/images//main%20logo.png" alt="">
                </a>
                <div class="box-user">
                   <a href="" class="mypage">
                    <img src="/assets/common/images//headericon1.png" alt="" width="23px" height="28px">
                </a>
                <a href="" class="login">
                    <img src="/assets/common/images//headericon2.png" alt="" width="80px" height="30px">
                </a>
                </div>
                
            </div>

            <div class="navibar container-fluid">
                <nav class="navbar navbar-expand-lg nav-distance">
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active nav-about" href="#">ABOUT</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active nav-recipe" href="/recipe/recipeBoard/selectAll.do">레시피</a>
                                <ul class="submenu">
                                    <li><a href="">모두보기</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=hansik">한식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=yangsik">양식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=ilsik">일식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=jungsik">중식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=bunsik ">분식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=vege">채식</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=dite">다이어트</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=banchan">밑반찬</a></li>
                                    <li><a href="/recipe/recipeBoardSelectList.do?recipe=annju">안주</a></li>
                                </ul>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link active nav-info" href="#">고객센터</a>
                                <ul class="submenu">
                                    <li><a href="">공지사항</a></li>
                                    <li><a href="">자주하는질문</a></li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active nav-mypage" href="#">마이냉장고</a>
                            </li>
                        </ul>
                    </div>
                </nav>
                <div class=" box-search d-flex justify-content-center h-100">
                    <div class="searchbar">
                        <input class="search_input" type="text" name="" placeholder="">
                        <a href="" class="search_icon"><i class="fas fa-search"></i></a>
                    </div>
                </div>

            </div>
        </div>
        
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
		            <div class="td"><input type="button" value="로그인" id="login" class="btn greenBtn input"></div>
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
            <div class="footer1 container">
            <pre>
(주)컬쳐히어로
            
            
상호:(주)컬쳐히어로   대표자:양준규  개인정보관리책임자:장호진  사업자 등록번호:144-81-35400
            
통신판매업 신고:제 2015-경기성남-1940호  전화:1833-8307  팩스:031-8017-1800
            
주소:(13487) 경기도 성남시 분당구 삼평동625 판교세븐벤처밸리1단지 제3동 1001호 이메일:commerce@culturehero.net
            </pre>
            </div>
            <div class="footer-bottom">
               <div id="footer-word">
                <pre>      이용약관   개인정보처리방침   공지사항   자주묻는질문   광고/제휴문의:contact@culturehero.net</pre>
                </div>
            </div>
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