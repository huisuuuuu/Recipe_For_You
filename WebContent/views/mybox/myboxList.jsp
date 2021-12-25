<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL 라이브러리 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&family=Roboto:ital,wght@0,100;1,100&display=swap" rel="stylesheet">
    <title>mybox</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/css/main.css">
    <style>
    
    	section.title {	min-height: 200px;	}
    	section.title h2 {	line-height: 200px; text-align: center;	}
    	
    	
    	.mybox-list { margin: 0 auto; width: 1000px; }
    	
    	.my-box-table table tr:hover { background-color: gray; }
    	.my-box-table table tr th,
    	.my-box-table table tr td { text-align: center; height: 50px; }
    	.my-box-table table tr th { background-color: #5c9970; color: #fff; }
    	
    	.my-box-table table tr th:nth-child(1) { width: 200px; }
    	.my-box-table table tr th:nth-child(2) { width: 200px; }
    	.my-box-table table tr th:nth-child(3) { width: 200px; }
    	.my-box-table table tr th:nth-child(4) { width: 100px; }
    	.my-box-table table tr th:nth-child(4) input { width: 100%; }
    	.my-box-table table tr th:nth-child(5) { width: 280px; }
    	.my-box-table table tr th:nth-child(6) { width: 100px; }
    	
    	.my-box-table table tr select { width: 90%; height: 30px; margin: 0 auto; text-align: center; }
    	
    	.my-box-table table tr td:nth-child(4) input { width: 100px; text-align: center }
    	
    	
    	.mybox-btn-box { height: 50px; text-align: right; }
    	.mybox-btn-box .btn { paddin: 10px; background-color: #5c9970; color: #fff;}
    	
    	.header { position: sticky; background-color: #fff; }
      
    </style>
    
    <script>
    	$(function() {
    		
    		// + 버튼 클릭 시, 행 추가
    		$('#btn-plus').on('click', function () {
    			
    			// 대분류 옵션 조회 AJAX
    			let productBigListSelect = productBigList();
    			let productMiddleListSelect = selectAjax('/mybox/ProductMiddleList.do', {'big_code' : 'PRODUCT_01'});
    			let ingredientListSelect = selectAjax('/mybox/IngredientList.do', {'middle_code' : 'P01_M01'});
    			
    			
    			let addTag = "<tr>"
    					   + "<td>"
    					   + productBigListSelect
    					   + "</td>" 
    					   + "<td>"
    					   //+ "<select name='product_mid' class='product_mid'><option value='1'>정육/계란</option><option value='2'>정육/계란</option><option value='3'>정육/계란</option></select>"
    					   + productMiddleListSelect
    					   + "</td>" 
    					   + "<td>"
    					   //+ "<select name='ingredient' class='ingredient'><option value='1'>돼지고기</option><option value='2'>정육/계란</option><option value='3'>정육/계란</option></select>"
    					   + ingredientListSelect
    					   + "</td>" 
    					   + "<td><input type='text' name='end_date'></td>"
   						   + "<td><input type='text' name='memo'></td>"
   						   + "<td><a href='javascript:;' class='btn-delete'><img src='/assets/common/images/remove.png'></a></td>"
    					   + "</tr>";
    					   
    				$('#my-box-table tbody').append(addTag);
    				
    				
    				// 대분류 변경 이벤트
    	    		$('.product_big').on('change', function () {
    	    			let big_code = $(this).val()
    	    			
    	    			let index = $('.product_big').index(this)
    	    			
    	    			
    	    			// AJAX 요청 - 대분류 하위의 중분류 목록
    	    			let url = '/mybox/ProductMiddleList.do'
    	    			let obj = {'big_code' : big_code}
    	    			let select = selectAjax(url, obj)
    	    			
    	    			// replaceWith() 선택한 요소를 새 요소로 바꾸기 
    	    			$('.product_mid').eq(index).replaceWith(select)
    	    			
    	    			
    	    			let middle_code = $('.product_mid').eq(index).val()
    	    			// AJAX 요청 - 대분류 하위의 중분류 목록
    	    			url = '/mybox/IngredientList.do'
    	    			obj = {'middle_code' : middle_code}
    	    			select = selectAjax(url, obj)
    	    			
    	    			// replaceWith() 선택한 요소를 새 요소로 바꾸기 
    	    			$('.ingredient').eq(index).replaceWith(select)
    	    			
    	    		})
    	    		
    				// 중분류 변경 이벤트
    	    		$('.product_mid').on('change', function () {
						let middle_code = $(this).val()
    	    			
    	    			let index = $('.product_mid').index(this)
    	    			
    	    			// AJAX 요청 - 대분류 하위의 중분류 목록
    	    			let url = '/mybox/IngredientList.do'
    	    			let obj = {'middle_code' : middle_code}
    	    			let select = selectAjax(url, obj)
    	    			
    	    			// replaceWith() 선택한 요소를 새 요소로 바꾸기 
    	    			$('.ingredient').eq(index).replaceWith(select)
    	    			
    	    			
    	    		})
			})
			
			
			// 등록 버튼 클릭 시, 행 추가
    		$('#btn-create').on('click', function () {
    			
    			let form = $('#mybox')
    			
    			if( confirm("정말로 등록하시겠습니까?") )
    				form.submit()
    			
    		})
    		
    		// 대분류 변경 이벤트
    		$('.product_big').on('change', function () {
    			let big_code = $(this).val()
    			
    			let index = $('.product_big').index(this)
    			
    			// AJAX 요청 - 대분류 하위의 중분류 목록
    			let url = '/mybox/ProductMiddleList.do'
    			let obj = {'big_code' : big_code}
    			let select = selectAjax(url, obj)
    			
    			// replaceWith() 선택한 요소를 새 요소로 바꾸기 
    			$('.product_mid').eq(index).replaceWith(select)
    			
    			
    			
    		})
    		
			// 중분류 변경 이벤트
    		$('.product_mid').on('change', function () {
    			let middle_code = $(this).val()
    			
    			let index = $('.product_mid').index(this)
    			
    			// AJAX 요청 - 대분류 하위의 중분류 목록
    			let url = '/mybox/IngredientList.do'
    			let obj = {'middle_code' : middle_code}
    			let select = selectAjax(url, obj)
    			
    			// replaceWith() 선택한 요소를 새 요소로 바꾸기 
    			$('.ingredient').eq(index).replaceWith(select)
    			
    		})
    		
    		
    		
    		// 삭제(x)버튼 클릭 이벤트
    		$('.btn-delete').on('click', function() {
    			let my_box_no = $(this).attr('data')
    			
    			if( confirm("정말로 삭제하시겠습니까?") )
    				deleteMyBox(my_box_no)
    			
    		})
    		
		})
		
		
		// 대분류 AJAX 요청 
		// return : <select> 
		function productBigList() {
    		
    		let result = ''
    		$.ajax({
    		    url				:'/mybox/ProductBigList.do', 	// request 보낼 서버의 경로
    		    type			:'post', 						// 메소드(get, post, put 등)
    		    data			: {}, 							// 보낼 데이터
    		    async			: false,						//
    		    success: function(data) {
    		        //서버로부터 정상적으로 응답이 왔을 때 실행
    		        if( typeof(data) == 'undefined' ) {
    		        	alert('데이터 확인 불가')
    		        	return
    		        }
    		        
    		        result = data
					   		        
    		    },
    		    error: function(err) {
    		        //서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
    		        alert("에러가 발생하였습니다.")
    		    }
    		});
    		console.log(result)
    		return result 
    	}
		
		
		
		
		// ajax 요청
		function selectAjax(url, obj) {
    		
    		let result = ''
    		$.ajax({
    		    url				: url, 	// request 보낼 서버의 경로
    		    type			:'post', 						// 메소드(get, post, put 등)
    		    data			: {
    		    	'big_code'	: obj.big_code,
    		    	'middle_code' : obj.middle_code,
    		    }, 			// 보낼 데이터
    		    async			: false,						//
    		    success: function(data) {
    		        //서버로부터 정상적으로 응답이 왔을 때 실행
    		        if( typeof(data) == 'undefined' ) {
    		        	alert('데이터 확인 불가')
    		        	return
    		        }
    		        
    		        result = data
					   		        
    		    },
    		    error: function(err) {
    		        //서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
    		        alert("에러가 발생하였습니다.")
    		    }
    		});
    		console.log(result)
    		return result 
    	}
		
		
		// 삭제 요청
		function deleteMyBox( my_box_no ) {
			
			let result = ''
    		$.ajax({
    		    url				:'/mybox/DeleteMyBox.do', 		// request 보낼 서버의 경로
    		    type			:'post', 						// 메소드(get, post, put 등)
    		    data			: {'my_box_no' : my_box_no}, 	// 보낼 데이터
    		    async			: false,						//
    		    success: function(data) {
    		        //서버로부터 정상적으로 응답이 왔을 때 실행
    		        if( typeof(data) == 'undefined' ) {
    		        	alert('데이터 확인 불가')
    		        	return
    		        }
    		        result = data
    		        
    		        if( result > 0 ) {
    		        	location.reload()
    		        } else {
    		        	alert("삭제가 적용되지 않았습니다.")
    		        }
					   		        
    		    },
    		    error: function(err) {
    		        //서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
    		        alert("에러가 발생하였습니다.")
    		    }
    		});
    		console.log(result)
    		return result 
			
			
		}
    		
    </script>
</head>

<body>
    <div id="wrapper">
        <div class="header fixed-top">
            <div class="top">
                <div class=top-img><img src="${pageContext.request.contextPath}/assets/common/images/topicon.png" alt="" width="18px" height="25px"></div>
                <span class="top1 align">마이냉장고에 나만의 식재료를 등록하고 레시피를 추천 받아 보세요!</span>
            </div>
            <div class="header1">
                <a href="" class="logo">
                    <img src="${pageContext.request.contextPath}/assets/common/images/main logo.png" alt="">
                </a>
                <div class="box-user">
                   <a href="" class="mypage">
                    <img src="${pageContext.request.contextPath}/assets/common/images/headericon1.png" alt="" width="23px" height="28px">
                </a>
                <a href="/views/member/memberLogin.jsp" class="login">
                    <img src="${pageContext.request.contextPath}/assets/common/images/headericon2.png" alt="" width="80px" height="30px">
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
                                <a class="nav-link active nav-recipe" href="#">레시피</a>
                                <ul class="submenu">
                                    <li><a href="">모두보기</a></li>
                                    <li><a href="">한식</a></li>
                                    <li><a href="">양식</a></li>
                                    <li><a href="">일식</a></li>
                                    <li><a href="">중식</a></li>
                                    <li><a href="">분식</a></li>
                                    <li><a href="">채식</a></li>
                                    <li><a href="">다이어트</a></li>
                                    <li><a href="">밑반찬</a></li>
                                    <li><a href="">안주</a></li>
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
                                <a class="nav-link active nav-mypage" href="${pageContext.request.contextPath}/mybox/myboxList.do">마이냉장고</a>
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
        	
        	<section class="title">
        		<h2>마이냉장고</h2>
        	</section>
        	
        	<!-- 냉장고 리스트 -->
        	<form id="mybox" action="/mybox/myboxCreate.do" method="post">
	        	<section class="mybox-list">
	        		<div class="mybox-btn-box">
	        			<button type="button" class="btn" id="btn-plus">+</button>
	        			<button type="button" class="btn" id="btn-create">등록</button>
	        		</div>
	        		
	        		<div class="my-box-table">
	        			<table border="1" id="my-box-table">
	        				<thead>
	        					<tr>
	        						<th>카테고리</th>
	        						<th>카테고리</th>
	        						<th>제품명</th>
	        						<th>유통기한</th>
	        						<th>메모</th>
	        						<th>삭제</th>
	        					</tr>
	        				</thead>
	        				<tbody>
	        					<c:forEach var="mybox" items="${myboxList}">
	        						<tr>
	        							<td>
	        								<select name="product_big" class="product_big">
        										<option value="${mybox.big_code}">${mybox.big_name}</option>
	        								</select>
	        							</td>	        							
	        							<td>
	        								<select name="product_mid" class="product_mid">
	        									<option value="${mybox.middle_code}">${mybox.middle_name}</option>
	        								</select>
	        							</td>	        							
	        							<td>
	        								<select name="ingredient" class="ingredient">
	        									<option value="${mybox.ingredient_code}">${mybox.ingredient_name}</option>
	        								</select>
	        							</td>	        							
	        							<td>
	        								<c:if test="${mybox.end_yn eq 'Y'}">
		        								<input type="text" name="end_date" value="${mybox.end_date}"  style="color : red;">
	        								</c:if>
	        								<c:if test="${mybox.end_yn eq 'N'}">
		        								<input type="text" name="end_date" value="${mybox.end_date}" >
	        								</c:if>
	        							</td>
	        								        							
	        							<td>
	        								<input type="text" name="memo" value="${mybox.memo}" >
	        							</td>
	        							<td>
	        								<a href="javascript:;" class="btn-delete" data="${mybox.my_box_no}"><img src="/assets/common/images/remove.png"></a>
	        							</td>	        							
	        						</tr>
	        					</c:forEach>
	        				</tbody>
	        			</table>
	        		</div>
	        	
	        	</section>
        	</form>
        	
        
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

    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->


</body></html>