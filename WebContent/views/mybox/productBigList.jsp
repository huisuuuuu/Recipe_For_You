<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<select name="product_big" class="product_big">
<c:forEach var="productBig" items="${productBigList}">
<option value="${productBig.big_code}">${productBig.big_name}</option>
</c:forEach>
</select>
