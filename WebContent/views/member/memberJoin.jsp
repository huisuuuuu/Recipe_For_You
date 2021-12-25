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
	<%-- Required meta tags --%>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--font--%>
    <style>    
    @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&family=Noto+Serif+KR:wght@200&display=swap');
    </style>
    <!-- header, footer css -->
    <link rel="stylesheet" type="text/css" href="/assets/css/recipeHeaderFooter.css">
<title>냉장고를 부탁해 회원가입</title>
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
            width: 100px;
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
		.td{
			line-height:46px;
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
        #consent{
            height : 20px;
            width : 20px;
            margin-top: 13px;
        }
        #showTOS{
            margin-right: 110px;
            line-height: 45px;
            float: right;
            color: #7FB292;
        }
        .greenBtn{
            background-color: #7FB292;
            color: white;
            border: 0px;
        }
        .Modal{
            top: 20%
        }
        .vBar{
            font-size: 16px;
            font-weight: 500;
        }
        #joinBtn:hover{
            color: black;
        }
        #ag{
        	line-height: 20px;
        }
        .indent{
        	margin-left: 10px;
        	text-indent: 10px;
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
		        <form action="/member/memberJoin.do" method="post" id="joinForm">
		            <div><div id="title">회원가입</div></div><br><br>
		            
		            <!--아이디-->
		            <div class="th">아이디 </div>
		            <div class="td">
		                <input type="text" name="userId" placeholder=" 6자 이상의 영문 혹은 영문과 숫자 조합" maxlength="12" data-name="아이디">
		                <button id="checkId" type="button">중복확인</button><br>
		            </div>
		            <div class="validation" style="display:none;"><div class="th"></div><span>6자 이상의 영문 혹은 영문과 숫자 조합</span></div><br>
		            
		            <!--비밀번호-->
		            <div class="th">비밀번호 </div>
		            <div class="td">
		                <input type="password" name="userPwd" placeholder=" 비밀번호를 입력해주세요" maxlength="14" data-name="비밀번호">
		            </div>
		            <div class="validation" style="display:none;"><div class="th"></div><span>8자 이상의 영문, 숫자, 특수문자 조합</span></div><br>
		            
		            <!--비밀번호 확인-->
		            <div class="th">비밀번호 확인</div>
		            <div class="td">
		                <input type="password" name="userPwd_re" placeholder=" 비밀번호를 한번 더 입력해주세요" maxlength="14" data-name="비밀번호 확인">
		            </div>
		            <div class="validation" style="display:none;"><div class="th"></div><span>비밀번호를 확인해주세요</span></div><br>
		
		            <!--이름-->
		            <div class="th">이름 </div>
		            <div class="td">
		            <input type="text" name="userName" placeholder=" 이름을 입력해주세요" data-name="이름" maxlength="10">
		            </div>
		            <div class="validation" style="display:none;"><div class="th"></div><span>2자 이상의 한글</span></div><br>
		
		            <!--이메일-->
		            <div class="th">이메일 </div>
		            <div class="td">
			            <input type="email" name="userEmail" placeholder=" 예: member@join.com" data-name="이메일">
		                <button id="checkEmail" type="button">인증하기</button><br>
		            </div>
					<div class="validation" style="display:none;"><div class="th"></div><span>이메일 형식으로 작성</span></div><br>
					<div style="display:none;" id="inputAuthKey">
						<div class="th">인증번호</div>
						<div class="td">
							<input type="text" id="authKeyNo" placeholder="인증번호를 입력하세요" />
							<button id="authKeyCheck" type="button">인증번호확인</button><br>
						</div>
					</div><br>
		
		            <!--전화번호-->
		            <div class="th">전화번호 </div>
		            <div class="td">
		                <input type="text" name="userPhone" placeholder=" 숫자만 입력해주세요" maxlength="11" data-name="전화번호">
		            </div>
		            <div class="validation" style="display:none;"><div class="th"></div><span>숫자만 입력</span></div><br>
		
		            <!--이용약관동의-->
		            <div class="th">이용약관 </div>
		            <div class="td">
		                <label><input type="checkbox" name="userConsent" id="consent" class="form-check-input" value="Y" vali="T"> 동의</label>
		                <a type="button" id="showTOS" data-bs-toggle="modal" data-bs-target="#tosModal">약관보기 &gt; </a>
		            </div><br>
		                 
		            <br><br><br>
		            <div class="th"></div><input type="button" value="회원가입" id="joinBtn" class="greenBtn">
		        </form>
		            <!-- 이용약관 Modal -->
		            <div class="modal fade" id="tosModal" tabindex="-1" aria-labelledby="tosModalLabel" aria-hidden="true">
		              <div class="modal-dialog modal-dialog-scrollable">
		                <div class="modal-content">
		                  <div class="modal-header">
		                    <h5 class="modal-title" id="tosModalLabel">이용약관</h5>
		                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		                  </div>
		                  <div class="modal-body" id="tosBody">
		                    	< 2팀 >('www.Recipe_For_You.co.kr'이하 '냉장고를 부탁해')은(는) 「개인정보 보호법」 제30조에 따라 정보주체의 개인정보를 보호하고 이와 관련한 고충을 신속하고 원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보 처리방침을 수립·공개합니다. <br />
									○ 이 개인정보처리방침은 2021년 12월 21부터 적용됩니다. <br /><br />
									제1조(개인정보의 처리 목적) <br />
										<p class="indent">
										< 2팀 >('www.Recipe_For_You.co.kr'이하 '냉장고를 부탁해')은(는) 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며 이용 목적이 변경되는 경우에는 「개인정보 보호법」 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다. <br />
										1. 홈페이지 회원가입 및 관리 <br />
										회원 가입의사 확인, 회원제 서비스 제공에 따른 본인 식별·인증, 회원자격 유지·관리, 서비스 부정이용 방지, 만14세 미만 아동의 개인정보 처리 시 법정대리인의 동의여부 확인 목적으로 개인정보를 처리합니다.<br />
										2. 재화 또는 서비스 제공<br />
										서비스 제공, 콘텐츠 제공, 맞춤서비스 제공, 본인인증을 목적으로 개인정보를 처리합니다.<br />
										3. 마케팅 및 광고에의 활용 <br />
										신규 서비스(제품) 개발 및 맞춤 서비스 제공, 서비스의 유효성 확인 등을 목적으로 개인정보를 처리합니다. 
										</p><br />
									제2조(개인정보의 처리 및 보유 기간)<br />
										<p class="indent">
										① < 2팀 >은(는) 법령에 따른 개인정보 보유·이용기간 또는 정보주체로부터 개인정보를 수집 시에 동의받은 개인정보 보유·이용기간 내에서 개인정보를 처리·보유합니다.<br />
										② 각각의 개인정보 처리 및 보유 기간은 다음과 같습니다.<br />
										1.<홈페이지 회원가입 및 관리><br />
										<홈페이지 회원가입 및 관리>와 관련한 개인정보는 수집.이용에 관한 동의일로부터<지체없이 파기>까지 위 이용목적을 위하여 보유.이용됩니다.<br />
										보유근거 : 개인정보보호법
										</p><br />
									제3조(개인정보의 제3자 제공)<br />
										<p class="indent">
										① < 2팀 >은(는) 개인정보를 제1조(개인정보의 처리 목적)에서 명시한 범위 내에서만 처리하며, 정보주체의 동의, 법률의 특별한 규정 등 「개인정보 보호법」 제17조 및 제18조에 해당하는 경우에만 개인정보를 제3자에게 제공합니다.<br />
										② < 2팀 >은(는) 다음과 같이 개인정보를 제3자에게 제공하고 있습니다.<br />
										1. < 냉장고를 부탁해 ><br />
										개인정보를 제공받는 자 : 냉장고를 부탁해<br />
										제공받는 자의 개인정보 이용목적 : 이메일, 휴대전화번호, 비밀번호, 로그인ID, 이름<br />
										제공받는 자의 보유.이용기간: 지체없이 파기
										</p><br />
									제4조(개인정보처리 위탁)<br /><br />
									제5조(정보주체와 법정대리인의 권리·의무 및 그 행사방법)<br />
										<p class="indent">
										① 정보주체는 2팀에 대해 언제든지 개인정보 열람·정정·삭제·처리정지 요구 등의 권리를 행사할 수 있습니다.<br />
										② 제1항에 따른 권리 행사는2팀에 대해 「개인정보 보호법」 시행령 제41조제1항에 따라 서면, 전자우편, 모사전송(FAX) 등을 통하여 하실 수 있으며 2팀은(는) 이에 대해 지체 없이 조치하겠습니다.<br />
										③ 제1항에 따른 권리 행사는 정보주체의 법정대리인이나 위임을 받은 자 등 대리인을 통하여 하실 수 있습니다.이 경우 “개인정보 처리 방법에 관한 고시(제2020-7호)” 별지 제11호 서식에 따른 위임장을 제출하셔야 합니다.<br />
										④ 개인정보 열람 및 처리정지 요구는 「개인정보 보호법」 제35조 제4항, 제37조 제2항에 의하여 정보주체의 권리가 제한 될 수 있습니다.<br />
										⑤ 개인정보의 정정 및 삭제 요구는 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다.<br />
										⑥ 2팀은(는) 정보주체 권리에 따른 열람의 요구, 정정·삭제의 요구, 처리정지의 요구 시 열람 등 요구를 한 자가 본인이거나 정당한 대리인인지를 확인합니다.
										</p><br />
									제6조(처리하는 개인정보의 항목 작성)<br />
										<p class="indent">
										① < 2팀 >은(는) 다음의 개인정보 항목을 처리하고 있습니다.<br />
										1< 홈페이지 회원가입 및 관리 ><br />
										필수항목 : 이메일, 휴대전화번호, 비밀번호, 로그인ID, 이름
										</p><br />
									제7조(개인정보의 파기)<br />
										<p class="indent">
										① < 2팀 > 은(는) 개인정보 보유기간의 경과, 처리목적 달성 등 개인정보가 불필요하게 되었을 때에는 지체없이 해당 개인정보를 파기합니다.<br />
										② 개인정보 파기의 절차 및 방법은 다음과 같습니다.<br />
										1. 파기절차<br />
										< 2팀 > 은(는) 파기 사유가 발생한 개인정보를 선정하고, < 2팀 > 의 개인정보 보호책임자의 승인을 받아 개인정보를 파기합니다.<br />
										2. 파기방법<br />
										전자적 파일 형태의 정보는 기록을 재생할 수 없는 기술적 방법을 사용합니다.
										</p><br />
									제8조(개인정보의 안전성 확보 조치)<br />
										<p class="indent">
										< 2팀 >은(는) 개인정보의 안전성 확보를 위해 다음과 같은 조치를 취하고 있습니다.<br />
										1. 정기적인 자체 감사 실시<br />
										개인정보 취급 관련 안정성 확보를 위해 정기적(분기 1회)으로 자체 감사를 실시하고 있습니다.<br />
										2. 개인정보 취급 직원의 최소화 및 교육<br />
										개인정보를 취급하는 직원을 지정하고 담당자에 한정시켜 최소화 하여 개인정보를 관리하는 대책을 시행하고 있습니다.
										</p><br />
									제9조(개인정보 자동 수집 장치의 설치•운영 및 거부에 관한 사항)<br />
										<p class="indent">
										① 2팀 은(는) 이용자에게 개별적인 맞춤서비스를 제공하기 위해 이용정보를 저장하고 수시로 불러오는 ‘쿠키(cookie)’를 사용합니다.<br />
										② 쿠키는 웹사이트를 운영하는데 이용되는 서버(http)가 이용자의 컴퓨터 브라우저에게 보내는 소량의 정보이며 이용자들의 PC 컴퓨터내의 하드디스크에 저장되기도 합니다.<br />
										가. 쿠키의 사용 목적 : 이용자가 방문한 각 서비스와 웹 사이트들에 대한 방문 및 이용형태, 인기 검색어, 보안접속 여부, 등을 파악하여 이용자에게 최적화된 정보 제공을 위해 사용됩니다.<br />
										나. 쿠키의 설치•운영 및 거부 : 웹브라우저 상단의 도구>인터넷 옵션>개인정보 메뉴의 옵션 설정을 통해 쿠키 저장을 거부 할 수 있습니다.<br />
										다. 쿠키 저장을 거부할 경우 맞춤형 서비스 이용에 어려움이 발생할 수 있습니다.<br /><br />
										</p>
									제10조 (개인정보 보호책임자)<br />
										<p class="indent">
										① 2팀 은(는) 개인정보 처리에 관한 업무를 총괄해서 책임지고, 개인정보 처리와 관련한 정보주체의 불만처리 및 피해구제 등을 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다.<br />
										▶ 개인정보 보호책임자<br />
										성명 : 홍길동<br />
										직책 : 대표이사<br />
										직급 : 사장<br />
										연락처 : 02-1234-5678, recipe@recipe.co.kr, 02-1234-5678<br />
										※ 개인정보 보호 담당부서로 연결됩니다.<br />
										▶ 개인정보 보호 담당부서<br />
										부서명 : 개인정보관리팀<br />
										담당자 : 홍길동<br />
										연락처 : 02-1234-5679, recipe@recipe.co.kr, 02-1234-5678<br />
										② 정보주체께서는 2팀 의 서비스(또는 사업)을 이용하시면서 발생한 모든 개인정보 보호 관련 문의, 불만처리, 피해구제 등에 관한 사항을 개인정보 보호책임자 및 담당부서로 문의하실 수 있습니다. 2팀 은(는) 정보주체의 문의에 대해 지체 없이 답변 및 처리해드릴 것입니다.
										</p><br />
									제11조(개인정보 열람청구)<br />
										<p class="indent">
										정보주체는 ｢개인정보 보호법｣ 제35조에 따른 개인정보의 열람 청구를 아래의 부서에 할 수 있습니다.<br />
										< 2팀 >은(는) 정보주체의 개인정보 열람청구가 신속하게 처리되도록 노력하겠습니다.<br />
										▶ 개인정보 열람청구 접수·처리 부서<br />
										부서명 : 개인정보관리팀<br />
										담당자 : 홍길공<br />
										연락처 : 02-1234-5678, recipe@recipe.co.kr, 02-1234-5678
										</p><br />
									제12조(권익침해 구제방법)<br />
										<p class="indent">
										정보주체는 개인정보침해로 인한 구제를 받기 위하여 개인정보분쟁조정위원회, 한국인터넷진흥원 개인정보침해신고센터 등에 분쟁해결이나 상담 등을 신청할 수 있습니다. 이 밖에 기타 개인정보침해의 신고, 상담에 대하여는 아래의 기관에 문의하시기 바랍니다.<br />
										1. 개인정보분쟁조정위원회 : (국번없이) 1833-6972 (www.kopico.go.kr)<br />
										2. 개인정보침해신고센터 : (국번없이) 118 (privacy.kisa.or.kr)<br />
										3. 대검찰청 : (국번없이) 1301 (www.spo.go.kr)<br />
										4. 경찰청 : (국번없이) 182 (ecrm.cyber.go.kr)<br />
										「개인정보보호법」제35조(개인정보의 열람), 제36조(개인정보의 정정·삭제), 제37조(개인정보의 처리정지 등)의 규정에 의한 요구에 대 하여 공공기관의 장이 행한 처분 또는 부작위로 인하여 권리 또는 이익의 침해를 받은 자는 행정심판법이 정하는 바에 따라 행정심판을 청구할 수 있습니다.<br />
										※ 행정심판에 대해 자세한 사항은 중앙행정심판위원회(www.simpan.go.kr) 홈페이지를 참고하시기 바랍니다.
										</p><br />
									제13조(개인정보 처리방침 변경)<br />
										<p class="indent">
										① 이 개인정보처리방침은 2021년 12월 21부터 적용됩니다.
										</p><br />
		                  </div>
		                  <div class="modal-footer">
		                    <button type="button" class="btn greenBtn" data-bs-dismiss="modal">확인</button>
		                  </div>
		                </div>
		              </div>
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
		                    <button type="button" class="btn greenBtn" data-bs-dismiss="modal">확인</button>
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
		var authKey;
        //입력받은 값 유효성검사 호출
        $("input[name]").keyup(function() {
            var exp = "";
            
            //입력하고 있는 데이터에 맞는 정규식을 찾아 exp에 넣음
            if ($(this).attr("name") == "userId") { // 아이디
                exp = "^[a-z0-9]{6,12}$";
            } else if ($(this).attr("name") == "userPwd") { // 비밀번호
                exp = "^(?=.+[a-z])(?=.+[0-9])(?=.+[~!@#$%^&*-+]).{8,14}";
            } else if ($(this).attr("name") == "userPwd_re") {//비밀번호 확인    
                var pw=$("input[name=userPwd]").val();
               	if($(this).val()==pw){
                    $(this).parent().next().children().next().css("color", "green");
                    $(this).attr("vali","T");
                }else{
                    $(this).parent().next().children().next().css("color", "red");
                    $(this).attr("vali","F");
                }
                $(this).parent().next().css("display","block");
                return;
            } else if ($(this).attr("name") == "userName") {//이름 확인
            	exp = "^[가-힣]{2,10}$";
            } else if ($(this).attr("name") == "userEmail") {//이메일 확인
            	exp = ".+@.+";
            } else if ($(this).attr("name") == "userPhone") {//전화번호 확인
            	exp = "^[0-9]{10,11}$";
            }
                $(this).parent().next().css("display","block");
            
            //정규식 결과에 따라 span 태그에 있는 메시지를 보여줌
            var result = checkValidation($(this).val(), exp);
            if (result) {
                    $(this).parent().next().children().next().css("color", "green");
                    $(this).attr("vali","T");
            } else {
                    $(this).parent().next().children().next().css("color", "red");
                    $(this).attr("vali","F");
            }
            $(this).parent().next().css("display","block");
        });
        
        //입력값과 정규식을 매개변수로 받아 유효성 검사
        function checkValidation(value, exp) {
            var exp = new RegExp(exp);
            return exp.test(value);
        }

        // 아이디 중복 확인
        $("#checkId").click(function() {
            var userId = $("input[name=userId]").val();
            var exp = "^[a-z0-9]{6,12}$";
        	var $msg = $("#alertModalMSG");
        	var $modal = $("#alertModal");
            
            	//아이디 중복확인전 다시한번 아이디 유효성 검사
            if(checkValidation(userId,exp)){
	            $.ajax({
	            	url : "/member/AjaxIdCheck.do",
	            	type : "post",
	            	data : {"userId":userId},
	            	success : function(idCheck){
	            		// idcheck값으로 1이 오면 중복된 아이디
	            		if(idCheck==0){
	            			$("input[name=userId]").attr("idCheck",userId) // userId 태그에 아이디 사용가능 표시로 속성을 추가
	            			$msg.html("사용가능한 아이디입니다.");
	            			$modal.modal("show");
	            		}else{
	            			$msg.html("이미 사용중인 아이디입니다.");
	            			$modal.modal("show");
	            		}	
	            	},
	            	error : function(){
	            		location.replace("/views/commons/error.jsp")
	            	}
	            })
            }else{
            	$msg.html("6자 이상의 영문 혹은 영문과 숫자 조합만 가능합니다.");
            	$modal.modal("show");
            };
        })
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
	    			$("input[name=userEmail]").attr("authCon",$("input[name=userEmail]").val());
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
        
			// 회원가입 버튼을 클릭하여 폼태그 사항을 submit하기전 입력사항 검사
        $("#joinBtn").click(function(){
        	var $msg = $("#alertModalMSG");
        	var $modal = $("#alertModal");
        	var $form = $("#joinForm");
        	var $userId = $("input[name=userId]");

        	$.each($("input[name]:not(input[name=checkbox])"), function(index, item){
        			// 입력값이 없을 경우
        		if($(this).val()==""){
        				var dataName = $(this).attr("data-name");
        				$msg.html(dataName+"을(를) 입력해주세요.");
                    	$modal.modal("show");
                    	$form = "";
	                return false;
        		}
        			// 이용약관동의 체크를 하지 않았을 경우
	        	if(!$("#consent").prop("checked")){
	        		$msg.html("이용약관에 동의해주세요.");
	        		$modal.modal("show");
		           	$form = "";
		            return false;
	        	}
        			// 유효성 검사규칙에 맞지 않을 경우
 	        	if($(this).attr("vali")!="T"){
 	        		$msg.html("입력정보를 확인해주세요.");
 	        		$modal.modal("show");
	            	$form = "";
	            	$(this).focus();
	            	console.log($(this).val());
	            	return false;
    			}
 	       		// 이메일 인증을 하지 않았을 경우 
 	        	if($("input[name=userEmail]").attr("authCon")!=$("input[name=userEmail]").val()){
 	        		$msg.html("이메일 인증을 해주세요");
 	        		$modal.modal("show");
	            	$form = "";
	            	return false;
 	        	}
        			// 아이디 중복확인을 하지 않았을 경우 
 	        	if($userId.attr("idCheck")!=$userId.val()){
 	        		$msg.html("아이디 중복확인을 해주세요");
 	        		$modal.modal("show");
	            	$form = "";
	            	return false;
 	        	}
        	})
        	if($form!=""){
        		$form.submit();
        	}
        })
    </script>    
</body>
</html>