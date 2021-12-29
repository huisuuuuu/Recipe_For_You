<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<!-- BootStrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
	integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
	crossorigin="anonymous">

<<<<<<< HEAD
	<link rel="stylesheet" type="text/css" href="/assets/css/adminRecipeUpload.css">
=======
<link rel="stylesheet" type="text/css" href="/assets/css/adminRecipeUpload.css">
>>>>>>> b86c4b999d42abc967f92e605cd32fb16a09b38f
  <link rel="stylesheet" type="text/css" href="/assets/css/recipeHeaderFooter.css">
<title>냉장고를 부탁해</title>

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
                    <img src="/assets/common/images//main%20logo.png" alt="">
                </a>
                <div class="box-user">
                   <a href="" class="mypage">
                    <img src="/assets/common/images//headericon1.png" alt="" width="23px" height="28px">
                </a>
                <a href="/views/member/memberLogin.jsp" class="login">
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
                                    <li><a href="/recipe/recipeBoard/selectAll.do">모두보기</a></li>
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
                                <a class="nav-link active nav-mypage" href="/mybox/myboxList.do">마이냉장고</a>
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
			<h1 class="main-title">CREATE A RECIPE</h1>
			<h2 class="sub-title">여러분만의 맛있고 특별한 레시피를 공유해주세요.</h2>
			<form id="recipePostFrm" action="/recipeBoard/UserRecipePostUpload.do" method="post" enctype="multipart/form-data">
				<div class="recipeCode">
					<select name="recipe_Code">
						<option>레시피 분류</option>
						<option value="Recipe_01">한식</option>
						<option value="Recipe_02">양식</option>
						<option value="Recipe_03">일식</option>
						<option value="Recipe_04">중식</option>
						<option value="Recipe_05">분식</option>
						<option value="Recipe_06">채식</option>
						<option value="Recipe_07">다이어트</option>
						<option value="Recipe_08">밑반찬</option>
						<option value="Recipe_09">안주</option>
					</select> <select name="level_Code">
						<option value>난이도</option>
						<option value="LEVEL_01">초급</option>
						<option value="LEVEL_02">중급</option>
						<option value="LEVEL_03">고급</option>
					</select> <select name="time_Code">
						<option value>조리시간</option>
						<option value="TIME_01">10분</option>
						<option value="TIME_02">20분</option>
						<option value="TIME_03">30분</option>
						<option value="TIME_04">40분</option>
						<option value="TIME_05">50분</option>
						<option value="TIME_06">60분이상</option>
					</select>
				</div>
				<div id="thumbnail">
					<label for="thumbnailImage"><img id="thumbnailUpload" src="/assets/images/test2.png"/>
					</label>
				</div>
				<div class="recipeContent">
					<input type=text name="title" class="recipeContentInput"
						placeholder="레시피 제목을 입력해주세요"/></br> <input type=text name="subTitle"
						class="recipeContentInput" maxlength="20"
						placeholder="레시피를 간단히 설명해주세요(20자 이내)" /></br>
						<textarea id="recipeContent" name="recipeContent"
						placeholder="레시피를 상세히 설명해주세요" style="resize: none;"></textarea>
				</div>
				<hr style="width: 650px; margin: 0 auto; margin-top: 15px;">
				<div id="ingredient" class="titleArea">
					<span>재료</span> <select name="big_Code" id="bigCode">
						<option value>대분류</option>
					</select> <select name="middle_Code" id="middleCode">
						<option id="middleOption" value>중분류</option>
					</select> <select name="ingredient_Code" id="ingredientCode">
						<option id="ingredientOption" value>소분류</option>
					</select> <input type="text" maxlength="10" name="ingredient_Num"
						id="ingredient_Num" placeholder="계량 정보 / 예)300g" />
					<button id="ingredientBtn" type="button">추가</button>
				</div>
				<div id="idgredientContent" style="width: 650px; margin: 10px auto;">
					<ul id="ingredientList" style="margin-left: 40px; padding: 0px;">
					</ul>
				</div>
				
				<script>
				
						$('#ingredientBtn').click(function() {
										
										var $ingredientSelect = $('#ingredientCode option:selected');
										var ingredient = $ingredientSelect
												.text();
										var $ingredient_Num = $(
												'#ingredient_Num').val();
										var divTag = document
										.createElement("div");
										var liTag = document
												.createElement("li");
										var spanTag = document
												.createElement("span");
										var inputTag = document.createElement("input");
										var inputTag2 = document.createElement("input");

										liTag.innerHTML = ingredient;
										spanTag.innerHTML = $ingredient_Num;
										divTag.appendChild(liTag);
										divTag.appendChild(spanTag);
										
										inputTag.setAttribute("type","hidden");
										inputTag.setAttribute("name","ingredientName");
										inputTag.setAttribute("value",ingredient);
										
										inputTag2.setAttribute("type","hidden");
										inputTag2.setAttribute("name","ingredientNum");
										inputTag2.setAttribute("value",$ingredient_Num);
										
										divTag.appendChild(inputTag);
										divTag.appendChild(inputTag2);

										if (ingredient != "소분류"
												&& $ingredient_Num.length > 0) {
											$('#ingredientList')[0]
													.appendChild(divTag);
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
						<div id="stepImage_1" class="stepImage" style="margin-right: 5px;"></div>
						</label>
						<div id="stepText_1" class="stepText">
							<textarea id="step_text_1" class="stepText" name="recipeContent"
								placeholder="조리법을 설명해주세요" style="resize: none;"></textarea>
						</div>
					</div>
				</div>
				<div id="recipeUpload">
				<input type="submit" value="작성하기"/>
				<button type="button"><a href="/recipeBoard/recipeBoardAllSelect.do">취소</a></button>
				</div>
				<input type="file" id="thumbnailImage" name="recipeImage" accept="image/*" onchange="setThumbnail(event,'thumbnailImage');" style="display: none;"/>
				<input type="file" id="recipeImage_1" name="recipeImage" style="display: none;"/>
			</form>
			
			<script>
				$(document).ready(function() {
					$('#thumbnailImage').on("change", function() {
						readURL(this);
					});
				});

				function readURL(input) {
					if (input.files && input.files[0]) {
						var reader = new FileReader();
						reader.onload = function(e) {
							$('#thumbnailUpload').attr('src', e.target.result);
							$('#thumbnailUpload').prop('style', "width: 650px; height: 500px; margin: 0 auto; border-style: none;");
							$('#thumbnail').prop('style', "background-image: none;");
						}
						reader.readAsDataURL(input.files[0]);
					}
				}
			</script>
			
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
					imageDiv.setAttribute("class", "stepImage");
					labelTag.appendChild(imageDiv);
					
					textDiv.setAttribute("id", "stepText_" + count)
					textDiv.setAttribute("class", "stepText");
					textareaTag.setAttribute("id", "step_text_" + count)
					textareaTag.setAttribute("name", "recipeContent")
					textareaTag.setAttribute("class", "stepText");
					textareaTag.setAttribute("placeholder", "조리법을 설명해주세요");
					textareaTag.setAttribute("style", "resize:none");
					textDiv.appendChild(textareaTag);
					
					parentDiv.appendChild(pTag);
					parentDiv.appendChild(labelTag);
					parentDiv.appendChild(textDiv);

					inputTag.setAttribute("type", "file");
					inputTag.setAttribute("id", "recipeImage_"+count);
					inputTag.setAttribute("name", "recipeImage");
					inputTag.setAttribute("style", "display:none");

					$('#stepArea')[0].appendChild(parentDiv);
					$('#recipePostFrm')[0].appendChild(inputTag);
					count++;
				})
			</script>
		</div>
		<div id="footer">
		<%@include file="/views/common/footer.jsp" %>
		</div>
	</div>
	
	<script>
		
	$(document).ready(function(){
		$.ajax({
			url: "/admin/productBig.do",
			type: "get",
			dataType: "json",
			success: function(data){
					
				var $bigCode = $('#bigCode');
				
				$.each(data, function(index, item){
					
					var str = "<option value='"+item.bigCode+"'>"+item.bigName+"</option>";
					$bigCode.append(str);
				});
				
			},
			error: function(){
				console.log("Ajax 통신 실패");	
			}
		});
	});
	
	$('#bigCode').click(function(){
		
		var bigCode = $(this).val();
		
		$.ajax({
			url: "/admin/productMiddle.do",
			data: {"bigCode":bigCode},
			dataType: "json",
			type: "get",
			success: function(data){
				
				var $middleCode = $('#middleCode');
				$('#middleCode option:not(#middleOption)').remove();
				
				$.each(data, function(index, item){
					
					var str = "<option value='"+item.middleCode+"'>"+item.middleName+"</option>";
					$middleCode.append(str);
				});
				
			},
			error: function(){
				console.log("Ajax 통신 실패");
			}
		});
		
	});
	
	$('#middleCode').click(function(){
		
		var middleCode = $(this).val();
		
		$.ajax({
			url: "/admin/productIngredient.do",
			data: {"middleCode":middleCode},
			dataType: "json",
			type: "get",
			success: function(data){
				
				var $ingredientCode = $('#ingredientCode');
				$('#ingredientCode option:not(#ingredientOption)').remove();
				
				$.each(data, function(index, item){
					
					var str = "<option value='"+item.ingredientCode+"'>"+item.ingredientName+"</option>";
					$ingredientCode.append(str);
				});
				
			},
			error: function(){
				console.log("Ajax 통신 실패");
			}
		});
		
	});
	</script>
</body>
</html>