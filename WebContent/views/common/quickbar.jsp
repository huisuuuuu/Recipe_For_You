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
    <!-- javascript sdk -->
	<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<!-- jQuery Cookie --> 
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.js" integrity="sha512-aUhL2xOCrpLEuGD5f6tgHbLYEXRpYZ8G5yD+WlFrXrPy2IrWBlu6bih5C9H6qGsgqnU6mgx6KtU8TreHpASprw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<title>Insert title here</title>
<style>
	#quickNavBar{
		width: 100px;
        position: absolute;
        z-index: 1;
        right: 20px;
        top: 300px;
        letter-spacing: -0.3px;
        transition: top 0.4s;

	}
	#qnbWrapper{
		display: none;
		width: 100px;
        transition-delay: 0.5;
        transition-duration: 0.5;
        margin: 3px auto;
        border: 1px solid #5E5E5E;
        background-color: white;
	}
	#qnbWrapper > div{
		font-family: 'Noto Sans KR', sans-serif;
		font-size: 10px;
		width: 100px;
		text-align: center;
		margin: 0px auto;
	}
	#recentRecipe{
		padding: 0px;

	}
	#recentRecipe > li{
		list-style: none;
		padding: 5px;
		margin: 0px auto;
		width: 100%;
		font-size: 12px;
		text-align: center;
    	font-family: 'Noto Sans KR', sans-serif;
	}
	#channel-chat-button{
		cursor:pointer;
		padding-left: 4px;
	}
	@media screen and (max-width: 1150px){
		#quickNavBar{
			display:none;
		}
	}
</style>
	<div id="quickNavBar">
		<!-- 최근 본 레시피 -->
		<div id="qnbWrapper">
			<ul id="recentRecipe">
			</ul>
		</div>
		<!-- 카카오 채팅상담 -->
		<div id="kakaoChat">
			<!-- http://pf.kakao.com/_QEIxjb/chat -->
			<a id="channel-chat-button" href="#" onclick="void chatChannel();">
			  <img src="/assets/common/images/kakaochat.png" width="90px" height="35px"/>
			</a>
		    <script>
		    	// SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
		        Kakao.init('667735');
		
		        // SDK 초기화 여부를 판단합니다.
		        console.log(Kakao.isInitialized());
		    </script>

			<script type="text/javascript">
			  function chatChannel() {
			    Kakao.Channel.chat({
			      channelPublicId: '_QEIxjb',
			    })
			  }
			</script>
		</div>
	</div>
    
   	<script>
    $(function(){
        // QNB 이동
        $(window).scroll(function(){
        // QNB 기본 위치
        scrollHeight = 300;
        // 스크롤 위치에 맞춰 QNB 위치 이동    
	        if($(window).scrollTop() > scrollHeight){
	            var top = $(window).scrollTop() + scrollHeight;
	            $("#quickNavBar").css("top",top+"px");
	        }else{
	            $("#quickNavBar").css("top",scrollHeight);
	        }
      	})
      	
        // 쿠키값을 가지고와서 퀵네비바에 보여주기
        if($.cookie("boardNo")!=undefined){
        	var boardNo = $.cookie("boardNo").split(",");                         
			var currentPage = $.cookie("page").split(",");
			var filePath = $.cookie("imgAddr").split(",");
			$("#qnbWrapper").css("display","block")
			$("#recentRecipe").append("<li>최근 본 레시피</li>");
   		// 쿠키에 있는 최근 본 레시피 QNB에 넣어주기   			
			for(i=0;i<boardNo.length;i++){
				item = "<li><a href='/recipe/recipeSelectContent.do?boardNo="+boardNo[i]+"&currentPage="+currentPage[i]+
        		"'>"+" <img src='"+filePath[i]+"' width='80px' height='80px'></a></li>";
       			$("#recentRecipe").append(item);	
			}
        }  		
    })
   	</script>   
</body>
</html>