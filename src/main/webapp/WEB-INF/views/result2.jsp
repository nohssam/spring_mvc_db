<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>json 파싱</title>
<style type="text/css">
	table{
		width: 600px; margin-top: 50px; border-collapse: collapse;
	}
	table, th, td {
	    border: 1px solid red; text-align: center;
    }
</style>
</head>
<body>
	<table>
		<thead>
			<tr><td>시·도별</td><td>총인구 (명)</td><td>1차 접종 누계</td><td>1차 접종 퍼센트</td>
			<td>2차 접종 누계</td><td>2차 접종 퍼센트</td></tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="k">
				<tr>
					<td>${k.city }</td>
					<td><fmt:formatNumber value="${k.totalcount }" pattern="#,###.##" /></td>
					<td><fmt:formatNumber value="${k.firstcount }" pattern="#,###.##" /></td>
					<td><fmt:formatNumber value="${k.firstpersent }" pattern="#,###.##" />% </td>
					<td><fmt:formatNumber value="${k.secondcount }" pattern="#,###.##" /></td>
					<td><fmt:formatNumber value="${k.secondpersent }" pattern="#,###.##" />% </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>