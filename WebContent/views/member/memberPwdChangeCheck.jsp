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
            text-align: center;
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
        #subTitle{
            color: #707070;
            font-size: 18px;
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
    </style>
</head>
<body>
    <div id="innerContentWrapper">
        <form action="/member/memberIdPwCheck.do" method="post">
            <div><div id="title" >비밀번호 변경</div></div><br>
            <div class="td" id="subTitle">비밀번호 재확인</div>
            <div class="td">
                <input type="text" class="input" name="userId" placeholder=" 아이디를 입력해주세요">
            </div>
            <div class="td">
                <input type="password" class="input" name="userPwd" placeholder=" 비밀번호를 입력해주세요">
                <input type="password" class="input" name="type" value="change" hidden>    
            </div>
            <br><br>
            <div class="td"><input type="submit" value="확인" id="submit" class="btn greenBtn input"></div>
        </form>

    </div>
    
    
</body>
</html>