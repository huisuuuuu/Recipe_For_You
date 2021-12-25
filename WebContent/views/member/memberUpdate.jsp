<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	<!-- bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
	<meta charset="UTF-8">
	<%-- Required meta tags --%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--font--%>
    <style>    
    @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&family=Noto+Serif+KR:wght@200&display=swap');
    </style>
    <!-- header, footer css -->
    <link rel="stylesheet" type="text/css" href="/assets/css/recipeHeaderFooter.css">	
<title>Insert title here</title>
    <style>
        *{
            box-sizing: border-box;
        }
        #innerContentWrapper {
            margin: 0px auto;
            width: 600px;
            height: 950px;
            padding: 20px;
        }
        #title{
            margin: auto;
            text-align: center;
            font-size: 23px;
            font-weight: 700;
            border-left: 1px solid black;
            border-right: 1px solid black;
            width: 150px;
            height: 20px;
            line-height: 20px;
        }
        .th {
            float: left;
            width: 100px;
            height : 48px;
            text-align: left;
            line-height: 48px;
            font-size: 16px;
        }
        input{
            height : 50px;
            width : 350px;
            border : 1px solid #707070;
            padding-left: 10px;
        }
        button{
            height : 50px;
            width : 100px;
            background-color: white;
            border: 1px solid #7FB292;
            color: #7FB292;
        }
        button:hover{
            font-weight: bolder;
        }
        .validation{
            width : 350px;
            height : 24px;
        }
        .greenBtn{
            background-color: #7FB292;
            color: white;
            border: 0px;
        }
        .vBar{
            font-size: 16px;
            font-weight: 500;
        }
        #submit:hover{
            color: black;
        }
        .textDiv{
            line-height: 45px;
            
        }
        .Modal{
            top: 20%
        }
        .disabledInputTag{
        	border: none;
        	background-color: white;
        	color: black;
        }
    </style>
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
			        <form action="/member/memberUpdate.do" method="post" id="updateForm">
			            <div><div id="title">개인정보 수정</div></div><br><br>
			            
			            <!--아이디-->
			            <div class="th">아이디 </div>
			            <div class="td">
			                <input type="text" class="disabledInputTag" value="${sessionScope.member.userId }" disabled/>
			            </div><br>
			            
			            <!--이름-->
			            <div class="th">이름 </div>
			            <div class="td">
			            	<input type="text" class="disabledInputTag" value="${sessionScope.member.userName }" disabled/>
			            </div><br>
			            
			            <!--이메일-->
			            <div class="th">이메일 </div>
			            <div class="td">
			            <input type="email" name="userEmail"  placeholder=" 예: member@join.com" data-name="이메일" vali="T">
			            <button id="checkEmail" type="button">인증하기</button><br>
			            </div><br />
			            <div style="display:none;" id="inputAuthKey">
							<div class="th">인증번호</div>
							<div class="td">
								<input type="text" id="authKeyNo" placeholder="인증번호를 입력하세요" />
								<button id="authKeyCheck" type="button">인증번호확인</button><br>
							</div><br />
						</div>
			            
			            <!--전화번호-->
			            <div class="th">전화번호 </div>
			            <div class="td">
			                <input type="text" name="userPhone" placeholder=" 숫자만 입력해주세요" data-name="전화번호" vali="T">
			            </div>
			            <br><br><br><br>
			            <div class="th"></div><input type="button" value="개인정보 수정" id="update" class="greenBtn">
			        </form><br />
			        	<div class="th"></div><input type="button" value="회원탈퇴" id="withDraw" class="greenBtn">
			    </div>
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
	   		<!-- 회원탈퇴 Modal -->
	            <div class="modal fade Modal" id="withdrawModal" tabindex="-1" aria-labelledby="alertModalLabel" aria-hidden="true">
	              <div class="modal-dialog modal-dialog-scrollable">
	                <div class="modal-content">
	                  <div class="modal-header">
	                    <h5 class="modal-title" id="alertModalLabel">알림메세지</h5>
	                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                  </div>
	                  <div class="modal-body" id="alertModalMSG">
							회원탈퇴를 진행하시겠습니까?
	                  </div>
	                  <div class="modal-footer">
	                    <button type="button" class="btn greenBtn" data-bs-dismiss="modal">취소</button>
	                    <button type="button" class="btn greenBtn" data-bs-dismiss="modal" id="withdrawBtn">회원탈퇴</button>
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
    	$(function(){
    		$("input[name=userEmail]").val("${sessionScope.member.userEmail }");
    		$("input[name=userPhone]").val("${sessionScope.member.userPhone }");
    	})
    	
    	$("input[placeholder]").blur(function(){
    		$(this).val($(this).val());
    	})
    	
	    $("input[name=userEmail]").keyup(function(){
	    	exp = ".+@.+";
	    	$(this).removeAttr("vali");
	    	if(checkValidation($(this).val(), exp)){
	    		$(this).attr("vali","T");
	    	}
	    });
	    $("input[name=userPhone]").keyup(function(){
	    	exp = "^[0-9]{10,11}$";
	    	$(this).removeAttr("vali");
	    	if(checkValidation($(this).val(), exp)){
	    		$(this).attr("vali","T");
	    	}
	    });
    	
	    
        //입력값과 정규식을 매개변수로 받아 유효성 검사
        function checkValidation(value, exp) {
            var exp = new RegExp(exp);
            return exp.test(value);
        }
    	
        	// 이메일 인증
        $("#checkEmail").click(function(){
        	var email = $("input[name=userEmail]").val();
        	var exp = ".+@.+";
        	var $msg = $("#alertModalMSG");
        	var $modal = $("#alertModal");

        	// 인증번호를 보내기전 email 유효성 검사
            if(checkValidation(email,exp)){
	            $.ajax({
	            	url : "/member/AjaxEmailCheck.do",
	            	type : "post",
	            	data : {"userEmail":email},
	            	success : function(emailCheck){
	            		// emailCheck 값으로 fail이 오면 중복된 이메일
	            		var result = $.trim(emailCheck);
	            		if(result!="fail"){
	            			$("input[name=userEmail]").attr("emailCheck",email) // userEmail 태그에 인증번호를 보낸 이메일주소를 속성으로 추가
	            			$("#inputAuthKey").css("display","block");
	            			authKey = emailCheck;
	            			$msg.html("입력하신 이메일주소로 인증번호를 발송하였습니다.");
	            			$modal.modal("show");
	            		}else{
	            			$msg.html("이미 사용중인 이메일입니다.");
	            			$modal.modal("show");
	            		}	
	            	},
	            	error : function(){
	            		location.replace("/views/commons/error.jsp")
	            	}
	            })
            }else{
            	$msg.html("이메일 형식이 아닙니다.");
            	$modal.modal("show");
            };
        })
        	//인증번호 확인
        $("#authKeyCheck").click(function(){
        	var authKeyNo = $("#authKeyNo").val();
        	var $msg = $("#alertModalMSG");
        	var $modal = $("#alertModal");
        	
        	if($("input[name=userEmail]").attr("emailCheck")==$("input[name=userEmail]").val()){
	        	if(authKeyNo = authKey){
	    			$msg.html("인증되었습니다.");
	    			$modal.modal("show");
	    			$("input[name=userEmail]").attr("emailCheck",$("input[name=userEmail]").val());
	    			return ;
	        	}else{
	        		$msg.html("인증번호가 다릅니다.");
	    			$modal.modal("show");
	    			$("input[name=userEmail]").removeAttr("emailCheck");
	    			return ;
	        	}
	        	$msg.html("이메일주소가 변경되었습니다.");
    			$modal.modal("show");
        	}
        	
        })
	    
    		// 개인정보수정 버튼을 클릭하여 폼태그 사항을 submit하기전 입력사항 검사
        $("#update").click(function(){
        	var $msg = $("#alertModalMSG");
        	var $modal = $("#alertModal");
        	var $form = $("#updateForm");
        	var $userEmail = $("input[name=userEmail]");

        	$.each($("input[name]"), function(index, item){
        			// 입력값이 없을 경우
        		if($(this).val()==""){
        				var dataName = $(this).attr("data-name");
        				$msg.html(dataName+"을(를) 입력해주세요.");
                    	$modal.modal("show");
                    	$form = "";
	                return false;
        		}
        			
 	       			// 이메일 인증을 하지 않았을 경우
 	       		if($userEmail.val()!="${sessionScope.member.userEmail }"){
	 	        	if($userEmail.attr("emailCheck")!=$userEmail.val()){
	 	        		$msg.html("이메일 인증을 해주세요");
	 	        		$modal.modal("show");
		            	$form = "";
		            	return false;
	 	        	}
 	       		}
        			// 유효성 검사규칙에 맞지 않을 경우
 	        	if($(this).attr("vali")!="T"){
 	        		$msg.html("입력정보를 확인해주세요.");
 	        		$modal.modal("show");
	            	$form = "";
	            	return false;
    			}
        	})
        	if($form!=""){
        		$form.submit();
        	}
        })
    	
    	// 회원탈퇴
    	$("#withDraw").click(function(){
			$("#withdrawModal").modal("show");
    	})
    	$("#withdrawBtn").click(function(){
    		location.replace("/member/memberWithDraw.do");
    	})
    </script>
</body>
</html>