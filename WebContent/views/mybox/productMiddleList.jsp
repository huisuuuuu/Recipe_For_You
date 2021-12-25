<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<select name="product_mid" class="product_mid">
<c:forEach var="productMid" items="${productMiddleList}">
<option value="${productMid.middle_code}">${productMid.middle_name}</option>
</c:forEach>
</select>


<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
	$(function() {
		
		//중분류 변경 이벤트
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


</script>
