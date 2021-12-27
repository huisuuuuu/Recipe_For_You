<%@page import="kr.co.rfy.member.model.vo.Member"%>
<%@page import="kr.co.rfy.adminRecipeBoard.model.vo.RecipeImage"%>
<%@page import="kr.co.rfy.adminRecipeBoard.model.vo.RecipeIngredient"%>
<%@page import="kr.co.rfy.adminRecipeBoard.model.vo.RecipeContent"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="kr.co.rfy.adminRecipeBoard.model.vo.AdminRecipeBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- font -->
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Noto+Serif+KR:wght@200&display=swap')
	;
</style>

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&family=Roboto:ital,wght@0,100;1,100&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="/assets/css/adminRecipeDetailContent.css">
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

<link rel="stylesheet" type="text/css"
	href="/assets/css/adminRecipeUpload.css">
	
<link rel="stylesheet" type="text/css"
	href="/assets/css/adminRecipeDetailContent.css">	

<title>Insert title here</title>
</head>
<body>

	<%
		//Servlet에서 보내온 데이터 가져오기
		HashMap<String, Object> pageDataMap = (HashMap<String, Object>) request.getAttribute("pageDataMap");

		AdminRecipeBoard recipeBoard = (AdminRecipeBoard) pageDataMap.get("recipeBoard");
		ArrayList<RecipeContent> contentList = (ArrayList<RecipeContent>) pageDataMap.get("contentList");
		ArrayList<RecipeIngredient> ingredientList = (ArrayList<RecipeIngredient>) pageDataMap
				.get("ingredientList");
		ArrayList<RecipeImage> imageList = (ArrayList<RecipeImage>) pageDataMap.get("imageList");

		//recipeBoard 안에는 2가지 상황이 있음
		//1. recipeBoard 객체가 담겨있는 상황(정상적으로 해당 글의 정보를 가져왔을 때)
		//2. null이 담겨있는 상황(정상적으로 해당 글의 정보를 가져오지 못했을 때)
	%>

	<%
		if (recipeBoard != null) {
	%>

		<div id="wrapper">
		<div id="header"></div>
		<div id="navigation">
			<%@include file="/views/common/adminNavigation.jsp" %>
		</div>
		<div id="content">
			<h1 class="main-title">CREATE A RECIPE</h1>
			<h2 class="sub-title">여러분만의 맛있고 특별한 레시피를 공유해주세요.</h2>
			<form id="recipePostFrm" action="/recipeBoard/adminRecipePostUpload.do" method="post" enctype="multipart/form-data">
				<div class="recipeCode">
					<select name="recipe_Code">
						<option><%=recipeBoard.getRecipeName() %></option>
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
						<option value><%=recipeBoard.getLevelName() %></option>
						<option value="LEVEL_01">초급</option>
						<option value="LEVEL_02">중급</option>
						<option value="LEVEL_03">고급</option>
					</select> <select name="time_Code">
						<option value><%=recipeBoard.getTimeName() %></option>
						<option value="TIME_01">10분</option>
						<option value="TIME_02">20분</option>
						<option value="TIME_03">30분</option>
						<option value="TIME_04">40분</option>
						<option value="TIME_05">50분</option>
						<option value="TIME_06">60분이상</option>
					</select>
				</div>
				<div id="thumbnail">
					<label for="thumbnailImage"><div id="thumbnailUpload">
						</div></label>
				</div>
				<div class="recipeContent">
					<input type=text name="title" class="recipeContentInput"
						placeholder="레시피 제목을 입력해주세요" value="<%=recipeBoard.getTitle() %>"/></br> <input type=text name="subTitle"
						class="recipeContentInput" maxlength="20"
						placeholder="레시피를 간단히 설명해주세요(20자 이내)"/ value="<%=recipeBoard.getSubTitle() %>"></br>
						<textarea id="recipeContent" name="recipeContent"
						placeholder="레시피를 상세히 설명해주세요" style="resize: none;"><%=contentList.get(0).getContent()%></textarea>
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
				<div id="ingredient_warpper">
				<div id="idgredientContent" style="width: 650px; margin: 10px auto;">
					<ul id="ingredientList" style="margin-left: 40px; padding: 0px;">
								<%
									for (int i = 0; i < ingredientList.size(); i++) {
								%>
									<div><li><%=ingredientList.get(i).getIngredientName()%></li>
									<span><%=ingredientList.get(i).getIngredientNum()%></span>
									<input type="hidden" name="ingredientName" value="(설도)불고기">
									<input type="hidden" name="ingredientNum" value="300"></div>
									<%
										if (i != ingredientList.size() - 1) {
									%>
									<div><li><%=ingredientList.get(i + 1).getIngredientName()%></li>
									<span><%=ingredientList.get(i + 1).getIngredientNum()%></span>
									<input type="hidden" name="ingredientName" value="(설도)불고기">
									<input type="hidden" name="ingredientNum" value="300"></div>
									<%
										}
									%>
								<%
									i++;
										}
								%>
							</ul>
						</div>
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
						<%
							for (int i = 1; i < contentList.size(); i++) {
						%>
				<div id="stepArea" style="width: 650px; margin: 20px auto;">
					<div id="stepItem_"+i class="step">
						<p>Step<%=i%></p>
						<label for="recipeImage_"+i>
						<div id="stepImage_"+i class="stepImage" style="margin-right: 5px; border-style: none;">
						<img src="<%=imageList.get(i).getFilePath()%>" style="width: 200px; height: 200px; display:inline-block; float:left"/>
						</div>
						</label>
						<div id="stepText_"+i class="stepText">
							<textarea id="step_text_"+i class="stepText" name="recipeContent"
								placeholder="조리법을 설명해주세요" style="resize: none;"><%=contentList.get(i).getContent()%></textarea>
						</div>
					</div>
				</div>
				<input type="file" id="thumbnailImage" name="recipeImage" accept="image/*" onchange="setThumbnail(event,'thumbnailImage');" style="display: none;"/>
				<input type="file" id="recipeImage_"+i name="recipeImage" style="display: none;"/>
				<%} %>
				<div id="recipeUpload">
				<input type="submit" value="완료"/>
				<button type="button"><a href="/recipeBoard/recipeBoardAllSelect.do">취소</a></button>
				</div>
			</form>
			<script>
				var count = $('#stepArea').child.

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
		<div id="footer"></div>
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

	<!-- Optional JavaScript; choose one of the two! -->

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script>
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"</script>

	<!-- Option 2: Separate Popper and Bootstrap JS -->
	<!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    -->
	<%
		} else {
	%>
	<script>
		alert('해당글이 존재하지 않습니다. 다시 확인해주세요.');
		location.replace('/recipeBoard/recipeBoardAllSelect.do');
	</script>
	<%
		}
	%>

</body>
</html>