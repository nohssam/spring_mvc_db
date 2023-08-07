<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#bbs table {
	    width:580px;
	    margin:0 auto;
	    margin-top:20px;
	    border:1px solid black;
	    border-collapse:collapse;
	    font-size:14px;
	    
	}
	
	#bbs table caption {
	    font-size:20px;
	    font-weight:bold;
	    margin-bottom:10px;
	}
	
	#bbs table th {
	    text-align:center;
	    border:1px solid black;
	    padding:4px 10px;
	}
	
	#bbs table td {
	    text-align:left;
	    border:1px solid black;
	    padding:4px 10px;
	}
	
	.no {width:15%}
	.subject {width:30%}
	.writer {width:20%}
	.reg {width:20%}
	.hit {width:15%}
	.title{background:lightsteelblue}
	.odd {background:silver}
</style>
<script type="text/javascript">
	function update_ok(f) {
		var pwd = "${bvo.pwd}";
		if(f.pwd.value == pwd){
			f.action="/MyController?cmd=upate_ok";
			f.submit();
		}else{
			alert("비밀번호 틀림");
			f.pwd.value="";
			f.pwd.focus();
			return;
		}
	}
	function list_go(f) {
		f.action="/MyController?cmd=list&cPage=${cPage}";
		f.submit();
	}
</script>
</head>
<body>
	<div id="bbs">
	<form method="post" encType="multipart/form-data">
		<table summary="게시판 수정">
			<caption>게시판 수정</caption>
			<tbody>
				<tr>
					<th>제목:</th>
					<td><input type="text" name="subject" size="45" value="${bvo.subject}"></td>
				</tr>
				<tr>
					<th>이름:</th>
					<td><input type="text" name="writer" size="12"  value="${bvo.writer}"></td>
				</tr>
				<tr>
					<th>내용:</th>
					<td><textarea name="content" cols="50" 
							rows="8">${bvo.content}</textarea></td>
				</tr>
				<tr>
					<th>첨부파일:</th>
					<c:choose>
						<c:when test="${empty bvo.f_name}">
							<td><input type="file" name="f_name"><b>이전 파일 없음</b></td>
						         <input type="hidden" name="old_f_name" value="">	
						</c:when>
						<c:otherwise>
							<td><input type="file" name="f_name"><b>${bvo.f_name}</b></td>
						         <input type="hidden" name="old_f_name" value="${bvo.f_name}">	
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<th>비밀번호:</th>
					<td><input type="password" name="pwd" size="12"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="hidden" name="b_idx" value="${bvo.b_idx}">
						<input type="hidden" name="cPage" value="${cPage}">
						<input type="button" value="수정" onclick="update_ok(this.form)"/>
						<input type="reset" value="다시"/>
						<input type="button" value="목록" onclick="list_go(this.form)"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
</body>
</html>

