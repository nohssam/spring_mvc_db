<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 방 명 록 </title>
<style type="text/css">
	a { text-decoration: none;}
	table{width: 600px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid black; padding: 3px}
	div{width: 600px; margin:auto; text-align: center;}
</style>
<script type="text/javascript">
	function delete_go(f) {
		var pwd = "${vo.pwd}"	;
		if(f.pwd.value == pwd){
			var chk = confirm("정말 삭제할까요?");
			if(chk){
				f.action="/jsp/GuestBook2";
				f.submit();
			}else{
				history.go(-1);
			}
			
		}else{
			alert("비빌번호 틀림");
			f.pwd.value="";
			f.pwd.focus();
			return;
		}
	}
</script>
</head>
<body>
	<div>
		<h2>방명록 : 삭제화면</h2>
		<hr />
		<p>[<a href="/jsp/GuestBook2?cmd=list">목록으로 이동</a>]</p>
		
		<form method="post">
			<table>
				<tr align="center">
					<td bgcolor="#99ccff">비밀번호</td>
					<td><input type="password" name="pwd" size ="20"></td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2">
							<input type="hidden" name="idx" value="${vo.idx }">
							<input type="hidden" name="cmd" value="delete_ok">
							<input type="button" value="삭제" onclick="delete_go(this.form)">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value="취소" >
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>