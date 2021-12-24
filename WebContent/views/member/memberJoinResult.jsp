<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL 라이브러리 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	<!-- bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<title>Insert title here</title>
<style>
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
        .greenBtn{
            background-color: #7FB292;
            color: white;
            border: 0px;
        }
        .Modal{
            top: 20%
        }

</style>
</head>
<body>
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
                    <a href="/"><button type="button" class="btn greenBtn" data-bs-dismiss="modal" id="#btn">확인</button></a>
                  </div>
                </div>
              </div>
            </div>
            
	<script>
		$(function(){
			var result = ${requestScope.result};
	
			if(result==true){
				$("#alertModalMSG").html("회원가입이 되었습니다.");
			}else{
				$("#alertModalMSG").html("회원가입 실패하였습니다."+"<br>"+" - 지속적인 문제 발생 시 관리자에게 문의해주세요 - ");
			}
        	$("#alertModal").modal("show");
		})
	
	</script> 
</body>
</html>