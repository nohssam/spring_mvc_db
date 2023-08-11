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
		f.action="/board_list.do";
		f.submit();
	}
	function update_go(f) {
		f.action = "/board_updateForm.do";
		f.submit();
	}
	function delete_go(f) {
		f.action = "/board_deleteForm.do";
		f.submit();
	}
	
	function ans_write(f){
		f.action = "/board_ans_insertForm.do";
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
					<td>${bv.title}</td>
				</tr>
				<tr>
					<th>이름:</th>
					<td>${bv.writer}</td>
				</tr>
				<tr>
					<th>내용:</th>
					<td><pre>${bv.content}</pre></td>
				</tr>
				<tr>
					<th>첨부파일:</th>
					<c:choose>
						<c:when test="${empty bv.f_name}">
							<td><b>첨부 파일 없음</b></td>
						</c:when>
						<c:otherwise>
							<td><a href="/board_down.do?f_name=${bv.f_name}"><img  src="resources/images/${bv.f_name}" style="80px"></a></td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td colspan="2">
						<input type="hidden" value="${bv.idx}" name="idx">
						<input type="hidden" value="${cPage}" name="cPage">
						<input type="button" value="수정" onclick="update_go(this.form)">
						<input type="button" value="삭제" onclick="delete_go(this.form)">
						<input type="button" value="목록" onclick="list_go(this.form)">
						<input type="button" value="답글" onclick="ans_write(this.form)">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
</body>
</html>

