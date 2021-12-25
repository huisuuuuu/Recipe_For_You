<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--제이쿼리-->
  <script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
  
   <style>
   @import url('https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@200&display=swap');
   </style>

   
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&family=Roboto:ital,wght@0,100;1,100&display=swap" rel="stylesheet">
    <title>header</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/recipeDetailMain.css">
     <link rel="stylesheet" type="text/css" href="/assets/css/userRecipePostUpload.css">














</head>
<body>


    <div id="wrapper">
        <div class="header fixed-top">
            <div class="top">
                <div class=top-img><img src="/assets/common/images/topicon.png" alt="" width="18px" height="25px"></div>
                <span class="top1 align">마이냉장고에 나만의 식재료를 등록하고 레시피를 추천 받아 보세요!</span>
            </div>
            <div class="header1">
                <a href="" class="logo">
                    <img src="/assets/common/images/main%20logo.png">
                </a>
                <div class="box-user">
                   <a href="" class="mypage">
                    <img src="/assets/common/images/headericon1.png" alt="" width="23px" height="28px">
                </a>
                <a href="" class="login">
                    <img src="/assets/common/images/headericon2.png" alt="" width="80px" height="30px">
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
            
            		<div id="contents">
			<h1 class="main-title">CREATE A RECIPE</h1>
			<h2 class="sub-title">여러분만의 맛있고 특별한 레시피를 공유해주세요.</h2>
			<form id="formList">
				<div class="recipeCode" style="text-align: center">
					<select name="recipe_Code">
						<option>레시피 분류</option>
						<option value="recipe_01">한식</option>
						<option value="recipe_02">양식</option>
						<option value="recipe_03">일식</option>
						<option value="recipe_04">중식</option>
						<option value="recipe_05">분식</option>
						<option value="recipe_06">채식</option>
						<option value="recipe_07">다이어트</option>
						<option value="recipe_08">밑반찬</option>
						<option value="recipe_09">안주</option>
					</select> <select name="levle_Code">
						<option value>난이도</option>
						<option value="levle_01">초급</option>
						<option value="levle_02">중급</option>
						<option value="levle_03">고급</option>
					</select> <select name="time_Code">
						<option value>조리시간</option>
						<option value="time_01">10분</option>
						<option value="time_02">20분</option>
						<option value="time_03">30분</option>
						<option value="time_04">40분</option>
						<option value="time_05">50분</option>
						<option value="time_06">60분이상</option>
					</select>
				</div>
				
				<div id="thumbnail">
					<label for="thumbnailImage"><div id="thumbnailUpload">
						</div></label>
				</div>
				<div class="recipeContent">
					<input type=text name="title" class="recipeContentInput"
						placeholder="레시피 제목을 입력해주세요" /></br> <input type=text name="subTitle"
						class="recipeContentInput" maxlength="20"
						placeholder="레시피를 간단히 설명해주세요(20자 이내)" /></br>
						  <textarea id="recipeContent" placeholder="레시피를 상세히 설명해주세요" style="resize: none;"></textarea>
				</div>
				<hr style="width: 650px; margin: 0 auto; margin-top: 15px;">
				<div id="ingredient" class="titleArea">
						<span>재료</span>
				 <select name="big_Code" onchange="selectBigcode()">
						<option value>대분류</option>
						<option value="PRODUCT_01">반찬/김치</option>
						<option value="PRODUCT_02">정육/계란</option>
						<option value="PRODUCT_03">수산/해산/건어물</option>
						<option value="PRODUCT_04">채소/과일</option>
						<option value="PRODUCT_05">견과/쌀</option>
						<option value="PRODUCT_06">면류</option>
						<option value="PRODUCT_07">간편식/조리식품</option>
						<option value="PRODUCT_08">과일청/잼</option>
						<option value="PRODUCT_09">디저트</option>
						<option value="PRODUCT_10">장류/조미료</option>
						<option value="PRODUCT_11">유제품</option>
					</select>
					 <select name="middle_Code" id="mArea" onchange="selectMiddlecode()" >
						<option value>중분류</option>
					 	
					</select> 
					
					<script>
						var middleCodeList;
						var mArea = document.getElementById("mArea")
						
						function selectBigcode(){
							
							var bigCode = $('select[name=big_Code]').val();
							
							 $.ajax({
								 
								 url:"/recipe/recipeBigcodeSelect.do",
								 type:"post",
								 dataType:"json",
								 data:{"bigCode":bigCode},
								 success:function(data){
									 
									 middleCodeList=data;
									 
									 
									 mArea.innerHTML="";
									 for(var i in middleCodeList)
									{		
										 mArea.innerHTML+='<option value='+middleCodeList[i].mCode+'>'+middleCodeList[i].mName+'</option>';
									  
									}

									 
								 },
								 error:function(){
									 
									 alert('통신 실패');
									 
								 }
								 
							 }); 
						};
						
					</script>
					
					
					
				<select name="ingredient_Code" id="ingredient_Code">
						<option value>소분류</option>
				</select> 
				
				
				<script>
						
						
						
						function selectMiddlecode(){
							
							var middleCode = $('select[name=middle_Code]').val();
							
							
							
							 $.ajax({
								 
								 url:"/recipe/recipeMiddlecodeSelect.do",
								 type:"post",
								 dataType:"json",
								 data:{"middleCode":middleCode},
								 success:function(data){
									
									 
									 alert('a');
									 
								 },
								 error:function(){
									 
									 alert('통신 실패');
									 
								 }
								 
							 }); 
						};
						
					</script>
					
				
				
				
				
				
				
				
				
				
				
				
				


					
					
					
					<input type="text" maxlength="10" name="ingredient_Num"
						id="ingredient_Num" placeholder="계량 정보 / 예)300g" />
					<button id="ingredientBtn" type="button">추가</button>
				</div>
				
				<div id="idgredientContent" style="width: 650px; margin: 10px auto;">
					<ul id="ingredientList" style="margin: 0px; padding: 0px;">
						<li>돼지고기 <span>300g</span>
						</li>
						<li>청주 <span>한 큰술</span>
						</li>
					</ul>
				</div>

				<script>
					$('#ingredientBtn')
							.click(
									function() {

										var $ingredientSelect = $('#ingredient_Code option:selected');
										var ingredient = $ingredientSelect
												.text();
										var $ingredient_Num = $(
												'#ingredient_Num').val();
										var liTag = document
												.createElement("li");
										var spanTag = document
												.createElement("span");

										spanTag.innerHTML = $ingredient_Num;
										liTag.innerHTML = ingredient;
										liTag.appendChild(spanTag);

										if (ingredient != "소분류"
												&& $ingredient_Num.length > 0) {
											$('#ingredientList')[0]
													.appendChild(liTag);
										} else {
											alert("재료 및 계랑 정보를 입력해주세요.");
										}
										;
									})
				</script>
				<div id="recipeStep" class="titleArea">
					<span>조리법</span>
					<button type="button" id="stepBtn">Step 추가</button>
				</div>
				<div id="stepArea" style="width: 650px; margin: 20px auto;">
					<div id="stepItem_1" class="step">
						<p>Step1</p>
						<label for="recipeImage_1">
						<div id="stepImage_1" class="stepImage imageWidth" style="margin-right: 5px; width:200px">
				
						</div>
						</label>
						<div id="stepText_1" class="stepText">
							<textarea id="step_text_1" class="stepText"
								placeholder="조리법을 설명해주세요" style="resize: none;"></textarea>
						</div>
					</div>
				</div>
				<div id="recipeUpload" style="text-align: center"">
				<input type="submit" value="작성하기"/>
				<button type="button"><a href="/">취소</a></button>
				</div>
			</form>
			<form id="fileUpload">
				
				<input type="file" id="thumbnailImage" style="display: none;" />
				 
				<input type="file" id="recipeImage_1" style="display: none;" />
			</form>

			<script>
				var count = 2;

				$('#stepBtn').click(function() {

					var parentDiv = document.createElement("div");
					var imageDiv = document.createElement("div");
					var textDiv = document.createElement("div");
					var pTag = document.createElement("p");
					var labelTag = document.createElement("label");
					var textareaTag = document.createElement("textarea");
					var inputTag = document.createElement("input");

					parentDiv.id = "stepItem_" + count;
					parentDiv.setAttribute("class", "step");
					pTag.innerText = "Step" + count;
                    
					labelTag.setAttribute("for", "recipeImage_" + count);    
					imageDiv.setAttribute("id", "stepImage_" + count)
					imageDiv.setAttribute("class", "stepImage imageWidth");
                    imageDiv.setAttribute("style", "margin-right: 5px; width:200px");
                    labelTag.appendChild(imageDiv);
                    
					textDiv.setAttribute("id", "stepText_" + count)
					textDiv.setAttribute("class", "stepText");
                    
					textareaTag.setAttribute("id", "step_text_" + count)
					textareaTag.setAttribute("class", "stepText");
					textareaTag.setAttribute("placeholder", "조리법을 설명해주세요");
					textareaTag.setAttribute("style", "resize:none");
					textDiv.appendChild(textareaTag);
                    
					parentDiv.appendChild(pTag);
					parentDiv.appendChild(labelTag);
					parentDiv.appendChild(textDiv);
					console.log(parentDiv);

					/*
                    inputTag.setAttribute("type", "file");
					inputTag.setAttribute("id", "recipeImage_" + count);
					inputTag.setAttribute("style", "display:none");
                    */

					$('#stepArea')[0].appendChild(parentDiv);
					//$('#fileUpload')[0].appendChild(inputTag);
					count++;
				})
			</script>
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



    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <!-- Option 2: Separate Popper and Bootstrap JS -->
    <!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->











</body>
</html>