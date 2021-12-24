<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<<<<<<< HEAD
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<H3>비정상적인 접근을 시도하였습니다.</H3>
	<H5>정상적인 방법으로 접근을 해주시길 바랍니다.</H5>
	<a href="/">메인 페이지로 이동</a><br>
=======
<!DOCTYPE html>
<html>
<head>
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<meta charset="UTF-8">
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
        #consent{
            height : 20px;
            width : 20px;
            margin-top: 13px;
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
                  	비정상적인 접근입니다.
                  </div>
                  <div class="modal-footer">
                    <a href="/"><button type="button" class="btn greenBtn" data-bs-dismiss="modal" id="#btn">확인</button></a>
                  </div>
                </div>
              </div>
            </div>
            
	<script>
		$(function(){
        	$("#alertModal").modal("show");
		})
	
	</script> 
>>>>>>> d5939b1f99d94c7a744a9b52d10efd48952b58be
</body>
</html>