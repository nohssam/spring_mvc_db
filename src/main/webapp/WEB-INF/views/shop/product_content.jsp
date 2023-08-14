<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품상세보기</title>
<style type="text/css">
table {
	margin: 10px auto;
	width: 800px;
	border-collapse: collapse;
	font-size: 12pt;
	border-color: navy;
}

table, th, td {
	border: 1px solid black;
	padding: 10px;
}

</style>
<script type="text/javascript">
	// 장바구니 담기
	function add_cart() {
		var idx = "${svo.idx}";
		location.href="/shop_addCart.do?idx="+idx;
	}
	// 장바구니 보기
	function show_cart() {
		location.href="/shop_showCart.do";	
	}
</script>
</head>
<body>
	<jsp:include page="top.jsp" />
	<table>
		<tr>
			<td width="40%">제품Category</td>
			<td width="60%">${svo.category} </td>
		</tr>
		<tr>
			<td width="40%">제품번호</td>
			<td width="60%">${svo.p_num }</td>
		</tr>
		<tr>
			<td width="40%">제품이름</td>
			<td width="60%">${svo.p_name }</td>
		</tr>
		<tr>
			<td width="40%">제품판매사</td>
			<td width="60%">${svo.p_company }</td>
		</tr>
		<tr>
			<td width="40%">제품가격</td>
			<td width="60%">시중가 : <fmt:formatNumber value="${svo.p_price }" pattern="#,##0" />원
			<font color="red">(할인가: <fmt:formatNumber value="${svo.p_saleprice }" pattern="#,##0" />원)</font></td>	
		</tr>
		<tr>
			<td>제품설명</td>
			<td>${vo.p_content }</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<img src="/resources/images/${svo.p_image_l }" width="450px">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="장바구니에 담기" onclick="add_cart()" > 
				<input type="button" value="장바구니 보기" 	onclick="show_cart()" />
			</td>
		</tr>
	</table>
</body>
</html>