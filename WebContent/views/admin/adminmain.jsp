<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

  <style>
	#div{
		width:250px;
		height:650px;
		background-color: #7FB292;
        text-align: center;
	}
	.title
	{
	color: white;
	letter-spacing: 3px;
    text-decoration: none;
    
	}
	
	.title:after
	{
       
	 content: "";
    display: block;
	width: 200px;
    border-bottom: 1px solid white;
    margin: 10px auto;

	}
 
   
 #wrap {

        width: 100%;
        height: 50%;
    }
     #navi-list {
        
        width: 100%;
        height: 100%;
    }

        #navi-list>li {
        
        list-style-type: none;
        font-size: 15px;
         width: 60%;
        
        line-height: 60px;
       

    }
       
         #navi-list li>a {
        text-decoration: none;
        display: block;
        width: 100%;
       
       
        color: white;
        /* 글자색*/
    }
    
       
        #navi-list li>a:hover{
        color: black;


    }
       
        .sub-menu{
        width: 80%;
        height:100%;
        
        display: none;
        border: 1px solid white;
    }
         .sub-menu>li {
        
            line-height: 30px;
    }
   .sub-menu>li>a{
       font-size: 13px;
        display: block;
        
       
       
    }
          #navi-list>li:hover>ul {
        display: block;
    }

</style>
</head>
<body>



<div id="div">
	<br><br><br>
	    <a href="" class="title" >냉장고를 부탁해 관리자</a>
	<br><br><br>
	 
  <div id="wrap">
        <div id="navi">
            <ul id="navi-list">
                <li><a href="">레시피 관리</a>
                <ul class="sub-menu">
                        <li><a href="/views/admin/adminRecipeUpload.jsp">레시피 등록</a></li>
                        <li><a href="/recipeBoard/recipeBoardAllSelect.do">레시피 조회</a></li>
                      
                    </ul>
                </li>
                <li><a href="">문의/리뷰 관리</a>
                    <ul class="sub-menu">
                        <li><a href="/views/commons/a.jsp">공지사항</a></li>
                        <li><a href="">자주하는 질문</a></li>
                     
                    </ul>
                </li>
                <li><a href="/admin/memberAllList.do">회원관리</a>
                    <ul class="sub-menu">
                        <li><a href="/admin/memberAllList.do">회원관리 페이지</a></li>
                        <li><a href="/admin/blackList.do">블랙리스트관리</a></li>
                       
                    </ul>
                </li>
               
            </ul>
        </div>


    </div>
</div>
</body>
</html>
</body>
</html>