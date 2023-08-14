<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
}
</style>
<script type="text/javascript">
	function delete_go(f) {
		location.href = "/shop_cartdelete.do?idx="+f ;
	}
</script>
</head>
<body>
    <jsp:include page="top.jsp" />
	<table>
		<caption>
			<h2>:: 장바구니 내용 ::</h2>
		</caption>
		<thead>
			<tr bgcolor="#dedede">
				<th style="width: 10%">제품번호</th>
				<th style="width: 15%">제품명</th>
				<th style="width: 15%">단가</th>
				<th style="width: 20%">수량</th>
				<th style="width: 15%">금액</th>
				<th style="width: 10%">삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty cartlist}">
					<tr><td colspan="6"><h3>장바구니에 담은 상품이 없습니다.</h3> </td></tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="k" items="${cartlist}">
						<tr>
							<td>${k.p_num}</td>
							<td>${k.p_name}</td>
							<td>시중가 : <fmt:formatNumber value="${k.p_price}" pattern="#,##0" /><br>
							     <font color="red">(할인가 : <fmt:formatNumber value="${k.p_saleprice}" pattern="#,##0" />)</font>
							</td>
							<td>
								<form method="post" action="/shop_cartedit.do">
									<input type="number" name="p_su" min="1" value="${k.p_su }" style="width: 50px;">
									<input type="hidden" name="p_num" value="${k.p_num}">
									<input type="hidden" name="m_id" value="${k.m_id }">&nbsp;&nbsp;&nbsp;
									<input type="submit" value="수정">									
								</form>
							</td>
							<td><fmt:formatNumber value="${k.p_saleprice * k.p_su}" pattern="#,##0" /></td>
							<td><button onclick="delete_go(${k.idx})">삭제</button></td>
						</tr>
						<c:set var="cartTotal" value="${cartTotal = cartTotal + (k.p_saleprice * k.p_su) }" />
					</c:forEach>			
				</c:otherwise>
			</c:choose>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6" style="text-align: right; padding: 10px 50px"> 
				총 결재액 : <fmt:formatNumber value="${cartTotal}" pattern="#,##0" /></td>
			</tr>
		</tfoot>
	</table>
</body>
</html>





















