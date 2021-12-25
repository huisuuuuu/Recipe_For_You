<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<select name="ingredient" class="ingredient">
<c:forEach var="ingredient" items="${ingredientList}">
<option value="${ingredient.ingredient_code}">${ingredient.ingredient_name}</option>
</c:forEach>
</select>
