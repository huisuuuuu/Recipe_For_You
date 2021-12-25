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
			        <form action="/member/memberPwdChange.do" method="post" id="pwdChangeForm">
			            <div><div id="title">비밀번호 변경</div></div><br><br>
			            
			            <!--비밀번호-->
			            <div class="th">비밀번호 </div>
			            <div class="td">
			                <input type="password" name="userPwd" placeholder=" 비밀번호를 입력해주세요">
			            </div>
			            <div class="validation" style="display:none;"><div class="th"></div><span>8자 이상의 영문, 숫자, 특수문자 조합</span></div><br>
			            <!--비밀번호 확인-->
			            <div class="th">비밀번호 확인</div>
			            <div class="td">
			                <input type="password" name="userPwd_re" placeholder=" 비밀번호를 한번 더 입력해주세요">
			            </div>
			            <div class="validation" style="display:none;"><div class="th"></div><span>비밀번호를 확인해주세요</span></div><br>
			 
			            <br>
			            <div class="th"></div><input type="submit" value="변경하기" id="submit" class="greenBtn">
			        </form>
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
    		// 비밀번호 유효성 검사
    	$("input[name=userPwd]").keyup(function(){
    		var pwd = $(this).val();
    		var number = pwd.search(/\d/);
    		var alphabet = pwd.search(/[a-z]/ig);
    		var special = pwd.search(/\W/);
    		var blank = pwd.search(/\s/);
    			
    		if(pwd.length<8 || number == -1 || alphabet == -1 || special == -1 || blank != -1){
    			$(this).parent().next().children().next().css("color", "red");
    			$(this).attr("vali","F");
    		}else{
    			$(this).parent().next().children().next().css("color", "green");
    			$(this).attr("vali","T");
    		}
    		$(this).parent().next().css("display","block");
    	})
    	
    		//비밀번호 확인
    	$("input[name=userPwd_re]").keyup(function(){
    		var pwd_re = $(this).val();
    		var pwd = $("input[name=userPwd]").val();

    		if(pwd!=pwd_re){
    			$(this).parent().next().children().next().css("color", "red");
    			$(this).attr("vali","F");
    		}else{
    			$(this).parent().next().children().next().css("color", "green");
    			$(this).attr("vali","T");
    		}
    		$(this).parent().next().css("display","block");
    	})
    	
    		// 서브밋 전 입력 사항 확인
		$("#update").click(function(){
        	var $msg = $("#alertModalMSG");
        	var $modal = $("#alertModal");
        	var $form = $("#pwdChangeForm");

       		if($(this).val()==""){
 				var dataName = $(this).attr("data-name");
 				$msg.html(dataName+"을(를) 입력해주세요.");
             	$modal.modal("show");
             	$form = "";
                return false;
       		}
       		
      			// 유효성 검사규칙에 맞지 않을 경우
        	if($(this).attr("vali")!="T"){
	       		$msg.html("입력정보를 확인해주세요.");
	       		$modal.modal("show");
	           	$form = "";
	           	return false;
  			}
      			
        	if($form!=""){
        		$form.submit();
        	}
        })

    </script>
</body>
</html>