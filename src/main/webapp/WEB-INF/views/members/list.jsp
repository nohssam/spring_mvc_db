<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table {
	width: 600px; border-collapse: collapse;text-align: center; margin: auto;
}
table, th,td{
	border: 1px solid black; padding: 3px;
}
h1{text-align: center;}
</style>
<script type="text/javascript">
	function addMember() {
		location.href="/members_addForm.do";
	}
</script>
</head>
<body>
	<h1>회원 정보 보기</h1>
	<h2>
		<table>
			<thead>
				<tr><th>번호</th><th>아아디</th><th>이름</th><th>가입일</th></tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty list }">
						<tr><td colspan="4"><h3>원하는 자료가 존재하지 않습니다.</h3></td></tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="k" items="${list}" varStatus="vs">
							<tr>
								<td>${vs.count}</td>
								<td>${k.m_id }</td>
								<td>${k.m_name }</td>
								<td>${k.m_reg.substring(0,10)}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>		
			</tbody>
			<tfoot>
				<tr><td colspan="4"><button onclick="addMember()">회원가입</button></td></tr>
			</tfoot>
		</table>
	</h2>
</body>
</html>