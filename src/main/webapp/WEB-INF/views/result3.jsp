<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카카오 로그인</title>

</head>
<body>
	<div>
		<header>
			<h1> 개인 정보</h1>
		</header>
		<article>
			<h2>${nickName }</h2>
			<h2>${email }</h2>
			<img src="${profile_image}">
		</article>
	</div>
</body>
</html>