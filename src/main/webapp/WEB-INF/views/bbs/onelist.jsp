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
	
	fieldset {
	 width: 580px;
    }
    input{
    	padding: 10px;
    }
</style>
<script type="text/javascript">
	function list_go(f) {
		f.action="/MyController?cmd=list";
		f.submit();
	}
	function update_go(f) {
		f.action = "/MyController?cmd=update";
		f.submit();
	}
	function delete_go(f) {
		f.action = "/MyController?cmd=delete";
		f.submit();
	}
	
	function comment_go(f) {
		 // 유효성 검사
		if(f.writer.value.trim().length <=0){
			alert("이름을 입력해 주세요");
			f.writer.focus();
			return;
		}
		if(f.content.value.trim().length <=0){
			alert("내용을 입력해 주세요");
			f.content.focus();
			return;
		}
		f.action = "/MyController?cmd=c_write";
		f.submit();
	}
	function comment_del(f) {
		f.action = "/MyController?cmd=c_delete";
		f.submit();
	}
</script>
</head>
<body>
	<div id="bbs">
	<form method="post">
		<table summary="게시판 글쓰기">
			<caption>게시판 글쓰기</caption>
			<tbody>
				<tr>
					<th>제목:</th>
					<td>${bvo.subject}</td>
				</tr>
				<tr>
					<th>이름:</th>
					<td>${bvo.writer}</td>
				</tr>
				<tr>
					<th>내용:</th>
					<td><pre>${bvo.content}</pre></td>
				</tr>
				<tr>
					<th>첨부파일:</th>
					<c:choose>
						<c:when test="${empty bvo.f_name}">
							<td><b>첨부 파일 없음</b></td>
						</c:when>
						<c:otherwise>
							<td><a href="/DownController?path=upload&f_name=${bvo.f_name}" target="_blank"><img  src="upload/${bvo.f_name}" style="80px"></a></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td colspan="2">
						<input type="hidden" value="${bvo.b_idx}" name="b_idx">
						<input type="hidden" value="${cPage}" name="cPage">
						<input type="button" value="수정" onclick="update_go(this.form)">
						<input type="button" value="삭제" onclick="delete_go(this.form)">
						<input type="button" value="목록" onclick="list_go(this.form)">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
	<%-- 댓글 입력 --%>
	<div style="padding:50px; width:580px; margin: auto; ">
		<form method="post">
			<fieldset>
				<p>이름 : <input type="text" name="writer" ></p>
				<p>내용 : <br>
					<textarea rows="4" cols="40" name="content"></textarea>
				 </p>
				 <input type="button" value="댓글저장" onclick="comment_go(this.form)">
				 <input type="hidden" name="b_idx" value="${bvo.b_idx}">
				 <input type="hidden" name="cPage" value="${cPage}">
			 </fieldset>
		</form>
	</div>
	<br><br><br>
	
	<%-- 댓글 출력 --%>
	<div style="display: table;" >
		<c:forEach var="k" items="${c_list}">
		 <div style="border: 1px solid #cc00cc; width: 400px; margin: 20px; padding: 20px;">
		 	<form method="post">
		 		<p>이름 : ${k.writer}</p>
		 		<p>내용 : ${k.content }</p>
		 		<p>날짜 : ${k.write_date.substring(0,10)}</p>
		 		<%-- 실제로는 로그인 성공해야 지만 삭제번트이 보여야 한다. --%>
		 		<input type="button" value="댓글삭제" onclick="comment_del(this.form)">
		 		<input type="hidden" value="${k.c_idx}" name="c_idx">
		 		<input type="hidden" value="${k.b_idx}" name="b_idx">
		 		<input type="hidden" name="cPage" value="${cPage}">
		 	</form>
		 </div>
		</c:forEach>
	</div>
	
	
</body>
</html>

